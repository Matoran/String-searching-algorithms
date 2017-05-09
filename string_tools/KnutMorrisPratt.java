package string_tools;

/**
 * @author ISELI Cyril & RODRIGUES Marco
 * @version 0.1
 * @date April and May 2017
 * @file KnutMorrisPratt.java
 *
 * Find a pattern in the text with the Knut-Morris-Pratt algorithm
 *
 */
public class KnutMorrisPratt {
    char m[];
    int pi[];

    /**
     * Calculate pi with KnutMorrisPratt algorithm
     * @param pattern
     */
    public KnutMorrisPratt(String pattern){
        m = new char[pattern.length()];
        pi = new int[pattern.length()+1];

        int maxK = 0;
        int cpt = 0;
        pi[0] = 0;
        for (int i = 1; i <= m.length; i++){
            m[i-1] = pattern.charAt(i-1);
            for(int k = 0; k < i-1; k++){
                for(int j = 0; j <= k; j++){
                    if(m[j] == pattern.charAt(i-k+j-1)){
                       cpt++;
                    }
                }
                if(cpt == k+1){
                    maxK = k+1;
                }
                cpt = 0;
            }
            pi[i] = maxK;
            maxK = 0;
        }
    }

    /**
     * Find the pattern in the text
     * @param text
     * @param pattern
     */
    public KnutMorrisPratt(String text, String pattern){
        this(pattern);
        int nbM = pattern.length();
        int q = 0;
        int count = 0;
        StringBuilder occurences = new StringBuilder();
        for(int i = 0; i < text.length(); i++){
            while (q > 0 && (m[q] != text.charAt(i))){
                q = pi[q];
            }

            if(m[q] == text.charAt(i)){
                q++;
            }

            if(q == nbM){
                count++;
                occurences.append(i-nbM+1).append(" ");
                q = pi[q];
            }
        }
        System.out.println(count);
        System.out.println(occurences);
    }

    /**
     * Display pi
     */
    public void display(){
        for (int i = 1; i < pi.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
