/**
 * ASTList类是所有非叶子节点的父类
 */
package day4;
import java.util.Iterator;
import java.util.List;

public class ASTList extends ASTree {
    // 用List存放子节点，注意奥，ASTList是个类，不是对象哦。
    // 作用仅仅是存放某个节点以及这个结点下面的节点。
    protected List<ASTree> children;
    public ASTList(List<ASTree> list) {
        children = list;
    }

    /**
     * 返回第i个节点
     * @param i
     * @return
     */
    public ASTree child(int i) {
        return children.get(i);
    }

    /**
     * 返回子节点的个数
     * @return
     */
    public int numChildren() {
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
        String sep = "";
        // foreach遍历方法，t是临时变量，遍历所有children动态数组（ArrayList）中的信息。
        for (ASTree t: children) {
            builder.append(sep);
            sep = " ";
            builder.append(t.toString());
        }
        // 最后再加上右括号返回
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
