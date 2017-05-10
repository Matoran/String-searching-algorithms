package string_tools;

import java.util.HashMap;

/**
 * @author ISELI Cyril & RODRIGUES Marco
 * @version 0.1
 * @date April and May 2017
 * @file FiniteStateAutomaton.java
 *
 * Find a pattern in the text with the finite-state automaton algorithm
 */
public class FiniteStateAutomaton {
    private int states[][];
    private HashMap<Integer, Integer> letters = new HashMap<>();

    /**
     * construct states of the automaton
     *
     * @param pattern text to search
     */
    public FiniteStateAutomaton(String pattern) {
        int nb = (int) pattern.chars().distinct().count();
        int lettersArray[] = pattern.chars().distinct().sorted().toArray();
        for (int i = 0; i < lettersArray.length; i++) {
            letters.put(lettersArray[i], i);
        }
        int finalState = pattern.length();
        states = new int[finalState + 1][nb];
        //for all states
        for (int line = 0; line <= finalState; line++) {
            //take every column letter
            for (int column = 0; column < nb; column++) {
                //take previous sub pattern and add one letter
                String subTry = pattern.substring(0, line) + (char) lettersArray[column];
                //compare with actual sub pattern
                int posLastLetter = line + 1 > finalState ? finalState : line + 1;
                String subPattern = pattern.substring(0, posLastLetter);
                //remove end letters until sub pattern have same suffix with sub try
                while (!isSuffix(subPattern, subTry)) {
                    subPattern = pattern.substring(0, --posLastLetter);
                }
                states[line][column] = posLastLetter;
            }
        }
    }

    /**
     * search all occurrences of pattern in text
     *
     * @param text    string that contain text
     * @param pattern text to search
     */
    public FiniteStateAutomaton(String text, String pattern) {
        this(pattern);
        int q = 0;
        int count = 0;
        StringBuilder occurrences = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            //just follow states with letter
            Integer index = letters.get((int) text.charAt(i));
            if (index != null) {
                q = states[q][index];
                //if it last state then one occurrence found
                if (q == pattern.length()) {
                    count++;
                    int s = i - pattern.length() + 1;
                    occurrences.append(s).append(" ");
                }
            } else {
                q = 0;
            }
        }
        System.out.println(count);
        System.out.println(occurrences);
    }

    /**
     * check if a is a suffix of b
     *
     * @param a string to check suffix
     * @param b string to check suffix, length must be lower or equal to a length
     * @return boolean
     */
    private boolean isSuffix(String a, String b) {
        int m = b.length();
        int n = a.length();
        return n <= m && b.substring(m - n).equals(a);
    }

    /**
     * display states
     */
    public void display() {
        for (int[] state : states) {
            for (int value : state) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
