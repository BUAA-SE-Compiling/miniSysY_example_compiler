package frontend;

import ir.IRBuilder;
import ir.Module;
import ir.Value;
import ir.type.*;
import ir.values.BasicBlock;
import ir.values.Constant;
import ir.values.Function;
import ir.values.GlobalVariable;
import ir.values.instructions.Inst;
import ir.values.instructions.TerminatorInst.*;

import java.math.BigInteger;
import java.util.*;
import java.util.List;

import ir.values.instructions.Inst.*;
import frontend.miniSysYParser.*;

import javax.script.ScriptEngineManager;
import javax.swing.*;

public class Visitor extends miniSysYBaseVisitor<Void> {
    //  visit*函数返回的值都是Value,
    // 所以一些值能够直接通过visit函数的返回值进行传递，但有一些额外的值没办法这么搞，我就通过全局变量传了。
    Module m = Module.module; //module 是单例的
    private final IRBuilder builder = IRBuilder.getInstance();//builder也是单例
    private final Scope scope = new Scope();//和作用域相匹配的符号表
    // singleton variables
    private final Constant.ConstantInt CONST0 = Constant.ConstantInt.get(0);
    private final Type i32Type_ = IntegerType.getI32();
    private final Type voidType_ = Type.VoidType.getType();
    //status word
    private boolean usingInt_ = false;//常量初始化要对表达式求值，并且用的Ident也要是常量
    private boolean globalInit_ = false;//初始化全局变量
    private boolean buildCall = false;    //用于检查是否在生成 call 指令
    private boolean expInRel = false;    //用于短路求值
    //用于回填
    private final BasicBlock breakMark = new BasicBlock("");
    private final BasicBlock continueMark = new BasicBlock("");
    //回填的用到的数据结构，每解析一层WhileStmt都会push一个ArrayList到Stack中
    //用于处理嵌套循环的break与continue
    Stack<ArrayList<Br>> backPatchRecord = new Stack<>();
    /**
     * 我使用全局变量来在函数间进行参数的传递，这么做是为了方便理解代码
     * 你也可以把上面 `extends miniSysYBaseVisitor<Void>` 部分的 `Void`改成`Value`
     * 这样当你在调用函数 visitXXX(abcExp)的时候，能够通过对应的visit函数的返回值来在函数之间传递Value
     */
    private Value tmp_;
    private int tmpInt_;
    private Type tmpTy_;
    private ArrayList<Type> tmpTyArr;
    private ArrayList<Value> tmpArr_;

    /**
     * program : compUnit ;
     * <p>
     * 初始化 module，定义内置函数
     */
    @Override
    public Void visitProgram(miniSysYParser.ProgramContext ctx) {
        builder.setModule(m);
        //先把库函数都加到 scope 里
        IntegerType i32Type = IntegerType.getI32();
        Type.VoidType voidType = Type.VoidType.getType();
        PointerType ptri32Type = PointerType.getPointTy(i32Type);
        ArrayList<Type> params_empty = new ArrayList<>(Collections.emptyList());
        ArrayList<Type> params_int = new ArrayList<>(Collections.singletonList(i32Type_));
        ArrayList<Type> params_array = new ArrayList<>(Collections.singletonList(ptri32Type));
        ArrayList<Type> params_int_and_array = new ArrayList<>(Arrays.asList(i32Type, ptri32Type));
        ArrayList<Type> param_memset = new ArrayList<>(Arrays.asList(ptri32Type, i32Type, i32Type));
        scope.put("memset", builder.buildFunction("memset", new FunctionType(voidType_, param_memset), true));
        ;
        scope.put("getint", builder.buildFunction("getint", new FunctionType(i32Type, params_empty), true));
        scope.put("getarray", builder.buildFunction("getarray", new FunctionType(i32Type, params_array), true));
        scope.put("getch", builder.buildFunction("getch", new FunctionType(i32Type, params_empty), true));
        scope.put("putint", builder.buildFunction("putint", new FunctionType(voidType, params_int), true));
        scope.put("putch", builder.buildFunction("putch", new FunctionType(voidType, params_int), true));
        scope.put("putarray", builder.buildFunction("putarray", new FunctionType(voidType, params_int_and_array), true));
        return super.visitProgram(ctx);
    }

    public Constant genConstArr(ArrayList<Integer> dims, ArrayList<Value> inits) {
        var curDimLength = dims.get(0);
        var curDimArr = new ArrayList<Constant>();
        var length = inits.size() / curDimLength;
        var arrTy = i32Type_;
        if (length == 1) {
            for (int i = 0; i < curDimLength; i++) {
                curDimArr.add((Constant) inits.get(i));
            }
        } else {
            for (int i = 0; i < curDimLength; i++) {
                //fix subDims and add to curDimArr
                curDimArr.add(
                        genConstArr(
                                new ArrayList<>(dims.subList(1, dims.size())),
                                new ArrayList<>(inits.subList(length * i, length * (i + 1)))));

            }
        }

        for (int i = dims.size(); i > 0; i--) {
            arrTy = new ArrayType(arrTy, dims.get(i - 1));
        }
        return builder.getConstantArray(arrTy, curDimArr);
    }

