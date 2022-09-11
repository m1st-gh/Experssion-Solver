/**
 * CMSC 256
 * Project 5
 * Patel, Kush
 */
import bridges.base.BinTreeElement;

import java.util.Stack;

public class Project5 {

    /**
    A helper private method to simplify the test for operators into a single expression, isOp.
     */

    private static boolean isOp(String i) {
        return i.equals("+") || i.equals("-") || i.equals("*") || i.equals("/");
    }

    public static bridges.base.BinTreeElement<String> buildParseTree(String expression) {

        Stack<BinTreeElement<String>> treeStack = new Stack<>();
        BinTreeElement<String> currentElement, tree;
        currentElement = tree = new BinTreeElement<>();
        String[] splitExpression = expression.split(" ");
        String token;
        treeStack.push(tree);

        /**
        Iterate through a split string array
         */

        for (int i = 0; i < splitExpression.length; i++) {

            token = splitExpression[i];

            /**
            Checks if the token is a '('
            push the current node to save the parent node
            create a left node then descend the tree to the new left node
             */
            if (token.equals("(")) {
                BinTreeElement<String> leftNode = new BinTreeElement<>();
                treeStack.push(currentElement);
                currentElement.setLeft(leftNode);
                currentElement = leftNode;
            }
            /**
            Checks if the token is an operator
            push the current node to save the parent node
            create a new right node then decent the tree
            ie "( 1 * 2 )" because 1 to the right
            must descend to the right of the op
             */
            else if(isOp(token)){
                currentElement.setValue(token);
                currentElement.setLabel(token);
                BinTreeElement<String> rightNode = new BinTreeElement<>();
                currentElement.setRight(rightNode);
                treeStack.push(currentElement);
                currentElement = rightNode;

            }
            /**
            Checks if the token is a ")"
            pop up to accent the tree to the initial tree element
            because the first element in the array is pointed to "current element"
            current element is the initial tree element
             */
            else if(token.equals(")")){
                if (treeStack.isEmpty()){
                    throw new IllegalArgumentException();
                }
                else
                currentElement = treeStack.pop();
            }
            /**
            Checks for operands
            set current to the token
            then pop to go back up in the tree
             */
            else {
                currentElement.setValue(token);
                currentElement.setLabel(token);
                if (treeStack.isEmpty()) {
                    throw new IllegalArgumentException();
                }
                else {
                    currentElement = treeStack.pop();
                }
            }
        }

        return tree;
    }

    public static double evaluate(bridges.base.BinTreeElement<String> tree) {
        String exp = tree.getValue();
        BinTreeElement<String> left, right;
        /**
        Creating left and right subtrees
         */
        left = tree.getLeft();
        right = tree.getRight();
        double value = 0;
        if (exp == null){
            throw new ArithmeticException();
        }
        /**
        checks the op, then calls on the recursive call on the left and right tree to get their value
         */
        if (isOp(exp) ){
            if(left != null  && right != null){
                double leftValue = evaluate(left);
                double rightValue = evaluate(right);
                switch (exp.charAt(0)) {
                    case '+':
                        value = leftValue + rightValue;
                        break;
                    case '-':
                        value = leftValue - rightValue;
                        break;
                    case '*':
                        value = leftValue * rightValue;
                        break;
                    case '/':
                        /*
                        checks for divide by zero!
                         */
                        if (rightValue == 0) {
                            throw new ArithmeticException();
                        }
                        value = leftValue / rightValue;
                        break;
                }
            }
            else{
                throw new ArithmeticException();
            }
        }
        /**
        if no exp then just return the value of the number.
         */
        else{
            value = Double.parseDouble(tree.getValue());
        }
        return value;
    }

    public static String getEquation(bridges.base.BinTreeElement<String> tree) {
        String exp = tree.getValue();
        BinTreeElement<String> left, right;
        left = tree.getLeft();
        right = tree.getRight();
        String value = "";

        /**
        checks the exp, then the left and right
        calls the recursive call on the left and right tree
        then combines that all into a parenthesized expression
         */
        if (exp == null){
            throw new ArithmeticException();
        }
        if (isOp(exp)) {
            if (left != null && right != null) {
                String leftString, rightString;
                leftString = getEquation(left);
                rightString = getEquation(right);
                value = "( " + leftString + " " + exp + " " + rightString + " )";
            } else {
                throw new ArithmeticException();
            }
        }
        /**
        if the exp is not an op just return the string back
         */
        else{
            value = exp;
        }
        return value;
    }
}

