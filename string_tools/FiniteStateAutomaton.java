package string_tools;

import java.util.HashMap;

/**
 * Created by matoran on 5/1/17.
 */
public class FiniteStateAutomaton {
    int states[][];

    public FiniteStateAutomaton(String pattern) {
        int nb = (int) pattern.chars().distinct().count();
        HashMap<Integer, Integer> letters = new HashMap<>();
        int lettersArray[] = pattern.chars().distinct().sorted().toArray();
        for (int i = 0; i < lettersArray.length; i++) {
            letters.put(lettersArray[i], i);
        }
        states = new int[pattern.length()+1][nb];
        for (int i = 0; i < pattern.length(); i++) {
            int index = letters.get((int)pattern.charAt(i));
            states[i][index] = i+1;
            System.out.println(letters.get((int)pattern.charAt(i)));
        }
        for (int[] state : states) {
            for (int i : state) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public FiniteStateAutomaton(String text, String pattern) {

    }
}
