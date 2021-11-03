package frontend;

import ir.Value;

public class Visitor extends miniSysYBaseVisitor<Value>{
    @Override
    public Value visitProgram(miniSysYParser.ProgramContext ctx) {
        return super.visitProgram(ctx);
    }

    @Override
    public Value visitCompUnit(miniSysYParser.CompUnitContext ctx) {
        return super.visitCompUnit(ctx);
    }

    @Override
    public Value visitDecl(miniSysYParser.DeclContext ctx) {
        return super.visitDecl(ctx);
    }

    @Override
    public Value visitConstDecl(miniSysYParser.ConstDeclContext ctx) {
        return super.visitConstDecl(ctx);
    }

    @Override
    public Value visitBType(miniSysYParser.BTypeContext ctx) {
        return super.visitBType(ctx);
    }

    @Override
    public Value visitConstDef(miniSysYParser.ConstDefContext ctx) {
        return super.visitConstDef(ctx);
    }

    @Override
    public Value visitConstInitVal(miniSysYParser.ConstInitValContext ctx) {
        return super.visitConstInitVal(ctx);
    }

    @Override
    public Value visitVarDecl(miniSysYParser.VarDeclContext ctx) {
        return super.visitVarDecl(ctx);
    }

    @Override
    public Value visitVarDef(miniSysYParser.VarDefContext ctx) {
        return super.visitVarDef(ctx);
    }

    @Override
    public Value visitInitVal(miniSysYParser.InitValContext ctx) {
        return super.visitInitVal(ctx);
    }

    @Override
    public Value visitFuncDef(miniSysYParser.FuncDefContext ctx) {
        return super.visitFuncDef(ctx);
    }

    @Override
    public Value visitFuncType(miniSysYParser.FuncTypeContext ctx) {
        return super.visitFuncType(ctx);
    }

    @Override
    public Value visitFuncFParams(miniSysYParser.FuncFParamsContext ctx) {
        return super.visitFuncFParams(ctx);
    }

    @Override
    public Value visitFuncFParam(miniSysYParser.FuncFParamContext ctx) {
        return super.visitFuncFParam(ctx);
    }

    @Override
    public Value visitBlock(miniSysYParser.BlockContext ctx) {
        return super.visitBlock(ctx);
    }

    @Override
    public Value visitBlockItem(miniSysYParser.BlockItemContext ctx) {
        return super.visitBlockItem(ctx);
    }

    @Override
    public Value visitStmt(miniSysYParser.StmtContext ctx) {
        return super.visitStmt(ctx);
    }

    @Override
    public Value visitAssignStmt(miniSysYParser.AssignStmtContext ctx) {
        return super.visitAssignStmt(ctx);
    }

    @Override
    public Value visitExpStmt(miniSysYParser.ExpStmtContext ctx) {
        return super.visitExpStmt(ctx);
    }

    @Override
    public Value visitConditionStmt(miniSysYParser.ConditionStmtContext ctx) {
        return super.visitConditionStmt(ctx);
    }

    @Override
    public Value visitWhileStmt(miniSysYParser.WhileStmtContext ctx) {
        return super.visitWhileStmt(ctx);
    }

    @Override
    public Value visitBreakStmt(miniSysYParser.BreakStmtContext ctx) {
        return super.visitBreakStmt(ctx);
    }

    @Override
    public Value visitContinueStmt(miniSysYParser.ContinueStmtContext ctx) {
        return super.visitContinueStmt(ctx);
    }

    @Override
    public Value visitReturnStmt(miniSysYParser.ReturnStmtContext ctx) {
        return super.visitReturnStmt(ctx);
    }

    @Override
    public Value visitExp(miniSysYParser.ExpContext ctx) {
        return super.visitExp(ctx);
    }

    @Override
    public Value visitCond(miniSysYParser.CondContext ctx) {
        return super.visitCond(ctx);
    }

    @Override
    public Value visitLVal(miniSysYParser.LValContext ctx) {
        return super.visitLVal(ctx);
    }

    @Override
    public Value visitPrimaryExp(miniSysYParser.PrimaryExpContext ctx) {
        return super.visitPrimaryExp(ctx);
    }

    @Override
    public Value visitNumber(miniSysYParser.NumberContext ctx) {
        return super.visitNumber(ctx);
    }

    @Override
    public Value visitIntConst(miniSysYParser.IntConstContext ctx) {
        return super.visitIntConst(ctx);
    }

    @Override
    public Value visitUnaryExp(miniSysYParser.UnaryExpContext ctx) {
        return super.visitUnaryExp(ctx);
    }

    @Override
    public Value visitCallee(miniSysYParser.CalleeContext ctx) {
        return super.visitCallee(ctx);
    }

    @Override
    public Value visitUnaryOp(miniSysYParser.UnaryOpContext ctx) {
        return super.visitUnaryOp(ctx);
    }

    @Override
    public Value visitFuncRParams(miniSysYParser.FuncRParamsContext ctx) {
        return super.visitFuncRParams(ctx);
    }

    @Override
    public Value visitParam(miniSysYParser.ParamContext ctx) {
        return super.visitParam(ctx);
    }

    @Override
    public Value visitMulExp(miniSysYParser.MulExpContext ctx) {
        return super.visitMulExp(ctx);
    }

    @Override
    public Value visitMulOp(miniSysYParser.MulOpContext ctx) {
        return super.visitMulOp(ctx);
    }

    @Override
    public Value visitAddExp(miniSysYParser.AddExpContext ctx) {
        return super.visitAddExp(ctx);
    }

    @Override
    public Value visitAddOp(miniSysYParser.AddOpContext ctx) {
        return super.visitAddOp(ctx);
    }

    @Override
    public Value visitRelExp(miniSysYParser.RelExpContext ctx) {
        return super.visitRelExp(ctx);
    }

    @Override
    public Value visitRelOp(miniSysYParser.RelOpContext ctx) {
        return super.visitRelOp(ctx);
    }

    @Override
    public Value visitEqExp(miniSysYParser.EqExpContext ctx) {
        return super.visitEqExp(ctx);
    }

    @Override
    public Value visitEqOp(miniSysYParser.EqOpContext ctx) {
        return super.visitEqOp(ctx);
    }

    @Override
    public Value visitLAndExp(miniSysYParser.LAndExpContext ctx) {
        return super.visitLAndExp(ctx);
    }

    @Override
    public Value visitLOrExp(miniSysYParser.LOrExpContext ctx) {
        return super.visitLOrExp(ctx);
    }

    @Override
    public Value visitConstExp(miniSysYParser.ConstExpContext ctx) {
        return super.visitConstExp(ctx);
    }
}
