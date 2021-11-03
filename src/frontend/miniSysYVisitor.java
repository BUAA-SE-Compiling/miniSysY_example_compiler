package frontend;// Generated from miniSysY.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link miniSysYParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface miniSysYVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(miniSysYParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#compUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompUnit(miniSysYParser.CompUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(miniSysYParser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#constDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstDecl(miniSysYParser.ConstDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#bType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBType(miniSysYParser.BTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#constDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstDef(miniSysYParser.ConstDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#constInitVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstInitVal(miniSysYParser.ConstInitValContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(miniSysYParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#varDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDef(miniSysYParser.VarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#initVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitVal(miniSysYParser.InitValContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#funcDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDef(miniSysYParser.FuncDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#funcType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncType(miniSysYParser.FuncTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#funcFParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncFParams(miniSysYParser.FuncFParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#funcFParam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncFParam(miniSysYParser.FuncFParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(miniSysYParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#blockItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockItem(miniSysYParser.BlockItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(miniSysYParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#assignStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStmt(miniSysYParser.AssignStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#expStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpStmt(miniSysYParser.ExpStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#conditionStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionStmt(miniSysYParser.ConditionStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#whileStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmt(miniSysYParser.WhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#breakStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStmt(miniSysYParser.BreakStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#continueStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStmt(miniSysYParser.ContinueStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#returnStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(miniSysYParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(miniSysYParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond(miniSysYParser.CondContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#lVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLVal(miniSysYParser.LValContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#primaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExp(miniSysYParser.PrimaryExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(miniSysYParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#intConst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntConst(miniSysYParser.IntConstContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#unaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExp(miniSysYParser.UnaryExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#callee}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallee(miniSysYParser.CalleeContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#unaryOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOp(miniSysYParser.UnaryOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#funcRParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncRParams(miniSysYParser.FuncRParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(miniSysYParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#mulExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulExp(miniSysYParser.MulExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#mulOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulOp(miniSysYParser.MulOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#addExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExp(miniSysYParser.AddExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#addOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddOp(miniSysYParser.AddOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#relExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelExp(miniSysYParser.RelExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#relOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelOp(miniSysYParser.RelOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#eqExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqExp(miniSysYParser.EqExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#eqOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqOp(miniSysYParser.EqOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#lAndExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLAndExp(miniSysYParser.LAndExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#lOrExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLOrExp(miniSysYParser.LOrExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#constExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstExp(miniSysYParser.ConstExpContext ctx);
}