    // TODO: 2021/12/21
    @Override
    public Void visitConstDef(miniSysYParser.ConstDefContext ctx) {
        var name = ctx.IDENT().getText();
        if (scope.top().get(name) != null) throw new RuntimeException("name already exists");
        if (ctx.constExp().isEmpty()) {//not array
            if (ctx.constInitVal() != null) {
                visit(ctx.constInitVal());
                scope.put(ctx.IDENT().getText(), tmp_);
            }
        } else {
            var arrTy = i32Type_;
            var dims = new ArrayList<Integer>();
            ctx.constExp().forEach(context -> {
                visit(context);
                dims.add(((Constant.ConstantInt) tmp_).getVal());
            });
            for (var i = dims.size(); i > 0; i--) arrTy = new ArrayType(arrTy, dims.get(i - 1));
            if (scope.isGlobal()) {
                if (ctx.constInitVal() != null) {
                    ctx.constInitVal().dimInfo_ = dims;
                    globalInit_ = true;
                    visit(ctx.constInitVal());
                    globalInit_ = false;
                    var arr = tmpArr_;
                    ArrayList<Constant> g = new ArrayList<>();
                    arr.forEach(i -> g.add((Constant.ConstantInt) i));
                    var init = new Constant.ConstantArray(arrTy, g);
                    var variable = builder.getGlobalVariable(ctx.IDENT().getText(), arrTy, init);
                    variable.setConst();
                    scope.put(ctx.IDENT().getText(), variable);
                } else {
                    var variable = builder.getGlobalVariable(ctx.IDENT().getText(), arrTy, null);
                    scope.put(ctx.IDENT().getText(), variable);
                }
            } else {
                var allocatedArray = builder.buildAlloca(arrTy);
                scope.put(ctx.IDENT().getText(), allocatedArray);
                if (ctx.constInitVal() != null) {
                    allocatedArray.setInit();
                    ctx.constInitVal().dimInfo_ = dims;
                    visit(ctx.constInitVal());
                    var arr = tmpArr_;
                    var ptr = builder.buildGEP(allocatedArray, new ArrayList<>() {{
                        add(CONST0);
                        add(CONST0);
                    }});
                    for (int i = 1; i < ctx.constInitVal().dimInfo_.size(); i++) {
                        ptr = builder.buildGEP(ptr, new ArrayList<>() {{
                            add(CONST0);
                            add(CONST0);
                        }});
                    }
                    for (int i = 0; i < arr.size(); i++) {
                        if (i == 0) {
                            builder.buildStore(arr.get(0), ptr);
                        } else {
                            int finalI = i;
                            var p = builder.buildGEP(ptr, new ArrayList<>() {{
                                add(Constant.ConstantInt.get(finalI));
                            }});
                            builder.buildStore(arr.get(i), p);
                        }
                    }
                }
            }
        }
        return null;
    }


    /**
     * constInitVal : constExp | (L_BRACE (constInitVal (COMMA constInitVal)*)? R_BRACE) ;
     */
    @Override
    public Void visitConstInitVal(miniSysYParser.ConstInitValContext ctx) {
        if ((ctx.constExp() != null) && ctx.dimInfo_ == null) {
            visit(ctx.constExp());
        } else {
            var curDimLength = ctx.dimInfo_.get(0);
            var sizeOfEachEle = 1;//&#x6BCF;&#x4E2A;&#x5143;&#x7D20;&#xFF08;i32&#x6216;&#x8005;&#x662F;&#x6570;&#x7EC4;&#xFF09;&#x7684;&#x957F;&#x5EA6;
            var arrOfCurDim = new ArrayList<Value>();//
            //calculate Size of Ele in cur dim
            for (int i = 1; i < ctx.dimInfo_.size(); i++) {
                sizeOfEachEle *= ctx.dimInfo_.get(i);
            }
            for (ConstInitValContext constInitValContext : ctx.constInitVal()) {
                if (constInitValContext.constExp() == null) {
                    var pos = arrOfCurDim.size();
                    for (int i = 0; i < (sizeOfEachEle - (pos % sizeOfEachEle)) % sizeOfEachEle; i++) {
                        arrOfCurDim.add(CONST0);//长度不足一个ele的补0为一个ele长
                    }
                    constInitValContext.dimInfo_ = new ArrayList<>(
                            ctx.dimInfo_.subList(1, ctx.dimInfo_.size()));
                    visit(constInitValContext);
                    arrOfCurDim.addAll(tmpArr_);
                } else {
                    visit(constInitValContext);
                    arrOfCurDim.add(tmp_);
                }
            }
            for (int i = arrOfCurDim.size(); i < curDimLength * sizeOfEachEle; i++) {
                arrOfCurDim.add(CONST0);
            }//长度不足一个ele*dimsize 的补0
            tmpArr_ = arrOfCurDim;
        }
        return null;
    }


