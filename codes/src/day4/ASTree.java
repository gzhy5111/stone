/**
 * ASTree类所有抽象语法树节点的父类
 */
package day4;

import java.util.Iterator;

public abstract class ASTree {
    /**
     * 用于返回第1个子节点
     * @param i
     * @return
     */
    abstract ASTree child(int i);

    /**
     * 返回子节点的个数
     * @return
     */
    abstract int numChildren();

    /**
     * 返回一个遍历所有子节点的迭代器
     * @return
     */
    abstract Iterator<ASTree> children();

    /**
     * 返回一个用于表示抽象语法树节点所在程序内部位置的字符串
     */
    abstract String location();
}
