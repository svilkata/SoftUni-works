package implementations;

import interfaces.Solvable;

import java.util.ArrayDeque;

public class BalancedParentheses implements Solvable {
    private String parentheses;

    public BalancedParentheses(String parentheses) {
        this.parentheses = parentheses;
    }

    @Override
    public Boolean solve() {
        String input = this.parentheses;
        java.util.ArrayDeque<Character> openBrackets = new ArrayDeque<>();
        boolean areBalanced = false;

        if (input.length() % 2 == 1) {
            return false;
        }


        for (int index = 0; index < input.length(); index++) {
            char currentBracket = input.charAt(index);
            if (currentBracket == '(' || currentBracket == '{' || currentBracket == '[') {
                openBrackets.push(currentBracket);
            } else if ((currentBracket == ')' || currentBracket == '}' || currentBracket == ']') && !openBrackets.isEmpty()) {
                char lastOpenBracket = openBrackets.pop();
                if (lastOpenBracket == '(' && currentBracket == ')') {
                    areBalanced = true;
                } else if (lastOpenBracket == '{' && currentBracket == '}') {
                    areBalanced = true;
                } else if (lastOpenBracket == '[' && currentBracket == ']') {
                    areBalanced = true;
                }
                else {
                    //небалансирани
                    areBalanced = false;
                    break;
                }
            } else {
                //небалансирани
                areBalanced = false;
                break;
            }
        }

        return areBalanced;
    }
}
