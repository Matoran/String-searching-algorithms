package string_tools;

/**
 * Created by matoran on 5/1/17.
 */
public class KnutMorrisPratt {
    char m[];
    int pi[];

    public KnutMorrisPratt(String pattern){
        m = new char[pattern.length()];
        pi = new int[pattern.length()];

        int maxK = 0;
        int cpt = 0;
        for (int i = 0; i < m.length; i++){
            m[i] = pattern.charAt(i);
            for(int k = 0; k < i; k++){
                for(int j = 0; j <= k; j++){
                    if(m[j] == pattern.charAt(i-k+j)){
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
                q = q+1;
            }

            if(q == nbM){
                count++;
                occurences.append(i-nbM+1).append(" ");
                q = pi[q-1];
            }
        }
        System.out.println(count);
        System.out.println(occurences);
    }

    public void displayPi(){
        for (int i : pi) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
