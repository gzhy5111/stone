package day5;

import day4.ASTList;
import day4.ASTree;

import java.util.List;

/**
 * 例如：
 *          if i % 2 == 0 {
 *              even = even + i
 *          } else {
 *              odd = oddd + i
 *          }
 *
 * IfStmnt节点有三个孩子：child(0)表示条件
 *                     child(1)表示  {
 *                                      even = even + i
 *                                   }
 *                     child(2)表示  {
 *                                      odd = oddd + i
 *                                  }
 *
 * 写此种类的时候，因为需要知道语法分析器输出的格式，建议先写 toString 方法以确定输出格式，然后再写上面的获取孩子的三个方法。
 */
public class IfStmnt extends ASTList {
    public IfStmnt(List<ASTree> list) {
        super(list);
    }
    ASTree condition() {
        return child(0);
    }
    ASTree thenBlock() {
        return child(1);
    }
    ASTree elseBlock() {
        return child(2);
    }

    @Override
    public String toString() {
        return "(if" + condition() + thenBlock() + "else" + elseBlock() + ")";
    }
}