    @Override
    public Void visitVarDef(miniSysYParser.VarDefContext ctx) {
        var varName = ctx.IDENT().getText();
        if (scope.top().get(varName) != null) throw new RuntimeException("name exists");
        if (ctx.constExp().isEmpty()) {
            if (scope.isGlobal()) {
                if (ctx.initVal() != null) {
                    globalInit_ = true;
                    visit(ctx.initVal());
                    globalInit_ = false;
                    var initializer = (Constant) tmp_;
                    var v = builder.getGlobalVariable(varName, i32Type_, initializer);
                    scope.put(varName, v);
                } else {
                    var initializer = CONST0;
                    var v = builder.getGlobalVariable(varName, i32Type_, initializer);
                    scope.put(varName, v);
                }
            } else {
                var allocator = builder.buildAlloca(i32Type_);
                scope.put(varName, allocator);
                if (ctx.initVal() != null) {
                    visit(ctx.initVal());
                    builder.buildStore(tmp_, allocator);
                }
            }
        } else {//array
            var arrTy = i32Type_;
            var dims = new ArrayList<Integer>();
            ctx.constExp().forEach(context -> {
                visit(context);
                dims.add(((Constant.ConstantInt) tmp_).getVal());
            });
            for (var i = dims.size(); i > 0; i--) {
                arrTy = new ArrayType(arrTy, dims.get(i - 1));
            }
            if (scope.isGlobal()) {
                if (ctx.initVal() != null) {
                    ctx.initVal().dimInfo_ = dims;
                    globalInit_ = true;
                    visit(ctx.initVal());
                    globalInit_ = false;
                    var arr = tmpArr_;
                    ArrayList<Constant> g = new ArrayList<>();
                    arr.forEach(i -> g.add(((Constant.ConstantInt) i)));
                    var init = genConstArr(dims, arr);
                    var glo = builder.getGlobalVariable(ctx.IDENT().getText(), arrTy, init);
                    scope.put(ctx.IDENT().getText(), glo);
                } else {
                    var v = builder.getGlobalVariable(ctx.IDENT().getText(), arrTy, null);
                    scope.put(ctx.IDENT().getText(), v);
                }
            } else {
                var alloc = builder.buildAlloca(arrTy);
                scope.put(ctx.IDENT().getText(), alloc);
                if (ctx.initVal() != null && !ctx.initVal().initVal().isEmpty()) {
                    alloc.setInit();
                    ctx.initVal().dimInfo_ = dims;
                    visit(ctx.initVal());
                    var arr = tmpArr_;
                    var pointer = builder.buildGEP(alloc, new ArrayList<>() {{
                        add(CONST0);
                        add(CONST0);
                    }});
                    for (var i = 1; i < dims.size(); i++) {
                        pointer = builder.buildGEP(pointer, new ArrayList<>() {{
                            add(CONST0);
                            add(CONST0);
                        }});
                    }
                    builder.buildCall(((Function) scope.find("memset")), new ArrayList<>(Arrays.asList(pointer, CONST0, Constant.ConstantInt.get(arr.size() * 4))));
                    for (int i = 0; i < arr.size(); i++) {
                        var t = arr.get(i);
                        if (t instanceof Constant.ConstantInt o && o.getVal() == 0) continue;
                        if (i != 0) {
                            int finalI = i;
                            var ptr = builder.buildGEP(pointer, new ArrayList<>() {{
                                add(Constant.ConstantInt.get(finalI));
                            }});
                            builder.buildStore(t, ptr);
                        } else {
                            builder.buildStore(t, pointer);
                        }
                    }
                } else if (ctx.initVal() != null && ctx.initVal().initVal().isEmpty()) {
                    var size = 1;
                    for (Integer dim : dims) size *= dim;
                    var pointer = builder.buildGEP(alloc, new ArrayList<>() {{
                        add(CONST0);
                        add(CONST0);
                    }});
                    for (int i = 1; i < dims.size(); i++) {
                        pointer = builder.buildGEP(pointer, new ArrayList<>() {{
                            add(CONST0);
                            add(CONST0);
                        }});
                    }
                    builder.buildCall((Function) scope.find("memset"), new ArrayList<>(
                            Arrays.asList(pointer, CONST0, Constant.ConstantInt.get(size * 4))));
                }
            }

        }
        return null;
    }

