package day5;

import day4.ASTList;
import day4.ASTree;

import java.util.List;

/**
 * eg：while i < 10 {
 *          ... ...
 *     }
 *
 * condition：i < 10
 * body：    {
 *            ... ...
 *       }
 */
public class WhileStmnt extends ASTList {
    public WhileStmnt(List<ASTree> list) {
        super(list);
    }
    // 2. 然后再写获取孩子节点的方法
    ASTree condition() {
        return child(0);
    }
    ASTree body() {
        return child(1);
    }

    @Override
    public String toString() {
        // 1. 期望输出的格式，看书P60页。我们先写这个方法。
        return "(while" + condition() + body() + ")";
    }
}
