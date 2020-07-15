/**
 * BinatyExpr：二元表达式
 * 其中包含：左子树、右子树和运算符。
 * eg：  2 + 3
 *       BinatyExpr中会有三个信息。
 */
package day4;

import java.util.ArrayList;
import java.util.List;

public class BinatyExpr extends ASTList {
    public BinatyExpr(ArrayList<ASTree> children) {
        super(children);
    }

    /**
     * 左子树
      */
    ASTree left() {
        return child(0);
    }

    /**
     * 运算符
     * @return
     */
    ASTree operator() {
        return child(1);
    }

    /**
     * 右子树
     * @return
     */
    ASTree right() {
        return child(2);
    }
}