    @Override
    public Void visitInitVal(miniSysYParser.InitValContext ctx) {
        if (ctx.exp() != null && ctx.dimInfo_ == null) {
            if (globalInit_) {
                usingInt_ = true;
                visit(ctx.exp());
                usingInt_ = false;
                tmp_ = Constant.ConstantInt.get(tmpInt_);
            } else visit(ctx.exp());
        } else {
            var curDimLength = ctx.dimInfo_.get(0);
            var sizeOfEachELe = 1;
            var arrOfCurDim = new ArrayList<Value>();
            for (int i = 1; i < ctx.dimInfo_.size(); i++) {
                sizeOfEachELe *= ctx.dimInfo_.get(i);
            }
            for (InitValContext context : ctx.initVal()) {
                if (context.exp() == null) {
                    var pos = arrOfCurDim.size();
                    for (int i = 0; i < (sizeOfEachELe - (pos % sizeOfEachELe)) % sizeOfEachELe; i++) {
                        arrOfCurDim.add(CONST0);
                    }
                    context.dimInfo_ = new ArrayList<>(ctx.dimInfo_.subList(1, ctx.dimInfo_.size()));
                    visit(context);
                    arrOfCurDim.addAll(tmpArr_);
                } else {
                    if (globalInit_) {
                        usingInt_ = true;
                        visit(context.exp());
                        usingInt_ = false;
                        tmp_ = Constant.ConstantInt.get(tmpInt_);
                    } else {
                        visit(context.exp());
                    }
                    arrOfCurDim.add(tmp_);
                }
            }
            for (int i = arrOfCurDim.size(); i < curDimLength * sizeOfEachELe; i++) {
                arrOfCurDim.add(CONST0);
            }
            tmpArr_ = arrOfCurDim;
        }
        return null;
    }

    /**
     * funcDef : funcType IDENT L_PAREN funcFParams? R_PAREN block ;
     * <p>
     * 初始化函数类型；初始化函数参数，并对参数插入 alloca 和 store；初始化基本块
     */
    @Override
    public Void visitFuncDef(miniSysYParser.FuncDefContext ctx) {
        var funcName = ctx.IDENT().getText();
        var retType = voidType_;
        var typeStr = ctx.getChild(0).getText();
        if (typeStr.equals("int")) retType = i32Type_;//返回值类型
        ArrayList<Type> paramTyList = new ArrayList<>();
        if (ctx.funcFParams() != null) {
            visit(ctx.funcFParams());
            paramTyList.addAll(tmpTyArr);
        }
        FunctionType functionType = new FunctionType(retType, paramTyList);
        var func = builder.buildFunction(funcName, functionType, false);
        builder.setFunc(func);
        scope.put(funcName, func);
        var bb = builder.buildBB(func.name + "_ENTRY");
        scope.addLayer();
        scope.preEnter = true;
        builder.setInsertPoint(bb);
        if (ctx.funcFParams() != null) {
            ctx.funcFParams().initBB = true;
            visit(ctx.funcFParams());
        }
        visit(ctx.block());
        var last = builder.curBB().list.getLast().getVal();
        if (last.tag != TAG.Br && last.tag != TAG.Ret) {
            if (func.type instanceof FunctionType o) {
                if (o.getRetTType().isVoidTy()) builder.buildRet();
                if (o.getRetTType().isIntegerType()) builder.buildRet(CONST0);
            }
        }
        return null;
    }

    //这个context 会被 visit 两遍，一遍用来生成在编译时所需要的信息，一遍用来生成 IR
    @Override
    public Void visitFuncFParams(miniSysYParser.FuncFParamsContext ctx) {
        if (ctx.initBB) {
            ctx.initBB = false;
            var paramList = builder.curFunc().getParamList();
            if (paramList.size() != 0) {
                for (int i = 0; i < ctx.funcFParam().size(); i++) {
                    var context = ctx.funcFParam(i);
                    if (!context.L_BRACKT().isEmpty()) {//这是个数组参数
                        var dimList = new ArrayList<Value>();
                        var type = i32Type_;
                        dimList.add(CONST0);
                        for (int j = 0; j < context.exp().size(); j++) {
                            usingInt_ = true;
                            visit(context.exp(context.exp().size() - (j + 1)));
                            usingInt_ = false;
                            dimList.add(tmp_);
                            type = new ArrayType(type, tmpInt_);
                        }
                        //alloca 一个 和 param 相同的 type 的值，然后把 param store 到这个 alloca 里
                        var arrAlloca = builder.buildAlloca(new PointerType(type));
                        builder.buildStore(paramList.get(i), arrAlloca);
                        scope.put(context.IDENT().getText(), arrAlloca);
                        paramList.get(i).setBounds(dimList);
                    } else {//int参数
                        var alloca = builder.buildAlloca(i32Type_);
                        builder.buildStore(paramList.get(i), alloca);
                        scope.put(ctx.funcFParam().get(i).IDENT().getText(), alloca);
                    }
                }
            }
        } else {
            //这个信息是保存给scope来提供给生成 call 指令使用的
            ArrayList<Type> types = new ArrayList<>();
            ctx.funcFParam().forEach(param -> {
                visit(param);
                types.add(tmpTy_);
            });
            tmpTyArr = types;
        }
        return null;
    }

