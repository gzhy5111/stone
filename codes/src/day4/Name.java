package day4;

import day3.Token;

public class Name extends ASTLeaf {
    public Name(Token t) {
        super(t);
    }
    String name() {
        return token.getText();
    }
}
