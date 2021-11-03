// Generated from miniSysY.g4 by ANTLR 4.9.2
package frontend;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link miniSysYVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 *            operations with no return type.
 */
public class miniSysYBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements miniSysYVisitor<T> {
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitProgram(miniSysYParser.ProgramContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitCompUnit(miniSysYParser.CompUnitContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitDecl(miniSysYParser.DeclContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitConstDecl(miniSysYParser.ConstDeclContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitBType(miniSysYParser.BTypeContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitConstDef(miniSysYParser.ConstDefContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitConstInitVal(miniSysYParser.ConstInitValContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitVarDecl(miniSysYParser.VarDeclContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitVarDef(miniSysYParser.VarDefContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitInitVal(miniSysYParser.InitValContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitFuncDef(miniSysYParser.FuncDefContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitFuncType(miniSysYParser.FuncTypeContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitFuncFParams(miniSysYParser.FuncFParamsContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitFuncFParam(miniSysYParser.FuncFParamContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitBlock(miniSysYParser.BlockContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitBlockItem(miniSysYParser.BlockItemContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitStmt(miniSysYParser.StmtContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitAssignStmt(miniSysYParser.AssignStmtContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitExpStmt(miniSysYParser.ExpStmtContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitConditionStmt(miniSysYParser.ConditionStmtContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitWhileStmt(miniSysYParser.WhileStmtContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitBreakStmt(miniSysYParser.BreakStmtContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitContinueStmt(miniSysYParser.ContinueStmtContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitReturnStmt(miniSysYParser.ReturnStmtContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitExp(miniSysYParser.ExpContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitCond(miniSysYParser.CondContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitLVal(miniSysYParser.LValContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitPrimaryExp(miniSysYParser.PrimaryExpContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitNumber(miniSysYParser.NumberContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitIntConst(miniSysYParser.IntConstContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitUnaryExp(miniSysYParser.UnaryExpContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitCallee(miniSysYParser.CalleeContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitUnaryOp(miniSysYParser.UnaryOpContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitFuncRParams(miniSysYParser.FuncRParamsContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitParam(miniSysYParser.ParamContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitMulExp(miniSysYParser.MulExpContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitMulOp(miniSysYParser.MulOpContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitAddExp(miniSysYParser.AddExpContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitAddOp(miniSysYParser.AddOpContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitRelExp(miniSysYParser.RelExpContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitRelOp(miniSysYParser.RelOpContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitEqExp(miniSysYParser.EqExpContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitEqOp(miniSysYParser.EqOpContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitLAndExp(miniSysYParser.LAndExpContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitLOrExp(miniSysYParser.LOrExpContext ctx) {return visitChildren(ctx);}

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitConstExp(miniSysYParser.ConstExpContext ctx) {return visitChildren(ctx);}
}