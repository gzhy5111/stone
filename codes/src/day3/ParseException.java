/**
 * 该异常处理是在day5的时候，引入了Parser库，Parser库会抛异常，所以现在，我们不得不对异常进行处理了。
 */
package day3;

import javax.naming.ldap.PagedResultsControl;

public class ParseException extends Exception {
    public ParseException(String message, Token token) {
        System.out.println("语法错误！报错信息是：" + message + "Token对象是" + token);
    }
    public ParseException(Token token) {
        System.out.println("有错误！Token对象是" + token);
    }
}
