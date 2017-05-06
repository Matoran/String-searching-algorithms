package string_tools;

import java.util.HashMap;

/**
 * Created by matoran on 5/1/17.
 */
public class FiniteStateAutomaton {
    int states[][];
    HashMap<Integer, Integer> letters = new HashMap<>();

    public FiniteStateAutomaton(String pattern) {
        int nb = (int) pattern.chars().distinct().count();
        int lettersArray[] = pattern.chars().distinct().sorted().toArray();
        for (int i = 0; i < lettersArray.length; i++) {
            letters.put(lettersArray[i], i);
        }
        int finalState = pattern.length();
        states = new int[finalState+1][nb];

        //for all states
        for (int line = 0; line <= finalState; line++) {
            //take every column letter
            for (int column = 0; column < nb; column++) {
                //take previous sub pattern and add one letter
                String subTry = pattern.substring(0,line) + Character.toString((char)lettersArray[column]);
                //compare with actual sub pattern
                int posLastLetter = line+1 > finalState ? finalState : line+1;
                String subPattern = pattern.substring(0,posLastLetter);
                //remove end letters until sub pattern have same suffix with sub try
                while(!isSuffix(subPattern, subTry)){
                    subPattern = pattern.substring(0,--posLastLetter);
                }
                states[line][column] = posLastLetter;
            }
        }
    }

    public FiniteStateAutomaton(String text, String pattern) {
        this(pattern);
        int q = 0;
        int count = 0;
        StringBuilder occurences = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            Integer index = letters.get((int)text.charAt(i));
            if(index != null){
                q = states[q][index];
                if(q == pattern.length()){
                    count++;
                    int s = i-pattern.length()+1;
                    occurences.append(s).append(" ");
                }
            }else{
                q = 0;
            }
        }
        System.out.println(count);
        System.out.println(occurences);
    }

    public boolean isSuffix(String subPattern, String subTry) {
        int m = subTry.length();
        int n = subPattern.length();
        return n <= m && subTry.substring(m - n).equals(subPattern);
    }
}
