package frontend;

import frontend.miniSysYParser.BlockContext;
import frontend.miniSysYParser.CompUnitContext;
import frontend.miniSysYParser.FuncDefContext;
import frontend.miniSysYParser.FuncTypeContext;
import frontend.miniSysYParser.IdentContext;
import frontend.miniSysYParser.IntConstContext;
import frontend.miniSysYParser.NumberContext;
import frontend.miniSysYParser.ProgramContext;
import frontend.miniSysYParser.StmtContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class Visitor implements miniSysYVisitor<Void> {

  @Override
  public Void visitProgram(ProgramContext ctx) {
    return null;
  }

  @Override
  public Void visitCompUnit(CompUnitContext ctx) {
    return null;
  }

  @Override
  public Void visitFuncDef(FuncDefContext ctx) {
    return null;
  }

  @Override
  public Void visitFuncType(FuncTypeContext ctx) {
    return null;
  }

  @Override
  public Void visitIdent(IdentContext ctx) {
    return null;
  }

  @Override
  public Void visitBlock(BlockContext ctx) {
    return null;
  }

  @Override
  public Void visitStmt(StmtContext ctx) {
    return null;
  }

  @Override
  public Void visitNumber(NumberContext ctx) {
    return null;
  }

  @Override
  public Void visitIntConst(IntConstContext ctx) {
    return null;
  }

  @Override
  public Void visit(ParseTree parseTree) {
    return null;
  }

  @Override
  public Void visitChildren(RuleNode ruleNode) {
    return null;
  }

  @Override
  public Void visitTerminal(TerminalNode terminalNode) {
    return null;
  }

  @Override
  public Void visitErrorNode(ErrorNode errorNode) {
    return null;
  }
}
