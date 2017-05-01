package string_tools;

import java.math.BigInteger;

/**
 * Created by matoran on 5/1/17.
 */
public class RabinKarp {
    public final int BASE = 128;
    public long MOD;

    public long mod (long x, long y) {
        long result = x % y;
        return result < 0 ? result + y : result;
    }

    public RabinKarp(String pattern) {
        MOD = BigInteger.valueOf(BASE).nextProbablePrime().longValue();
        long hash = hash(pattern);
        System.out.println(BASE + " " + MOD + " " + hash);
    }

    public RabinKarp(String text, String pattern) {
        if(text.length() < pattern.length())
            return;
        MOD = BigInteger.valueOf(Math.max(BASE, text.length())).nextProbablePrime().longValue();
        long p = hash(pattern) % MOD;
        int m = pattern.length();
        long t = hash(text.substring(0, m))  % MOD;
        int count = 0;
        StringBuilder occurences = new StringBuilder();
        for (int s = 0; s < text.length()-m; s++) {
            if(p == t){
                if(pattern.equals(text.substring(s, s+m))){
                    occurences.append(s).append(" ");
                    count++;
                }
            }
            t = rollingHash(t, text.charAt(s), text.charAt(m+s), m);
        }
        System.out.println(count); // nombre d'occurences du motifs
        System.out.println(occurences); //liste des positions du motif
    }

    public long hash(String motif) {
        int p = 0;
        int b = 1;
        for (int i = motif.length()-1; i >= 0; i--) {
            p += motif.charAt(i)*b;
            p %= MOD;
            b *= BASE;
            b %= MOD;
        }
        return p;
    }

    public long rollingHash(long val, int previous, int current, int m){
        long pow = BigInteger.valueOf(BASE).modPow(BigInteger.valueOf(m-1), BigInteger.valueOf(MOD)).longValue();
        return mod(BASE*(val-previous*pow)+current, MOD);
    }
}
