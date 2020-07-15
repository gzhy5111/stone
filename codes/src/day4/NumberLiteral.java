/**
 * 返回数字类型的数值信息。
 * eg： 13 + x * 2
 *     NumberLiter类就是用于处理13和2的。
 * 还需要知道一点就是，NumberLiteral只能属于叶子类。
 */
package day4;

import day3.Token;

public class NumberLiteral extends ASTLeaf{
    // 有参构造函数，用于初始化。
    public NumberLiteral(Token t) {
        // 子类构造函数必须在构造函数第一行调用父类构造函数。
        super(t);
    }

    int value() {
        return token.getNumber();
    }
}
