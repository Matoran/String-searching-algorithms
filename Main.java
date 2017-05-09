import string_tools.BoyerMoore;
import string_tools.FiniteStateAutomaton;
import string_tools.KnutMorrisPratt;
import string_tools.RabinKarp;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException, NumberFormatException {
        String fileName = null;
        String pattern = null;
        int algo = 0;
        switch (args.length) {
            case 3:
                fileName = args[2];
            case 2:
                algo = Integer.parseInt(args[1]);
                pattern = args[0];
                break;
            default:
                System.err.println("usage: java Main <motif> <algo> (<fichier_texte>)");
                System.exit(1);
        }
        String fileContent = "";

        if(fileName != null){
            fileContent = new String(Files.readAllBytes(Paths.get(fileName)));
        }
        switch (algo) {
            case 1:
                if (fileName == null) {
                    RabinKarp rabinKarp = new RabinKarp(pattern);
                    rabinKarp.display();
                } else {
                    new RabinKarp(fileContent, pattern);
                }
                break;
            case 2:
                if (fileName == null) {
                    FiniteStateAutomaton finiteStateAutomaton = new FiniteStateAutomaton(pattern);
                    finiteStateAutomaton.display();
                } else {
                    new FiniteStateAutomaton(fileContent, pattern);
                }
                break;
            case 3:
                if (fileName == null) {
                    KnutMorrisPratt kmp = new KnutMorrisPratt(pattern);
                    kmp.display();
                } else {
                    new KnutMorrisPratt(fileContent, pattern);
                }
                break;
            case 4:
                if (fileName == null) {
                    BoyerMoore boyerMoore = new BoyerMoore(pattern);
                    boyerMoore.display();
                } else {
                    new BoyerMoore(fileContent, pattern);
                }
                break;
            default:
                System.err.println("Algorithm not implemented");
                System.exit(2);
        }
    }
}











