/*!
 * 逐行读取源代码，以供执行词法分析
 */

package day3;

import javax.swing.*;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    //正则表达式 匹配 最开始所有空格、注释、数值、字符串和标识符 理解有难度，具体可看day3.md笔记
    public static String regexPat
            = "\\s*((//.*)|([0-9]+)|(\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")|[A-Z_a-z][A-Z_a-z0-9]*|==|<=|>=|&&|\\|\\||\\p{Punct})?";
    Pattern p = Pattern.compile(regexPat);                  //使用java中的正则表达式模块
    private ArrayList<Token> queue = new ArrayList<Token>();    //队列，用于存放获取用户输入的所有字符 存放的是Token对象类型
    private boolean hasMore;                                //如果本行有字符，hasMore为true；没有字符为false
    private LineNumberReader reader;

    /**
     * @author 高志远
     * @methodsName Lexer
     * @desscription 构造函数 为了初始化 hasMore 和 reader 对象
     * @param r
     */
    public Lexer(Reader r) {
        reader = new LineNumberReader(r);
        hasMore = true;
    }

    /**
     * @author:  高志远
     * @methodsName: read
     * @description: 主函数调用窗口后，写入的所有字符都会传到本函数，然后会判断队列是否存在
     * @param:
     * @return:
     * @throws:
     */
    public Token read () {
        //参数0表示读取第一行
        if (fillQueue(0)) {
            //传给 fillQueue 方法中的 readLine 方法后，没有问题后。可以从队列中弹出
            return queue.remove(0);
        } else {
            return Token.EOF;
        }
    }

    /**
     * @author:  高志远
     * @methodsName: fillQueue
     * @description: 首先判断行号是否大于队列长度，如果小于队列长度并且一行中存在字符的话，会交由 readLine() 方法处理一行中的所有字符。
     * @param:  i: 读取的行号
     * @return:  boolean
     * @throws:
     */
    boolean fillQueue(int i) {
        //i如果大于等于队列的长度，就会一直卡在这里
        while (i >= queue.size()) {
            //如果有字符
            if (hasMore) {
                //传给 readLine() 函数读取一行所有字符
                readLine();
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * @auctor:  高志远
     * @methodsName:  readLine
     * @description:  读取一行所有字符、遍历整行匹配并发送到 addToken() 方法。
     * @param:
     * @return:  void
     * @throws:
     */
    public void readLine() {
        //先定义一个变量，用于处理该函数需要处理的，这一行的所有字符。
        String line = "";
        try {
            //获取到一行中所有的字符 这里会去调用 CodeDialog 类
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //如果刚刚获取到的这行是空的话，改 hasMore 的标志为 false。
        if (line == null) {
            hasMore = false;

        }

        /* 如果没有上面异常的情况，开始正了八经进行匹配 */
        int lineNo = reader.getLineNumber();    //获取行号
        Matcher matcher = p.matcher(line);
        //忽略“\b”之类的标识符
        matcher.useTransparentBounds(true);
        //因为默认情况下，匹配器使用锚定区域边界。所以这里需要禁止掉
        matcher.useAnchoringBounds(false);
        //遍历整行匹配
        int pos = 0;                            //pos是游标
        int endPos = line.length();
        while (pos < endPos) {
            //设置此匹配器的匹配区域。范围：[pos,endPos)
            matcher.region(pos, endPos);
            //lookingAt()方法：匹配成功则返回 true
            if (matcher.lookingAt()) {
                //通过addToken函数加到Token类中
                addToken(lineNo, matcher);
                //pos游标移动，准备往后匹配
                pos = matcher.end();
            }
        }
        //加到队列中
        queue.add(new IdToken(lineNo, Token.EOL));
    }

    /**
     * @auctor:  高志远
     * @methodsName:  addToken
     * @description:  把一行源代码先初步筛选大的类别：标识符、整型字面量和字符串。
     * @param:
     * @return:
     * @throws:
     */
    void addToken(int lineNo, Matcher matcher) {
        //获取匹配的类型
        //当正则表达式包含多个group时,也就是含有多个’(pattern)’格式的子表达式时,它的分组索引(group number)是从1开始的,而group(0)代表了整个匹配的字符串.
        //标识符
        Token token;
        if ((matcher.group(1) != null) && (matcher.group(2) == null) &&  (matcher.group(3) == null) && (matcher.group(4) == null)) {
            token = new IdToken(lineNo, matcher.group(1));
            queue.add(token);
        }
        //整型字面量
        if ((matcher.group(1) != null) && (matcher.group(2) == null) && ( matcher.group(3) != null) && (matcher.group(4)) == null) {
            token = new NumToken(lineNo, Integer.parseInt(matcher.group(1)));
            queue.add(token);
        }
        //字符串
        if ((matcher.group(1) != null) && (matcher.group(2) == null) &&  (matcher.group(3) == null) && (matcher.group(4)) != null) {
            token = new StrToken(lineNo, toStringLiteral(matcher.group(1)));
            queue.add(token);
        }
    }

    /**
     * @auctor:  高志远
     * @methodsName:  toStringLiteral
     * @description:  对字符串字面量做更细的筛选。
     * @param:
     * @return:
     * @throws:
     */
    private String toStringLiteral(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length() - 1;
        for (int i = 1; i < len; i++) {
            char c = s.charAt(i);
            if (c == '\\' && i + 1 < len) {
                int c2 = s.charAt(i + 1);
                if (c2 == '"' || c2 == '\\')
                    c = s.charAt(++i);
                else if (c2 == 'n') {
                    ++i;
                    c = '\n';
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /* 重写Token类中的方法 */
    /**
     * @author: 高志远
     * @className: IdToken
     * @packageName: day3
     * @description: 三个类分别都是Token的子类，通过子类中的重写函数，对外提供getText()方法供主函数文件使用。
     * @data:
     **/
    class IdToken extends Token {
        private String text;
        IdToken(int lineNo, String s) {
            super(lineNo);
            text = s;
        }
        @Override
        public boolean isIdentifier() {
            return true;
        }
        public String getText() {
            return text;
        }
    }

    /**
     * @author: 高志远
     * @className: NumToken
     * @packageName: day3
     * @description: 三个类分别都是Token的子类，通过子类中的重写函数，对外提供getText()方法供主函数文件使用。
     * @data:
     **/
    class NumToken extends Token {
        int text;
        private int integer;
        NumToken(int lineNo, int integer) {
            super(lineNo);
            text = integer;
        }

        @Override
        public boolean isNumber() {
            return true;
        }
        public String getText() {
            return Integer.toString(text);
        }

        @Override
        public int getNumber() {
            return text;
        }
    }

    /**
     * @author: 高志远
     * @className: StrToken
     * @packageName: day3
     * @description: 三个类分别都是Token的子类，通过子类中的重写函数，对外提供getText()方法供主函数文件使用。
     * @data:
     **/
    class StrToken extends Token {
        String text;
        private String str;
        StrToken(int lineNo, String str) {
            super(lineNo);
            text = str;
        }

        @Override
        public boolean isString() {
            return true;
        }
        public String getText() {
            return text;
        }
    }
}