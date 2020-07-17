package day3;

public class LexerRunner {
    public static void main(String[] args) throws ParseException{
        //System.out.println("hello world!");
        Lexer l = new Lexer(new CodeDialog());
        //进入死循环 当 (t = l.read()) !=Token.EOF 时退出
        //先调用 Lexer 类中的 read() 方法
        for (Token t; (t = l.read()) !=Token.EOF; ) {
            System.out.println("-->" + t.getText());
        }
    }
}
