
import bridges.base.BinTreeElement;
import bridges.connect.Bridges;
import bridges.validation.RateLimitException;

import java.io.IOException;

public class testSuit {
    public static void main(String[] args) throws RateLimitException, IOException, RateLimitException, IOException {
        Bridges bridges = new Bridges(0, "patelku2", "873014073775");
        bridges.setTitle("");
        bridges.setDescription("");
        System.out.println("Please input a SPACE seperated parenthesised equation, i.e. ( ( 8 ) / ( 2 * (2 + 2) )");
        String input = System.console().readLine();
        BinTreeElement<String> bintree = Project5.buildParseTree(input);
        bridges.setDataStructure(bintree);
        bridges.visualize();
        System.out.println(Project5.evaluate(bintree));
        System.out.println(Project5.getEquation(bintree));
    }
}