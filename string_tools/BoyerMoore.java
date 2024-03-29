package string_tools;

import java.util.HashMap;

/**
 * @author ISELI Cyril & RODRIGUES Marco
 * @version 0.1
 * @date April and May 2017
 * @file BoyerMoore.java
 *
 * Find a pattern in the text with the Boyer-Moore algorithm
 */
public class BoyerMoore {
    private int wrongLetter[];
    private int suffix[];
    private HashMap<Integer, Integer> letters = new HashMap<>();

    /**
     * construct array of wrong letter, to obtain  the shift number
     * foreach letter shift equals pattern length - last index of the letter
     *
     * @param pattern text to search
     */
    private void constructBoyerMooreWrongLetter(String pattern) {
        int nb = letters.size() + 1;
        //other letters
        wrongLetter = new int[nb];
        wrongLetter[nb - 1] = pattern.length();
        //init array
        for (int i = 0; i < wrongLetter.length - 1; i++) {
            wrongLetter[i] = -1;
        }
        int count = 0;
        for (int i = pattern.length() - 1; i >= 0; i--) {
            Integer posLetter = letters.get((int) pattern.charAt(i));
            //if it's the first time the letter is found
            if (wrongLetter[posLetter] == -1) {
                wrongLetter[posLetter] = pattern.length() - i - 1;
                count++;
            }
            //occurrences if equals number of letters then stop
            if (count == nb - 1) {
                break;
            }
        }
    }

    /**
     * construct shift array from suffix
     * shift equals last match with subpattern with previous character != forbidden
     * Example: ANPANMAN take: MAN forbidden: M subpattern: AN
     * AN found but M = M then continue
     * AN found P != M it's ok
     * Exception if no more occurrences are found then we will take a subpattern of subpattern
     *
     * @param pattern text to search
     */
    private void constructBoyerMooreSuffix(String pattern) {
        suffix = new int[pattern.length()];
        //foreach subpattern
        for (int i = pattern.length() - 1; i >= 1; i--) {
            String subPattern = pattern.substring(i);
            int forbidden = pattern.charAt(i - 1);
            int shift;
            int pos = pattern.length();
            //while subpattern is in pattern and forbidden equals previous character
            do {
                pos = pattern.lastIndexOf(subPattern, pos - 1);
            } while (pos > 0 && pattern.charAt(pos - 1) == forbidden);
            if (pos == -1) {
                //find subpattern suffix equals to pattern prefix
                do {
                    subPattern = subPattern.substring(1);
                } while (!pattern.substring(0, subPattern.length()).equals(subPattern));
                shift = pattern.length() - subPattern.length();
            } else {
                shift = pattern.length() - pos - subPattern.length();
            }
            suffix[pattern.length() - i - 1] = shift;
        }
        //last is always equals to before last
        suffix[pattern.length() - 1] = suffix[pattern.length() - 2];
    }

    /**
     * create arrays for the algorithm
     *
     * @param pattern text to search
     */
    public BoyerMoore(String pattern) {
        int lettersArray[] = pattern.chars().distinct().toArray();
        for (int i = 0; i < lettersArray.length; i++) {
            letters.put(lettersArray[i], i);
        }
        constructBoyerMooreWrongLetter(pattern);
        constructBoyerMooreSuffix(pattern);
    }

    /**
     * search all occurrences of pattern in text
     *
     * @param text string that contain text
     * @param pattern text to search
     */
    public BoyerMoore(String text, String pattern) {
        this(pattern);
        int m = pattern.length();
        int t = text.length();
        int s = m;
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        while (s <= t) {
            //search from the end, the index error
            int j = m;
            while (j > 0 && text.charAt(s - m + j - 1) == pattern.charAt(j - 1)) {
                j--;
            }
            //no error
            if (j == 0) {
                stringBuilder.append(s - m).append(" ");
                count++;
            }
            //error on last letter
            if (j == m) {
                Integer index = letters.get((int) text.charAt(s - 1));
                s += wrongLetter[index == null ? wrongLetter.length - 1 : index];
            } else {
                s += suffix[m - j - 1];
            }
        }
        System.out.println(count);
        System.out.println(stringBuilder);
    }

    /**
     * display arrays of the algorithm
     */
    public void display() {
        for (int i : wrongLetter) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : suffix) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
