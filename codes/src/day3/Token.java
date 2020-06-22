/*!
 * 词法分析：得到的字符串不是普通的String类型，而是Token对象
 * Token对象会记录：该单词对应的字符串、单词的类型、单词所处位置的行号等信息
 */
package day3;

public class Token {
    //实际的单词是Token类 子类的对象
    //Token类根据单词的类型 又定义了不同的子类
    //类型有：标识符、整型字面量和字符串字面量
    public static final Token EOF = new Token(-1) {};   //EOF表示程序结束，EOF是对象类型
    public static final String EOL= "\\n";                  //表示换行符 \n ，EOL是String类型
    int lineNumber;                                         //行号

    Token (int line) {
        lineNumber = line;
    }

    //下面的方法都会被重写
    public int getLineNumber() {
        return lineNumber;
    }
    public boolean isIdentifier() {
        return false;
    }
    public boolean isNumber() {
        return false;
    }
    public boolean isString() {
        return false;
    }
    /* 被 Lexer 类中的方法重写后 把结果返回给主函数 */
    public String getText() {
        return "";
    }
}
