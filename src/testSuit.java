
import bridges.base.BinTreeElement;
import bridges.connect.Bridges;
import bridges.validation.RateLimitException;

import java.io.IOException;

public class testSuit {
    public static void main(String[] args) throws RateLimitException, IOException, RateLimitException, IOException {
        Bridges bridges = new Bridges(0, "patelku2", "873014073775");
        bridges.setTitle("");
        bridges.setDescription("");
        BinTreeElement<String> bintree = Project5.buildParseTree("( ( 72 * 34 ) / ( 8.93 / 3323.99 ) ) ");
        bridges.setDataStructure(bintree);
        bridges.visualize();
        System.out.println(Project5.evaluate(bintree));
        System.out.println(Project5.getEquation(bintree));
    }
}