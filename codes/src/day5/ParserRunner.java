package day5;

import day3.CodeDialog;
import day3.Lexer;
import day3.ParseException;
import day3.Token;
import day4.ASTree;

public class ParserRunner {
    public static void main(String[] args) throws ParseException {
        // 词法分析器
        Lexer l = new Lexer(new CodeDialog());
        // 语法分析器
        BasicParser basicParser = new BasicParser();
        while (l.peek(0) != Token.EOF) {
            ASTree ast = basicParser.parse(l);              // 构造树，传传去的参数为词法分析器的对象 l
            System.out.println("==>" + ast.toString());     // toString方法可以获取树结构，打印出来。
        }
    }
}
