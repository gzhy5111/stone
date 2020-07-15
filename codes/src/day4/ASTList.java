/**
 * ASTList类是所有非叶子节点的父类
 */
package day4;
import java.util.ArrayList;
import java.util.Iterator;

public class ASTList extends ASTree {
    public ASTList(ArrayList<ASTree> children) {
        this.children = children;
    }

    // 用ArrayList存放子节点
    ArrayList<ASTree> children = new ArrayList<ASTree>();

    /**
     * 返回第i个节点
     * @param i
     * @return
     */
    ASTree child(int i) {
        return children.get(i);
    }

    /**
     * 返回子节点的个数
     * @return
     */
    int numChildren() {
        return children.size();
    }

    /**
     * 返回子结点迭代器
     * @return
     */
    Iterator<ASTree> children() {
        return children.iterator();
    }

    /**
     * 例如输入的是i和=和1，希望输出(i = 1)。即加个括号。
     * @return
     */
    public String toString() {
        // builder对象用于存放临时的东西
        StringBuilder builder = new StringBuilder();
        // 因为要实现加括号，这里是第一步，我先加一个左括号。
        builder.append('(');
        // foreach遍历方法，t是临时变量，遍历所有children动态数组（ArrayList）中的信息。
        for (ASTree t: children) {
            builder.append("");
            builder.append(t.toString());
            builder.append(" ");
        }
        // 最后再加上右括号
        builder.append(')');
        return builder.toString();
    }

    /**
     * 对位置信息进行判断，如果位置不为空就返回；如果位置为null则返回null。
     * @return
     */
    String location() {
        for (ASTree t: children) {
            String s = t.location();
            if (s != null) {
                return s;
            }
        }
        return null;
    }
}
