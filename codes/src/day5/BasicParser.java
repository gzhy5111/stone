package day5;

import day3.Lexer;
import day3.ParseException;
import day3.Token;
import day4.ASTree;
import day4.BinatyExpr;
import day4.Name;
import day4.NumberLiteral;
// 导入作者写的名为 Parser库
import java.util.HashSet;

import static day5.Parser.rule;
import day5.Parser.*;

public class BasicParser {
    // 利用Parser库提供的方法，实现书本上 代码清单5.1 提供的扩展的巴科斯范式。
    Operators operators = new Operators();                 // 运算符
    HashSet<String> reserved = new HashSet<String>();
    Parser expr0 = rule();                                               // 因为语法规则是递归的
    //非终结符primary（基本构成元素）
    Parser primary = rule(PrimaryExpr.class).or(
                                rule().sep("(").ast(expr0).sep(")"),
                                rule().number(NumberLiteral.class),
                                rule().identifier(Name.class, reserved),    // 根据接口的要求，发现它需要传两个参数。
                                rule().string(StringLiteral.class));
    // 非终结符因子
    Parser factor = rule().or(
                                rule(NegativeExpr.class).sep("-").ast(primary),
                                primary
                                );
    // 表达式
    Parser expr = expr0.expression(BinatyExpr.class, factor, operators);

    Parser statement0 = rule();                                         // 因为语法规则是递归的
    //语句块
    Parser block = rule(BlockStmnt.class)
            .sep("{").option(statement0)
            .repeat( rule().sep(";", Token.EOL).option(statement0) )
            .sep("}");
    //语句（statement）可以是if语句、while语句或者是最简单的表达式语句（simple）
    Parser simple = rule(PrimaryExpr.class).ast(expr);
    Parser statement = statement0.or(
            rule(IfStmnt.class).sep("if").ast(expr).ast(block).option(rule().sep("else").ast(block)),
            rule(WhileStmnt.class).sep("while").ast(expr).ast(block),
            simple
    );
    //program终结符表示一个完整的语句
    Parser program = rule().or(
            // 或者有语句，或者为空语句
            statement,
            rule(NullStmnt.class)
    ).sep(";", Token.EOL);

    /**
     * 构造方法
     */
    public BasicParser() {
        // 以下三个字符 ; } //n 不应该被语法分析器当作有效内容进行分析。所以我们将他们加到reserved HashSet中保存。
        reserved.add(";");
        reserved.add("}");
        reserved.add(Token.EOL);

        // 运算符表，第一个参数表示优先级，数值越大优先级越高。
        // 该表摘抄自 https://blog.csdn.net/sunshihua12829/article/details/47912123。只实现了部分
        operators.add("=", 1, Parser.Operators.RIGHT);
        operators.add("==", 2, Parser.Operators.LEFT);
        operators.add(">", 2, Parser.Operators.LEFT);
        operators.add("<", 2, Parser.Operators.LEFT);
        operators.add("+", 3, Parser.Operators.LEFT);
        operators.add("-", 3, Parser.Operators.LEFT);
        operators.add("*", 4, Parser.Operators.LEFT);
        operators.add("/", 4, Parser.Operators.LEFT);
        operators.add("%", 4, Parser.Operators.LEFT);
    }

    /**
     * 接收主函数传过来的Token文本，移交给day3写的词法分析器先做处理。
     */
    public ASTree parse(Lexer lexer) throws ParseException{
        return program.parse(lexer);
    }
}

