// Generated from miniSysY.g4 by ANTLR 4.9.2
package frontend;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class miniSysYLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SEMI=1, DECIMAL_CONST=2, OCTAL_CONST=3, HEXADECIMAL_CONST=4, LINE_COMMENT=5, 
		MULTILINE_COMMENT=6, WS=7, LPAREN=8, RPAREN=9, INT=10, MAIN=11, LBRACE=12, 
		RBRACE=13, RETURN=14;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"SEMI", "DECIMAL_CONST", "OCTAL_CONST", "HEXADECIMAL_CONST", "LINE_COMMENT", 
			"MULTILINE_COMMENT", "WS", "LPAREN", "RPAREN", "INT", "MAIN", "LBRACE", 
			"RBRACE", "RETURN"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", null, null, null, null, null, null, "'('", "')'", "'int'", 
			"'main'", "'{'", "'}'", "'return'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SEMI", "DECIMAL_CONST", "OCTAL_CONST", "HEXADECIMAL_CONST", "LINE_COMMENT", 
			"MULTILINE_COMMENT", "WS", "LPAREN", "RPAREN", "INT", "MAIN", "LBRACE", 
			"RBRACE", "RETURN"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public miniSysYLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "miniSysY.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\20v\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\3\3\3\3\3\6\3%\n\3\r"+
		"\3\16\3&\5\3)\n\3\3\4\3\4\3\4\6\4.\n\4\r\4\16\4/\5\4\62\n\4\3\5\3\5\3"+
		"\5\3\5\5\58\n\5\3\5\6\5;\n\5\r\5\16\5<\3\6\3\6\3\6\3\6\7\6C\n\6\f\6\16"+
		"\6F\13\6\3\6\3\6\3\7\3\7\3\7\3\7\7\7N\n\7\f\7\16\7Q\13\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\b\6\bY\n\b\r\b\16\bZ\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3"+
		"\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3O\2\20\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\20\3\2\b\3\2\63;\3\2\62;\3\2\629\5\2\62;CHch\4\2\f\f\17\17\5"+
		"\2\13\f\17\17\"\"\2~\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2"+
		"\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3"+
		"\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\3\37\3\2\2"+
		"\2\5(\3\2\2\2\7\61\3\2\2\2\t\67\3\2\2\2\13>\3\2\2\2\rI\3\2\2\2\17X\3\2"+
		"\2\2\21^\3\2\2\2\23`\3\2\2\2\25b\3\2\2\2\27f\3\2\2\2\31k\3\2\2\2\33m\3"+
		"\2\2\2\35o\3\2\2\2\37 \7=\2\2 \4\3\2\2\2!)\t\2\2\2\"$\t\2\2\2#%\t\3\2"+
		"\2$#\3\2\2\2%&\3\2\2\2&$\3\2\2\2&\'\3\2\2\2\')\3\2\2\2(!\3\2\2\2(\"\3"+
		"\2\2\2)\6\3\2\2\2*\62\7\62\2\2+-\7\62\2\2,.\t\4\2\2-,\3\2\2\2./\3\2\2"+
		"\2/-\3\2\2\2/\60\3\2\2\2\60\62\3\2\2\2\61*\3\2\2\2\61+\3\2\2\2\62\b\3"+
		"\2\2\2\63\64\7\62\2\2\648\7z\2\2\65\66\7\62\2\2\668\7Z\2\2\67\63\3\2\2"+
		"\2\67\65\3\2\2\28:\3\2\2\29;\t\5\2\2:9\3\2\2\2;<\3\2\2\2<:\3\2\2\2<=\3"+
		"\2\2\2=\n\3\2\2\2>?\7\61\2\2?@\7\61\2\2@D\3\2\2\2AC\n\6\2\2BA\3\2\2\2"+
		"CF\3\2\2\2DB\3\2\2\2DE\3\2\2\2EG\3\2\2\2FD\3\2\2\2GH\b\6\2\2H\f\3\2\2"+
		"\2IJ\7\61\2\2JK\7,\2\2KO\3\2\2\2LN\13\2\2\2ML\3\2\2\2NQ\3\2\2\2OP\3\2"+
		"\2\2OM\3\2\2\2PR\3\2\2\2QO\3\2\2\2RS\7,\2\2ST\7\61\2\2TU\3\2\2\2UV\b\7"+
		"\2\2V\16\3\2\2\2WY\t\7\2\2XW\3\2\2\2YZ\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[\\"+
		"\3\2\2\2\\]\b\b\2\2]\20\3\2\2\2^_\7*\2\2_\22\3\2\2\2`a\7+\2\2a\24\3\2"+
		"\2\2bc\7k\2\2cd\7p\2\2de\7v\2\2e\26\3\2\2\2fg\7o\2\2gh\7c\2\2hi\7k\2\2"+
		"ij\7p\2\2j\30\3\2\2\2kl\7}\2\2l\32\3\2\2\2mn\7\177\2\2n\34\3\2\2\2op\7"+
		"t\2\2pq\7g\2\2qr\7v\2\2rs\7w\2\2st\7t\2\2tu\7p\2\2u\36\3\2\2\2\f\2&(/"+
		"\61\67<DOZ\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}