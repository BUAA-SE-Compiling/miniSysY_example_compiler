// Generated from miniSysY.g4 by ANTLR 4.9.2
package frontend;
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
	 * Visit a parse tree produced by {@link miniSysYParser#ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent(miniSysYParser.IdentContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(miniSysYParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link miniSysYParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(miniSysYParser.StmtContext ctx);
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
}