package day5;

import day3.Token;
import day4.ASTLeaf;
import day4.ASTList;

/**
 * 注意！这里，StringLiteral（字符串常量值）位于语法树的叶子，所以继承ASTLeaf类。
 * 写之前，我们先看下 BasicParser.java ，找到 StringLiteral ，顺藤摸瓜找到 EBNF（扩展的巴科斯范式）。
 * 最后发现，STRING是一个终结符，在 EBNF 中是用大写字母表示的。
 */
public class StringLiteral extends ASTLeaf {
    public StringLiteral(Token token) {
        super(token);
    }
    String value() {                // 不知道该写什么返回类型的时候就点下进去 getText() 方法，看下 getText() 返回的是什么类型。
        return token().getText();
    }
}