    @Override
    public Void visitFuncFParam(miniSysYParser.FuncFParamContext ctx) {
        //这个 param 是数组
        if (!ctx.L_BRACKT().isEmpty()) {
            var type = i32Type_;
            for (int i = 0; i < ctx.exp().size(); i++) {
                usingInt_ = true;
                visit(ctx.exp(ctx.exp().size() - (i + 1)));
                usingInt_ = false;
                type = new ArrayType(type, tmpInt_);
            }
            tmpTy_ = new PointerType(type);
        } else {//这个param是int值
            tmpTy_ = i32Type_;
        }
        return null;
    }

    @Override
    public Void visitBlock(miniSysYParser.BlockContext ctx) {
        scope.addLayer();
        ctx.blockItem().forEach(this::visit);
        scope.popLayer();
        return null;
    }

    @Override
    public Void visitAssignStmt(miniSysYParser.AssignStmtContext ctx) {
        visit(ctx.lVal());
        var rhs = tmp_;
        visit(ctx.exp());
        var lhs = tmp_;
        builder.buildStore(lhs, rhs);
        return null;
    }

    /**
     * conditionStmt : IF_KW L_PAREN cond R_PAREN stmt (ELSE_KW stmt)? ;
     */
    @Override
    public Void visitConditionStmt(miniSysYParser.ConditionStmtContext ctx) {
        var parentBB = builder.curBB();
        var trueBlock = builder.buildBB("_then");
        var nxtblock = builder.buildBB("_nxtBLock");
        var falseBlock = ctx.ELSE_KW() == null ? nxtblock : builder.buildBB("_else");
        var ifEndWithRet = false;
        ctx.cond().falseblock = falseBlock;
        ctx.cond().trueblock = trueBlock;
        visit(ctx.cond());
        builder.setInsertPoint(trueBlock);
        visit(ctx.stmt(0));
        builder.buildBr(nxtblock);
        //解析完stmt后的最后一个块的末尾语句是ret(),
        //在 有 if 有 else 且 if 条件块和 else 条件块的最后一条语句都是 ret 的时候，就将nxtblock删去，不然会产生一个空的块
        if (builder.curBB().list.getLast().getVal() instanceof Ret) ifEndWithRet = true;
        if (ctx.ELSE_KW() != null) {
            builder.setInsertPoint(falseBlock);
            visit(ctx.stmt(1));
            builder.buildBr(nxtblock);
            if (ifEndWithRet && builder.curBB().list.getLast().getVal() instanceof Ret) nxtblock.node.removeSelf();
        }
        builder.setInsertPoint(nxtblock);
        return null;
    }


    @Override
    public Void visitWhileStmt(miniSysYParser.WhileStmtContext ctx) {
        var parentBB = builder.curBB();
        //如果最后的时候输出正常，这些名字是不会出现在导出的文件里的，如果输出不正常，这些名字可以辅助debug
        var whileCondEntryBlock = builder.buildBB("_whileCondition");
        var trueBlock = builder.buildBB("_body");
        var nxtBlock = builder.buildBB("_nxtBlock");
        backPatchRecord.push(new ArrayList<>());//每解析一个whileStmt都push一层进去，记录这个while的break和continue
        builder.setInsertPoint(parentBB);
        builder.buildBr(whileCondEntryBlock);//在parentBB末尾插入一个跳转到whileCond的Br
        ctx.cond().falseblock = nxtBlock;
        ctx.cond().trueblock = trueBlock;
        builder.setInsertPoint(whileCondEntryBlock);
        visit(ctx.cond());
        builder.setInsertPoint(trueBlock);
        visit(ctx.stmt());
        builder.buildBr(whileCondEntryBlock);
        for (Br br : backPatchRecord.pop()) {
            if (br.getOP(0) == breakMark) {
                br.setOperand(nxtBlock, 0);
            } else if (br.getOP(0) == continueMark) {
                br.setOperand(whileCondEntryBlock, 0);
            }
        }
        builder.setInsertPoint(nxtBlock);
        return null;
    }

    @Override
    public Void visitBreakStmt(miniSysYParser.BreakStmtContext ctx) {
        backPatchRecord.peek().add(builder.buildBr(continueMark));
        return null;
    }

    @Override
    public Void visitContinueStmt(miniSysYParser.ContinueStmtContext ctx) {
        backPatchRecord.peek().add(builder.buildBr(breakMark));
        return null;
    }

