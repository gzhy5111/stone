package day5;

import day4.ASTList;
import day4.ASTree;

import java.util.List;

/**
 * 对应扩展巴科斯的 factor 规则（具体可看 BasicParser.java）
 * NegativeExpr节点有一个节点，表示符号 —— “-”
 */
public class NegativeExpr extends ASTList {
    public NegativeExpr(List<ASTree> list) {
        super(list);
    }
    ASTree operand() {
        return child(0);                  // 返回 ASTList 中第0个节点（也就是首个）
                                            // 如果是 -y ，返回来的其实就是一个 y
    }

    @Override
    public String toString() {
        return "-" + operand();             // 现在前面加了个“-”，最终返回的就是 -y
    }
}
