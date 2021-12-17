package frontend;

import ir.IRBuilder;
import ir.Module;
import ir.Value;
import ir.type.FunctionType;
import ir.type.IntegerType;
import ir.type.PointerType;
import ir.type.Type;
import ir.values.Constant;
import ir.values.Function;
import ir.values.instructions.Inst;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ir.values.instructions.Inst.*;
import frontend.miniSysYParser.*;

public class Visitor extends miniSysYBaseVisitor<Void> {
    //  visit*函数返回的值都是Value,
    // 所以一些值能够直接通过visit函数的返回值进行传递，但有一些额外的值没办法这么搞，我就通过全局变量传了。
    Module m = Module.module; //module 是单例的
    private final IRBuilder builder = IRBuilder.getInstance();//builder也是单例的
    private final Scope scope = new Scope();//和作用域相匹配的符号表
    // singleton variables
    private final Constant.ConstantInt CONST0 = Constant.ConstantInt.get(0);
    private final Type i32Type_ = IntegerType.getI32();
    private final Type voidType_ = Type.VoidType.getType();
    //status word
    private boolean usingInt_ = false;//常量初始化要对表达式求值，并且用的Ident也要是常量
    private boolean globalInit_ = false;//初始化全局变量
    private boolean buildCall = false;
    private boolean expInRel = false;
    /**
     * 我使用全局变量来在函数间进行参数的传递，这么做是为了方便理解代码
     * 你也可以把上面 `extends miniSysYBaseVisitor<Void>` 部分的 `Void`改成`Value`
     * 这样当你在调用函数 visitXXX(abcExp)的时候，能够通过对应的visit函数的返回值来在函数之间传递Value
     */
    private Value tmp_;
    private int tmpInt_;
    private Type tmpTy_;
    private ArrayList<Type> tmpTyArr;

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
        scope.put("putarray",
                builder.buildFunction("putarray", new FunctionType(voidType, params_int_and_array), true));
        return super.visitProgram(ctx);
    }

    @Override
    public Void visitCompUnit(miniSysYParser.CompUnitContext ctx) {
        return super.visitCompUnit(ctx);
    }

    @Override
    public Void visitDecl(miniSysYParser.DeclContext ctx) {
        return super.visitDecl(ctx);
    }

    @Override
    public Void visitConstDecl(miniSysYParser.ConstDeclContext ctx) {
        return super.visitConstDecl(ctx);
    }

    @Override
    public Void visitBType(miniSysYParser.BTypeContext ctx) {
        return super.visitBType(ctx);
    }

    @Override
    public Void visitConstDef(miniSysYParser.ConstDefContext ctx) {
        return super.visitConstDef(ctx);
    }

    @Override
    public Void visitConstInitVal(miniSysYParser.ConstInitValContext ctx) {
        return super.visitConstInitVal(ctx);
    }

    @Override
    public Void visitVarDecl(miniSysYParser.VarDeclContext ctx) {
        return super.visitVarDecl(ctx);
    }

    @Override
    public Void visitVarDef(miniSysYParser.VarDefContext ctx) {
        return super.visitVarDef(ctx);
    }

    @Override
    public Void visitInitVal(miniSysYParser.InitValContext ctx) {
        return super.visitInitVal(ctx);
    }

    @Override
    public Void visitFuncDef(miniSysYParser.FuncDefContext ctx) {
        return super.visitFuncDef(ctx);
    }

    @Override
    public Void visitFuncType(miniSysYParser.FuncTypeContext ctx) {
        return super.visitFuncType(ctx);
    }

    @Override
    public Void visitFuncFParams(miniSysYParser.FuncFParamsContext ctx) {
        return super.visitFuncFParams(ctx);
    }

    @Override
    public Void visitFuncFParam(miniSysYParser.FuncFParamContext ctx) {
        return super.visitFuncFParam(ctx);
    }

    @Override
    public Void visitBlock(miniSysYParser.BlockContext ctx) {
        return super.visitBlock(ctx);
    }

    @Override
    public Void visitBlockItem(miniSysYParser.BlockItemContext ctx) {
        return super.visitBlockItem(ctx);
    }

    @Override
    public Void visitStmt(miniSysYParser.StmtContext ctx) {
        return super.visitStmt(ctx);
    }

    @Override
    public Void visitAssignStmt(miniSysYParser.AssignStmtContext ctx) {
        return super.visitAssignStmt(ctx);
    }

    @Override
    public Void visitExpStmt(miniSysYParser.ExpStmtContext ctx) {
        return super.visitExpStmt(ctx);
    }

    @Override
    public Void visitConditionStmt(miniSysYParser.ConditionStmtContext ctx) {
        return super.visitConditionStmt(ctx);
    }

    @Override
    public Void visitWhileStmt(miniSysYParser.WhileStmtContext ctx) {
        return super.visitWhileStmt(ctx);
    }

    @Override
    public Void visitBreakStmt(miniSysYParser.BreakStmtContext ctx) {
        return super.visitBreakStmt(ctx);
    }

    @Override
    public Void visitContinueStmt(miniSysYParser.ContinueStmtContext ctx) {
        return super.visitContinueStmt(ctx);
    }

    @Override
    public Void visitReturnStmt(miniSysYParser.ReturnStmtContext ctx) {
        return super.visitReturnStmt(ctx);
    }

    @Override
    public Void visitExp(miniSysYParser.ExpContext ctx) {
        return super.visitExp(ctx);
    }

    @Override
    public Void visitCond(miniSysYParser.CondContext ctx) {
        return super.visitCond(ctx);
    }

    @Override
    public Void visitLVal(miniSysYParser.LValContext ctx) {
        return super.visitLVal(ctx);
    }

    @Override
    public Void visitPrimaryExp(miniSysYParser.PrimaryExpContext ctx) {

        return super.visitPrimaryExp(ctx);

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
                buildCall = paramTy.isIntegerType();
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
        for (int i = 0; i < ctx.addExp().size(); i++) {
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
    public Void visitConstExp(miniSysYParser.ConstExpContext ctx) {
        return super.visitConstExp(ctx);
    }
}