    /**
     * returnStmt : RETURN_KW (exp)? SEMICOLON ;
     */
    @Override
    public Void visitReturnStmt(miniSysYParser.ReturnStmtContext ctx) {
        if (ctx.exp() != null) {
            //有返回值
            visit(ctx.exp());
            builder.buildRet(tmp_);
        } else {
            //没有返回值
            builder.buildRet();
        }
        return null;
    }

    /**
     * lVal : IDENT (L_BRACKT exp R_BRACKT)* ;
     */
    @Override
    public Void visitLVal(miniSysYParser.LValContext ctx) {
        var name = ctx.IDENT().getText();
        var val = scope.find(name);
        if (val == null) throw new RuntimeException("undefined value name :" + name);
        if (val.type.isIntegerType()) {//是在编译时就知道值的 int 类型，
            tmp_ = val;
            return null;
        }
        boolean INT = false, PTR = false, ARR = false;
        if (val.type.isPointerTy()) {
            //alloca i32
            INT = ((PointerType) val.type).getContained().isIntegerType();
            //函数传参传入的数组
            PTR = ((PointerType) val.type).getContained().isPointerTy();
            //指向别的数组
            ARR = ((PointerType) val.type).getContained().isArrayTy();
        }
        if (INT) {
            if (ctx.exp().isEmpty()) {
                tmp_ = val;
                return null;
            } else {
                for (ExpContext expContext : ctx.exp()) {
                    visit(expContext);
                    var fromExp = tmp_;
                    val = builder.getGEP(val, new ArrayList<>() {{
                        add(fromExp);
                    }});
                }
                tmp_ = val;
                return null;
            }
        }
        if (PTR) {
            if (ctx.exp().isEmpty()) {
                //作为参数传递给 Call
                tmp_ = builder.buildLoad(((PointerType) val.type).getContained(), val);
            } else {
                PointerType allocatedTy = (PointerType) val.type;
                var containedTy = (PointerType) allocatedTy.getContained();
                var load = builder.buildLoad(containedTy, val);
                visit(ctx.exp(0));
                var gep = builder.buildGEP(load, new ArrayList<>() {{
                    add(tmp_);
                }});
                for (int i = 1; i < ctx.exp().size(); i++) {
                    visit(ctx.exp(i));
                    gep = builder.buildGEP(gep, new ArrayList<>() {{
                        add(CONST0);
                        add(tmp_);
                    }});
                }
                tmp_ = gep;
            }
        }
        if (ARR) {
            if (ctx.exp().isEmpty()) {
                tmp_ = builder.buildGEP(val, new ArrayList<>() {{
                    add(CONST0);
                    add(CONST0);
                }});
            } else {
                for (ExpContext expContext : ctx.exp()) {
                    visit(expContext);
                    val = builder.buildGEP(val, new ArrayList<>() {{
                        add(CONST0);
                        add(tmp_);
                    }});
                }
                tmp_ = val;
            }
        }
        return null;
    }

    @Override
    public Void visitPrimaryExp(miniSysYParser.PrimaryExpContext ctx) {
        if (usingInt_) {
            if (ctx.exp() != null) visit(ctx.exp());
            if (ctx.lVal() != null) {
                visit(ctx.lVal());
                tmpInt_ = ((Constant.ConstantInt) tmp_).getVal();
            }
            if (ctx.number() != null) visit(ctx.number());
        } else {
            if (ctx.exp() != null) visit(ctx.exp());
            if (ctx.lVal() != null) {
                if (buildCall) {//正在生成 call 指令
                    buildCall = false;
                    visit(ctx.lVal());
                    return null;
                } else {
                    visit(ctx.lVal());
                    if (tmp_.type.isIntegerType()) return null;//如果是Integer类型的就不用load
                    //要Load的类型是 Integer*
                    tmp_ = builder.buildLoad(((PointerType) tmp_.type).getContained(), tmp_);
                }
            }
            if (ctx.number() != null) visit(ctx.number());
        }
        return null;
    }

    @Override
    public Void visitNumber(miniSysYParser.NumberContext ctx) {
        visit(ctx.intConst());
        if (!usingInt_) tmp_ = builder.getConstantInt(tmpInt_);
        return null;
    }


    @Override
    public Void visitIntConst(miniSysYParser.IntConstContext ctx) {
        if (ctx.DECIMAL_CONST() != null) tmpInt_ = (new BigInteger(ctx.DECIMAL_CONST().getText(), 10).intValue());
        if (ctx.HEXADECIMAL_CONST() != null)
            tmpInt_ = (new BigInteger(ctx.HEXADECIMAL_CONST().getText().substring(2), 16).intValue());
        if (ctx.OCTAL_CONST() != null) tmpInt_ = (new BigInteger(ctx.OCTAL_CONST().getText(), 8)).intValue();
        return null;
    }

