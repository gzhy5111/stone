package day5;

import day4.ASTList;
import day4.ASTree;

import java.util.List;

/**
 * 本类是抽象语法树的最最基本的节点，位于抽象语法树的叶子。
 */
public class PrimaryExpr extends ASTList {
    //public PrimaryExpr(List<ASTree> list) {
    //    super(list);
    //}
    //ASTree create(List<ASTree> node) {
    //    // 如果只是用下这个类，是往上面摞的，而不需要创建一个PrimaryExpr的话（即传进来的node参数只有一个，那我就返回，让你用好了）
    //    if (node.size() == 1) {
    //        return node.get(0);
    //    }
    //    // 因为这个PrimaryExpr是最最基础的节点，他还需要承担新建节点（添枝增叶）的功能。
    //    else {
    //        return new PrimaryExpr(node);
    //    }
    //}
    public PrimaryExpr(List<ASTree> c) { super(c); }
    public static ASTree create(List<ASTree> c) {
        return c.size() == 1 ? c.get(0) : new PrimaryExpr(c);
    }
}
