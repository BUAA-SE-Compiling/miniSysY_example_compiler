import java.io.IOException;

import emit.EmitLLVM;
import ir.Module;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import frontend.*;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;

public class Compiler {
    //这个编译器仅作为示例用，你可以参考实现架构，但不允许直接抄写代码
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("args are required.");
        }
        var source = args[0];// .sy
      //  var target = args[1];// .ll
        CharStream input = CharStreams.fromFileName(source);//read file context from source
        miniSysYLexer lexer = new miniSysYLexer(input);//生成一个 lexer
        lexer.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                                    int pos, String msg, RecognitionException e) {
                throw new RuntimeException("Lex Error in line: " + line + "pos: " + pos);
            }
        });//给 lexer 加上错误监听
        CommonTokenStream tokens = new CommonTokenStream(lexer);//产生token stream
        miniSysYParser parser = new miniSysYParser(tokens);//将 token stream 作为输入，生成 parser
        parser.setErrorHandler(
                new BailErrorStrategy());//因为我们对错误处理的要求并不高，所以使用 bailErrorStrategy 意思是出现异常以后不进行恢复，仅 throw 一个 runtime exception
        ParseTree tree = parser.program();//解析 tokens, "program"相当于开始符号，在这个解析结束后 parser 会生成一棵完整的 AST，也就四 tree
        Visitor visitor = new Visitor();
        visitor.visit(tree);//通过访问者模式去 "visit" 由 parser 生成的 ast
        var emit = new EmitLLVM();
        emit.run(Module.module);
    }
}