    /**
     * unaryExp
     * : primaryExp
     * | callee
     * | (unaryOp unaryExp)
     * ;
     */
    @Override
    public Void visitUnaryExp(miniSysYParser.UnaryExpContext ctx) {
        if (usingInt_) {//如果需要折叠常量
            if (ctx.unaryExp() != null) {//如果有unaryExp作为子结点，就先visit这个子结点
                visit(ctx.unaryExp());
                //负号，正号和取反
                if (ctx.unaryOp().MINUS() != null) tmpInt_ = -tmpInt_;
                if (ctx.unaryOp().PLUS() != null) tmpInt_ = +tmpInt_;
                if (ctx.unaryOp().NOT() != null) tmpInt_ = tmpInt_ == 0 ? 1 : 0;
            } else {
                if (ctx.primaryExp() != null) return visit(ctx.primaryExp());
                if (ctx.callee() != null) throw new RuntimeException("Func call in constExp");
            }
        } else {
            if (ctx.unaryExp() != null) {
                visit(ctx.unaryExp());
                if (tmp_.type.isI1()) builder.buildZext(tmp_);
                if (ctx.unaryOp().NOT() != null) tmp_ = builder.buildBinary(Inst.TAG.Eq, CONST0, tmp_);
                if (ctx.unaryOp().PLUS() != null) {
                    //do nothing;
                }
                if (ctx.unaryOp().MINUS() != null) {
                    if (tmp_.type.isI1()) tmp_ = builder.buildZext(tmp_);
                    tmp_ = builder.buildBinary(Inst.TAG.Sub, CONST0, tmp_);
                }
            }
            if (ctx.callee() != null) {
                visit(ctx.callee());
            }
            if (ctx.primaryExp() != null) {
                visit(ctx.primaryExp());
            }
        }
        return null;
    }

    /**
     * 函数调用
     * callee : IDENT L_PAREN funcRParams? R_PAREN ;
     */
    @Override
    public Void visitCallee(miniSysYParser.CalleeContext ctx) {
        //函数
        var func = scope.find(ctx.IDENT().getText());
        //实参
        var args = new ArrayList<Value>();
        List<ParamContext> argsCtx;
        if (ctx.funcRParams() != null && func instanceof Function f) {
            argsCtx = ctx.funcRParams().param();
            var paramTys = ((FunctionType) func.type).getParamsTypes();
            for (int i = 0; i < argsCtx.size(); i++) {
                var argument = argsCtx.get(i);
                var paramTy = paramTys.get(i);
                buildCall = !paramTy.isIntegerType();
                visit(argument.exp());
                buildCall = false;
                args.add(tmp_);
            }
        }
        tmp_ = builder.buildCall((Function) func, args);
        return null;
    }

    /**
     * mulExp : unaryExp (mulOp unaryExp)* ;
     */
    @Override
    public Void visitMulExp(miniSysYParser.MulExpContext ctx) {
        if (usingInt_) {
            visit(ctx.unaryExp(0));
            var s = tmpInt_;
            for (var i = 1; i < ctx.unaryExp().size(); i++) {
                visit(ctx.unaryExp(i));
                if (ctx.mulOp(i - 1).MUL() != null) s *= tmpInt_;
                if (ctx.mulOp(i - 1).DIV() != null) s /= tmpInt_;
                if (ctx.mulOp(i - 1).MOD() != null) s %= tmpInt_;
            }
            tmpInt_ = s;
        } else {
            visit(ctx.unaryExp(0));
            var lhs = tmp_;
            for (int i = 1; i < ctx.unaryExp().size(); i++) {
                visit(ctx.unaryExp(i));
                var rhs = tmp_;
                if (lhs.type.isI1()) lhs = builder.buildZext(lhs);
                if (rhs.type.isI1()) rhs = builder.buildZext(rhs);
                if (ctx.mulOp(i - 1).MUL() != null) lhs = builder.buildBinary(TAG.Mul, lhs, rhs);
                if (ctx.mulOp(i - 1).DIV() != null) lhs = builder.buildBinary(TAG.Div, lhs, rhs);
                if (ctx.mulOp(i - 1).MOD() != null) {
                    //x%y=x - (x/y)*y
                    var a = builder.buildBinary(TAG.Div, lhs, rhs);
                    var b = builder.buildBinary(TAG.Mul, a, rhs);
                    lhs = builder.buildBinary(TAG.Sub, lhs, b);
                }
            }
            tmp_ = lhs;
        }
        return null;


    }

