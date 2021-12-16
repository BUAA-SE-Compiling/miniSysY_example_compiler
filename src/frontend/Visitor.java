package frontend;

import ir.IRBuilder;
import ir.Module;
import ir.Value;
import ir.type.FunctionType;
import ir.type.IntegerType;
import ir.type.PointerType;
import ir.type.Type;
import ir.values.Constant;
import ir.values.instructions.Inst;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
        } else {// TODO: 2021/12/14
            if (ctx.unaryExp() != null) {
                visit(ctx.unaryExp());
                if (tmp_.type.isI1()) builder.buildZext(tmp_);
                if (ctx.unaryOp().NOT() != null) {
                    tmp_ = builder.getBinary(Inst.TAG.Eq, CONST0, tmp_);
                }
                ;
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
     * callee : IDENT L_PAREN funcRParams? R_PAREN ;
     */
    @Override
    public Void visitCallee(miniSysYParser.CalleeContext ctx) {
        return super.visitCallee(ctx);
    }

    @Override
    public Void visitUnaryOp(miniSysYParser.UnaryOpContext ctx) {
        return super.visitUnaryOp(ctx);
    }

    @Override
    public Void visitFuncRParams(miniSysYParser.FuncRParamsContext ctx) {
        return super.visitFuncRParams(ctx);
    }

    @Override
    public Void visitParam(miniSysYParser.ParamContext ctx) {
        return super.visitParam(ctx);
    }

    @Override
    public Void visitMulExp(miniSysYParser.MulExpContext ctx) {
        return super.visitMulExp(ctx);
    }

    @Override
    public Void visitMulOp(miniSysYParser.MulOpContext ctx) {
        return super.visitMulOp(ctx);
    }

    @Override
    public Void visitAddExp(miniSysYParser.AddExpContext ctx) {
        return super.visitAddExp(ctx);
    }

    @Override
    public Void visitAddOp(miniSysYParser.AddOpContext ctx) {
        return super.visitAddOp(ctx);
    }

    @Override
    public Void visitRelExp(miniSysYParser.RelExpContext ctx) {
        return super.visitRelExp(ctx);
    }

    @Override
    public Void visitRelOp(miniSysYParser.RelOpContext ctx) {
        return super.visitRelOp(ctx);
    }

    @Override
    public Void visitEqExp(miniSysYParser.EqExpContext ctx) {
        return super.visitEqExp(ctx);
    }

    @Override
    public Void visitEqOp(miniSysYParser.EqOpContext ctx) {
        return super.visitEqOp(ctx);
    }

    @Override
    public Void visitLAndExp(miniSysYParser.LAndExpContext ctx) {
        return super.visitLAndExp(ctx);
    }

    @Override
    public Void visitLOrExp(miniSysYParser.LOrExpContext ctx) {
        return super.visitLOrExp(ctx);
    }

    @Override
    public Void visitConstExp(miniSysYParser.ConstExpContext ctx) {
        return super.visitConstExp(ctx);
    }
}
