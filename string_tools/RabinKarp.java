package string_tools;

import java.math.BigInteger;

/**
 * @author ISELI Cyril & RODRIGUES Marco
 * @version 0.1
 * @date April and May 2017
 * @file RabinKarp.java
 *
 * Find a pattern in the text with the Rabin-Karp algorithm
 *
 */
public class RabinKarp {
    private final int BASE = 128;
    private long mod;
    private long hash;

    private long mod(long x, long y) {
        long result = x % y;
        return result < 0 ? result + y : result;
    }

    public RabinKarp(String pattern) {
        mod = BigInteger.valueOf(BASE).nextProbablePrime().longValue();
        hash = hash(pattern);
    }

    public RabinKarp(String text, String pattern) {
        if(text.length() < pattern.length())
            return;
        mod = BigInteger.valueOf(Math.max(BASE, text.length())).nextProbablePrime().longValue();
        long p = hash(pattern) % mod;
        int m = pattern.length();
        long t = hash(text.substring(0, m))  % mod;
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

    private long hash(String motif) {
        int p = 0;
        int b = 1;
        for (int i = motif.length()-1; i >= 0; i--) {
            p += motif.charAt(i)*b;
            p %= mod;
            b *= BASE;
            b %= mod;
        }
        return p;
    }

    private long rollingHash(long val, int previous, int current, int m){
        long pow = BigInteger.valueOf(BASE).modPow(BigInteger.valueOf(m-1), BigInteger.valueOf(mod)).longValue();
        return mod(BASE*(val-previous*pow)+current, mod);
    }

    public void display() {
        System.out.println(BASE + " " + mod + " " + hash);
    }
}