    /**
     * @value :
     * <p>
     * addExp : mulExp (addOp mulExp)* ;
     */
    @Override
    public Void visitAddExp(miniSysYParser.AddExpContext ctx) {
        if (usingInt_) {//所有值包括ident都必须是常量
            visit(ctx.mulExp(0));
            var s = tmpInt_;
            for (var i = 1; i < ctx.mulExp().size(); i++) {
                visit(ctx.mulExp(i));
                if (ctx.addOp(i - 1).PLUS() != null) s += tmpInt_;
                if (ctx.addOp(i - 1).MINUS() != null) s -= tmpInt_;
            }
            tmpInt_ = s;
            return null;
        } else {
            visit(ctx.mulExp(0));
            var lhs = tmp_;
            for (int i = 1; i < ctx.mulExp().size(); i++) {
                visit(ctx.mulExp(i));
                var rhs = tmp_;
                if (lhs.type.isI1()) lhs = builder.buildZext(lhs);
                if (rhs.type.isI1()) rhs = builder.buildZext(rhs);
                if (ctx.addOp(i - 1).PLUS() != null) lhs = builder.buildBinary(TAG.Add, lhs, rhs);
                if (ctx.addOp(i - 1).MINUS() != null) lhs = builder.buildBinary(TAG.Sub, lhs, rhs);
            }
            tmp_ = lhs;
        }
        return null;
    }

    /**
     * relExp : addExp (relOp addExp)* ;
     */
    @Override
    public Void visitRelExp(miniSysYParser.RelExpContext ctx) {
        visit(ctx.addExp(0));
        var lhs = tmp_;
        for (int i = 1; i < ctx.addExp().size(); i++) {
            expInRel = false;
            visit(ctx.addExp(i));
            var rhs = tmp_;
            if (ctx.relOp(i - 1).LE() != null) lhs = builder.buildBinary(TAG.Le, lhs, rhs);
            if (ctx.relOp(i - 1).GE() != null) lhs = builder.buildBinary(TAG.Ge, lhs, rhs);
            if (ctx.relOp(i - 1).GT() != null) lhs = builder.buildBinary(TAG.Gt, lhs, rhs);
            if (ctx.relOp(i - 1).LT() != null) lhs = builder.buildBinary(TAG.Lt, lhs, rhs);
        }
        tmp_ = lhs;
        return null;
    }

    /**
     * eqExp : relExp (eqOp relExp)* ;
     */
    @Override
    public Void visitEqExp(miniSysYParser.EqExpContext ctx) {
        visit(ctx.relExp(0));
        var lhs = tmp_;
        for (int i = 1; i < ctx.relExp().size(); i++) {
            expInRel = false;
            visit(ctx.relExp(i));
            if (ctx.eqOp(i - 1).EQ() != null) lhs = builder.buildBinary(TAG.Eq, lhs, tmp_);
            if (ctx.eqOp(i - 1).NEQ() != null) lhs = builder.buildBinary(TAG.Ne, lhs, tmp_);
        }
        tmp_ = lhs;
        return null;
    }

    @Override
    public Void visitLAndExp(miniSysYParser.LAndExpContext ctx) {
        ctx.eqExp().forEach(exp -> {
            var newBB = builder.buildBB("");
            expInRel = true;
            visit(exp);
            if (expInRel) {
                expInRel = false;
                tmp_ = builder.buildBinary(TAG.Ne, tmp_, CONST0);
            }
            builder.buildBr(tmp_, newBB, ctx.falseblock);
            builder.setInsertPoint(newBB);
        });
        builder.buildBr(ctx.trueblock);
        return null;
    }

    @Override
    public Void visitLOrExp(miniSysYParser.LOrExpContext ctx) {
        ctx.lAndExp(0).isFirstBlock = true;
        for (int i = 0; i < ctx.lAndExp().size() - 1; i++) {
            var newBB = builder.buildBB("");
            ctx.lAndExp(i).trueblock = ctx.trueblock;//向下传递继承属性，记录要跳转的块从而实现短路求值
            ctx.lAndExp(i).falseblock = newBB;
            visit(ctx.lAndExp(i));
            builder.setInsertPoint(newBB);
        }
        ctx.lAndExp(ctx.lAndExp().size() - 1).falseblock = ctx.falseblock;
        ctx.lAndExp(ctx.lAndExp().size() - 1).trueblock = ctx.trueblock;
        visit(ctx.lAndExp(ctx.lAndExp().size() - 1));
        return null;
    }

    @Override
    public Void visitCond(miniSysYParser.CondContext ctx) {
        ctx.lOrExp().falseblock = ctx.falseblock;
        ctx.lOrExp().trueblock = ctx.trueblock;
        visit(ctx.lOrExp());
        return null;
    }

    /**
     * constExp : addExp ;
     * <p>
     * tmpint_ -> res of exp
     * <p>
     * 表达式求和
     */
    @Override
    public Void visitConstExp(miniSysYParser.ConstExpContext ctx) {
        usingInt_ = true;
        visit(ctx.addExp());
        tmp_ = builder.getConstantInt(tmpInt_);
        usingInt_ = false;
        return null;
    }

}
