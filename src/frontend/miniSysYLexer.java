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
		CONST_KW=1, INT_KW=2, VOID_KW=3, IF_KW=4, ELSE_KW=5, WHILE_KW=6, BREAK_KW=7, 
		CONTINUE_KW=8, RETURN_KW=9, IDENT=10, DECIMAL_CONST=11, OCTAL_CONST=12, 
		HEXADECIMAL_CONST=13, STRING=14, PLUS=15, MINUS=16, NOT=17, MUL=18, DIV=19, 
		MOD=20, ASSIGN=21, EQ=22, NEQ=23, LT=24, GT=25, LE=26, GE=27, AND=28, 
		OR=29, L_PAREN=30, R_PAREN=31, L_BRACE=32, R_BRACE=33, L_BRACKT=34, R_BRACKT=35, 
		COMMA=36, SEMICOLON=37, DOUBLE_QUOTE=38, WS=39, LINE_COMMENT=40, MULTILINE_COMMENT=41;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"CONST_KW", "INT_KW", "VOID_KW", "IF_KW", "ELSE_KW", "WHILE_KW", "BREAK_KW", 
			"CONTINUE_KW", "RETURN_KW", "IDENT", "DECIMAL_CONST", "OCTAL_CONST", 
			"HEXADECIMAL_CONST", "STRING", "REGULAR_CHAR", "ESC", "PLUS", "MINUS", 
			"NOT", "MUL", "DIV", "MOD", "ASSIGN", "EQ", "NEQ", "LT", "GT", "LE", 
			"GE", "AND", "OR", "L_PAREN", "R_PAREN", "L_BRACE", "R_BRACE", "L_BRACKT", 
			"R_BRACKT", "COMMA", "SEMICOLON", "DOUBLE_QUOTE", "WS", "LINE_COMMENT", 
			"MULTILINE_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'const'", "'int'", "'void'", "'if'", "'else'", "'while'", "'break'", 
			"'continue'", "'return'", null, null, null, null, null, "'+'", "'-'", 
			"'!'", "'*'", "'/'", "'%'", "'='", "'=='", "'!='", "'<'", "'>'", "'<='", 
			"'>='", "'&&'", "'||'", "'('", "')'", "'{'", "'}'", "'['", "']'", "','", 
			"';'", "'\"'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "CONST_KW", "INT_KW", "VOID_KW", "IF_KW", "ELSE_KW", "WHILE_KW", 
			"BREAK_KW", "CONTINUE_KW", "RETURN_KW", "IDENT", "DECIMAL_CONST", "OCTAL_CONST", 
			"HEXADECIMAL_CONST", "STRING", "PLUS", "MINUS", "NOT", "MUL", "DIV", 
			"MOD", "ASSIGN", "EQ", "NEQ", "LT", "GT", "LE", "GE", "AND", "OR", "L_PAREN", 
			"R_PAREN", "L_BRACE", "R_BRACE", "L_BRACKT", "R_BRACKT", "COMMA", "SEMICOLON", 
			"DOUBLE_QUOTE", "WS", "LINE_COMMENT", "MULTILINE_COMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2+\u011b\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\13\6\13\u0090\n\13\r\13\16\13\u0091\5\13\u0094\n\13\3\f\3\f"+
		"\3\f\6\f\u0099\n\f\r\f\16\f\u009a\5\f\u009d\n\f\3\r\3\r\3\r\6\r\u00a2"+
		"\n\r\r\r\16\r\u00a3\5\r\u00a6\n\r\3\16\3\16\3\16\3\16\5\16\u00ac\n\16"+
		"\3\16\6\16\u00af\n\16\r\16\16\16\u00b0\3\17\3\17\7\17\u00b5\n\17\f\17"+
		"\16\17\u00b8\13\17\3\17\3\17\3\20\3\20\5\20\u00be\n\20\3\21\3\21\3\21"+
		"\3\21\5\21\u00c4\n\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26"+
		"\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\34\3\34"+
		"\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3!\3!\3\"\3\"\3"+
		"#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\6*\u00fd\n*\r*\16*\u00fe"+
		"\3*\3*\3+\3+\3+\3+\7+\u0107\n+\f+\16+\u010a\13+\3+\3+\3,\3,\3,\3,\7,\u0112"+
		"\n,\f,\16,\u0115\13,\3,\3,\3,\3,\3,\4\u00b6\u0113\2-\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\2!\2#\21%\22\'\23"+
		")\24+\25-\26/\27\61\30\63\31\65\32\67\339\34;\35=\36?\37A C!E\"G#I$K%"+
		"M&O\'Q(S)U*W+\3\2\n\5\2C\\aac|\6\2\62;C\\aac|\3\2\63;\3\2\62;\3\2\629"+
		"\5\2\62;CHch\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2\u0126\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+"+
		"\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2"+
		"\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2"+
		"C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3"+
		"\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\3Y\3\2\2\2\5_\3\2\2"+
		"\2\7c\3\2\2\2\th\3\2\2\2\13k\3\2\2\2\rp\3\2\2\2\17v\3\2\2\2\21|\3\2\2"+
		"\2\23\u0085\3\2\2\2\25\u0093\3\2\2\2\27\u009c\3\2\2\2\31\u00a5\3\2\2\2"+
		"\33\u00ab\3\2\2\2\35\u00b2\3\2\2\2\37\u00bd\3\2\2\2!\u00c3\3\2\2\2#\u00c5"+
		"\3\2\2\2%\u00c7\3\2\2\2\'\u00c9\3\2\2\2)\u00cb\3\2\2\2+\u00cd\3\2\2\2"+
		"-\u00cf\3\2\2\2/\u00d1\3\2\2\2\61\u00d3\3\2\2\2\63\u00d6\3\2\2\2\65\u00d9"+
		"\3\2\2\2\67\u00db\3\2\2\29\u00dd\3\2\2\2;\u00e0\3\2\2\2=\u00e3\3\2\2\2"+
		"?\u00e6\3\2\2\2A\u00e9\3\2\2\2C\u00eb\3\2\2\2E\u00ed\3\2\2\2G\u00ef\3"+
		"\2\2\2I\u00f1\3\2\2\2K\u00f3\3\2\2\2M\u00f5\3\2\2\2O\u00f7\3\2\2\2Q\u00f9"+
		"\3\2\2\2S\u00fc\3\2\2\2U\u0102\3\2\2\2W\u010d\3\2\2\2YZ\7e\2\2Z[\7q\2"+
		"\2[\\\7p\2\2\\]\7u\2\2]^\7v\2\2^\4\3\2\2\2_`\7k\2\2`a\7p\2\2ab\7v\2\2"+
		"b\6\3\2\2\2cd\7x\2\2de\7q\2\2ef\7k\2\2fg\7f\2\2g\b\3\2\2\2hi\7k\2\2ij"+
		"\7h\2\2j\n\3\2\2\2kl\7g\2\2lm\7n\2\2mn\7u\2\2no\7g\2\2o\f\3\2\2\2pq\7"+
		"y\2\2qr\7j\2\2rs\7k\2\2st\7n\2\2tu\7g\2\2u\16\3\2\2\2vw\7d\2\2wx\7t\2"+
		"\2xy\7g\2\2yz\7c\2\2z{\7m\2\2{\20\3\2\2\2|}\7e\2\2}~\7q\2\2~\177\7p\2"+
		"\2\177\u0080\7v\2\2\u0080\u0081\7k\2\2\u0081\u0082\7p\2\2\u0082\u0083"+
		"\7w\2\2\u0083\u0084\7g\2\2\u0084\22\3\2\2\2\u0085\u0086\7t\2\2\u0086\u0087"+
		"\7g\2\2\u0087\u0088\7v\2\2\u0088\u0089\7w\2\2\u0089\u008a\7t\2\2\u008a"+
		"\u008b\7p\2\2\u008b\24\3\2\2\2\u008c\u0094\t\2\2\2\u008d\u008f\t\2\2\2"+
		"\u008e\u0090\t\3\2\2\u008f\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u008f"+
		"\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0094\3\2\2\2\u0093\u008c\3\2\2\2\u0093"+
		"\u008d\3\2\2\2\u0094\26\3\2\2\2\u0095\u009d\t\4\2\2\u0096\u0098\t\4\2"+
		"\2\u0097\u0099\t\5\2\2\u0098\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u0098"+
		"\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009d\3\2\2\2\u009c\u0095\3\2\2\2\u009c"+
		"\u0096\3\2\2\2\u009d\30\3\2\2\2\u009e\u00a6\7\62\2\2\u009f\u00a1\7\62"+
		"\2\2\u00a0\u00a2\t\6\2\2\u00a1\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3"+
		"\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a6\3\2\2\2\u00a5\u009e\3\2"+
		"\2\2\u00a5\u009f\3\2\2\2\u00a6\32\3\2\2\2\u00a7\u00a8\7\62\2\2\u00a8\u00ac"+
		"\7z\2\2\u00a9\u00aa\7\62\2\2\u00aa\u00ac\7Z\2\2\u00ab\u00a7\3\2\2\2\u00ab"+
		"\u00a9\3\2\2\2\u00ac\u00ae\3\2\2\2\u00ad\u00af\t\7\2\2\u00ae\u00ad\3\2"+
		"\2\2\u00af\u00b0\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1"+
		"\34\3\2\2\2\u00b2\u00b6\5Q)\2\u00b3\u00b5\5\37\20\2\u00b4\u00b3\3\2\2"+
		"\2\u00b5\u00b8\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b7\u00b9"+
		"\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00ba\5Q)\2\u00ba\36\3\2\2\2\u00bb"+
		"\u00be\5!\21\2\u00bc\u00be\13\2\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00bc\3"+
		"\2\2\2\u00be \3\2\2\2\u00bf\u00c0\7^\2\2\u00c0\u00c4\7$\2\2\u00c1\u00c2"+
		"\7^\2\2\u00c2\u00c4\7^\2\2\u00c3\u00bf\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4"+
		"\"\3\2\2\2\u00c5\u00c6\7-\2\2\u00c6$\3\2\2\2\u00c7\u00c8\7/\2\2\u00c8"+
		"&\3\2\2\2\u00c9\u00ca\7#\2\2\u00ca(\3\2\2\2\u00cb\u00cc\7,\2\2\u00cc*"+
		"\3\2\2\2\u00cd\u00ce\7\61\2\2\u00ce,\3\2\2\2\u00cf\u00d0\7\'\2\2\u00d0"+
		".\3\2\2\2\u00d1\u00d2\7?\2\2\u00d2\60\3\2\2\2\u00d3\u00d4\7?\2\2\u00d4"+
		"\u00d5\7?\2\2\u00d5\62\3\2\2\2\u00d6\u00d7\7#\2\2\u00d7\u00d8\7?\2\2\u00d8"+
		"\64\3\2\2\2\u00d9\u00da\7>\2\2\u00da\66\3\2\2\2\u00db\u00dc\7@\2\2\u00dc"+
		"8\3\2\2\2\u00dd\u00de\7>\2\2\u00de\u00df\7?\2\2\u00df:\3\2\2\2\u00e0\u00e1"+
		"\7@\2\2\u00e1\u00e2\7?\2\2\u00e2<\3\2\2\2\u00e3\u00e4\7(\2\2\u00e4\u00e5"+
		"\7(\2\2\u00e5>\3\2\2\2\u00e6\u00e7\7~\2\2\u00e7\u00e8\7~\2\2\u00e8@\3"+
		"\2\2\2\u00e9\u00ea\7*\2\2\u00eaB\3\2\2\2\u00eb\u00ec\7+\2\2\u00ecD\3\2"+
		"\2\2\u00ed\u00ee\7}\2\2\u00eeF\3\2\2\2\u00ef\u00f0\7\177\2\2\u00f0H\3"+
		"\2\2\2\u00f1\u00f2\7]\2\2\u00f2J\3\2\2\2\u00f3\u00f4\7_\2\2\u00f4L\3\2"+
		"\2\2\u00f5\u00f6\7.\2\2\u00f6N\3\2\2\2\u00f7\u00f8\7=\2\2\u00f8P\3\2\2"+
		"\2\u00f9\u00fa\7$\2\2\u00faR\3\2\2\2\u00fb\u00fd\t\b\2\2\u00fc\u00fb\3"+
		"\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff"+
		"\u0100\3\2\2\2\u0100\u0101\b*\2\2\u0101T\3\2\2\2\u0102\u0103\7\61\2\2"+
		"\u0103\u0104\7\61\2\2\u0104\u0108\3\2\2\2\u0105\u0107\n\t\2\2\u0106\u0105"+
		"\3\2\2\2\u0107\u010a\3\2\2\2\u0108\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109"+
		"\u010b\3\2\2\2\u010a\u0108\3\2\2\2\u010b\u010c\b+\2\2\u010cV\3\2\2\2\u010d"+
		"\u010e\7\61\2\2\u010e\u010f\7,\2\2\u010f\u0113\3\2\2\2\u0110\u0112\13"+
		"\2\2\2\u0111\u0110\3\2\2\2\u0112\u0115\3\2\2\2\u0113\u0114\3\2\2\2\u0113"+
		"\u0111\3\2\2\2\u0114\u0116\3\2\2\2\u0115\u0113\3\2\2\2\u0116\u0117\7,"+
		"\2\2\u0117\u0118\7\61\2\2\u0118\u0119\3\2\2\2\u0119\u011a\b,\2\2\u011a"+
		"X\3\2\2\2\21\2\u0091\u0093\u009a\u009c\u00a3\u00a5\u00ab\u00b0\u00b6\u00bd"+
		"\u00c3\u00fe\u0108\u0113\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}