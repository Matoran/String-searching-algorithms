package string_tools;

import java.util.HashMap;

/**
 * Created by matoran on 5/1/17.
 */
public class BoyerMoore {
    private int delta1[];
    private int delta2[];
    private HashMap<Integer, Integer> letters = new HashMap<>();

    public BoyerMoore(String pattern) {
        int lettersArray[] = pattern.chars().distinct().toArray();
        for (int i = 0; i < lettersArray.length; i++) {
            letters.put(lettersArray[i], i);
        }
        int nb = (int)pattern.chars().distinct().count()+1;
        delta1 = new int[nb];
        delta1[nb-1] = pattern.length();
        for (int i = 0; i < delta1.length-1; i++) {
            delta1[i] = -1;
        }
        int count = 0;
        for (int i = pattern.length()-1; i >= 0; i--) {
            Integer posLetter = letters.get((int)pattern.charAt(i));
            if(delta1[posLetter] == -1){
                delta1[posLetter] = pattern.length()-i-1;
                count++;
            }
            if(count == nb-1){
                break;
            }
        }
        System.out.println("delta1");
        for (int i : delta1) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = pattern.length()-1; i >= 1; i--) {
            String subPattern = pattern.substring(i);
            char forbidden = pattern.charAt(i-1);
            int shift = -1;
            int pos = pattern.length();
            while (pos > 0){
                pos = pattern.lastIndexOf(subPattern, pos-1);
                if(pos > 0 && pattern.charAt(pos-1) != forbidden)
                    break;
            }
            if(pos==-1){
                do{
                    if(subPattern.length() > 1)
                        subPattern = subPattern.substring(1);
                    else{
                        shift = pattern.length();
                        break;
                    }
                }while(pattern.indexOf(subPattern) != 0);
                if(shift == -1){
                    shift = pattern.length()-subPattern.length();
                }
            }else{
                shift = pos;
            }
            System.out.print(shift + " ");


        }
        System.out.println();




    }
}
