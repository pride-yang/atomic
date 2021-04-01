package top.yang.text;

import org.apache.commons.lang3.CharSetUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CharsetUtil extends CharSetUtils {
    /**
     * ISO-8859-1
     */
    public static final Charset CHARSET_ISO_8859_1 = StandardCharsets.ISO_8859_1;
    /**
     * UTF-8
     */
    public static final Charset CHARSET_UTF_8 = StandardCharsets.UTF_8;
    /**
     * GBK
     */
    public static Charset CHARSET_GBK = Charset.forName("GBK");

    /**
     * 字符常量：空格符 {@code ' '}
     */
    public static final char SPACE = ' ';
    /**
     * 字符常量：制表符 {@code '\t'}
     */
    public static final char TAB = '	';
    /**
     * 字符常量：点 {@code '.'}
     */
    public static final char DOT = '.';
    /**
     * 字符常量：斜杠 {@code '/'}
     */
    public static final char SLASH = '/';
    /**
     * 字符常量：反斜杠 {@code '\\'}
     */
    public static final char BACKSLASH = '\\';
    /**
     * 字符常量：回车符 {@code '\r'}
     */
    public static final char CR = '\r';
    /**
     * 字符常量：换行符 {@code '\n'}
     */
    public static final char LF = '\n';
    /**
     * 字符常量：减号（连接符） {@code '-'}
     */
    public static final char DASHED = '-';
    /**
     * 字符常量：下划线 {@code '_'}
     */
    public static final char UNDERLINE = '_';
    /**
     * 字符常量：逗号 {@code ','}
     */
    public static final char COMMA = ',';
    /**
     * 字符常量：花括号（左） <code>'{'</code>
     */
    public static final char DELIM_START = '{';
    /**
     * 字符常量：花括号（右） <code>'}'</code>
     */
    public static final char DELIM_END = '}';
    /**
     * 字符常量：中括号（左） {@code '['}
     */
    public static final char BRACKET_START = '[';
    /**
     * 字符常量：中括号（右） {@code ']'}
     */
    public static final char BRACKET_END = ']';
    /**
     * 字符常量：双引号 {@code '"'}
     */
    public static final char DOUBLE_QUOTES = '"';
    /**
     * 字符常量：单引号 {@code '\''}
     */
    public static final char SINGLE_QUOTE = '\'';
    /**
     * 字符常量：与 {@code '&'}
     */
    public static final char AMP = '&';
    /**
     * 字符常量：冒号 {@code ':'}
     */
    public static final char COLON = ':';
    /**
     * 字符常量：艾特 {@code '@'}
     */
    public static final char AT = '@';

}