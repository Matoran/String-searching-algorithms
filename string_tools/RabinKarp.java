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

    /**
     * return modulus not remainder
     * @param number long
     * @param mod long
     * @return long
     */
    private long mod(long number, long mod) {
        long result = number % mod;
        return result < 0 ? result + mod : result;
    }

    /**
     * calc mod and hash
     * @param pattern text to search
     */
    public RabinKarp(String pattern) {
        mod = BigInteger.valueOf(BASE).nextProbablePrime().longValue();
        hash = hash(pattern);
    }

    /**
     * search all occurrences of pattern in text
     *
     * @param text string that contain text
     * @param pattern text to search
     */
    public RabinKarp(String text, String pattern) {
        if(text.length() < pattern.length())
            return;
        //find prime number, not required but coprime required
        mod = BigInteger.valueOf(Math.max(BASE, text.length())).nextProbablePrime().longValue();
        long p = hash(pattern);
        int m = pattern.length();
        long t = hash(text.substring(0, m));
        int count = 0;
        StringBuilder occurrences = new StringBuilder();
        for (int s = 0; s < text.length()-m; s++) {
            //if the hash is the same, check is pattern is equals to subtext
            if(p == t){
                if(pattern.equals(text.substring(s, s+m))){
                    occurrences.append(s).append(" ");
                    count++;
                }
            }
            t = rollingHash(t, text.charAt(s), text.charAt(m+s), m);
        }
        System.out.println(count);
        System.out.println(occurrences);
    }

    /**
     * calc pattern hash
     * @param pattern text to search
     * @return hash
     */
    private long hash(String pattern) {
        int p = 0;
        int b = 1;
        for (int i = pattern.length()-1; i >= 0; i--) {
            p += pattern.charAt(i)*b;
            p %= mod;
            b *= BASE;
            b %= mod;
        }
        return p;
    }

    /**
     * remove previous from val and add current
     * @param val base value
     * @param previous value to be removed
     * @param current value to be added
     * @param m pattern length
     * @return calculated value modulo mod
     */
    private long rollingHash(long val, int previous, int current, int m){
        long pow = BigInteger.valueOf(BASE).modPow(BigInteger.valueOf(m-1), BigInteger.valueOf(mod)).longValue();
        return mod(BASE*(val-previous*pow)+current, mod);
    }

    /**
     * display base, mod and hash of the pattern
     */
    public void display() {
        System.out.println(BASE + " " + mod + " " + hash);
    }
}
