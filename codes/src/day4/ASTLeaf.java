/**
 * ASTLeaf类是所有叶子节点的父类
 */
package day4;

import day3.Token;

import java.util.ArrayList;
import java.util.Iterator;

public class ASTLeaf extends ASTree {
    public ASTLeaf(Token token) {
        this.token = token;
    }

    // 初始化一个动态数组，名为empty
    ArrayList<ASTree> empty = new ArrayList<ASTree>();
    // 构造Token的对象token
    Token token;

    /**
     * 叶子节点中不含有子节点，所以子节点个数返回0
     * @return
     */
    int numChildren() {
        return 0;
    }

    /**
     * 因为叶子节点没有子节点，所以我们构造了一个名为empty的ArrayList，返回一个空的迭代器。
     * @return
     */
    Iterator<ASTree> children() {
        return empty.iterator();
    }

    /**
     * 因为叶子节点没有子节点，所以无法返回第i个节点。如果使用了此方法，就会报Exception。
     * @param i
     * @return
     */
    ASTree child(int i) {
        throw new IndexOutOfBoundsException();
    }

    /**
     * 利用token中的getLineNumber方法，获取当前token所在的行数。
     * @return
     */
    @Override
    String location() {
        return "目前所在第 " + token.getLineNumber() + "行";
    }

    /**
     * 从token对象中使用getText()方法，获取值。
     * @return
     */
    public String toString() {
        return token.getText();
    }
}
