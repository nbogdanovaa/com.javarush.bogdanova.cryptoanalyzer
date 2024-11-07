package com.example.cryptoanalyzer.services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BruteForce {
    private static final Set<String> DICTIONARY = new HashSet<>();

    static {
        loadDictionary("src/main/resources/dictionary.txt");
    }

    private static void loadDictionary(String filePath) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            while (bufferedReader.ready()) {
                DICTIONARY.add(bufferedReader.readLine().trim().toUpperCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String decryptByBruteForce(String text, List alphabet) {
        int maxMatchCount = 0;
        String bestDecryptedText = "";
        for (int i = 0; i < alphabet.size(); i++) {

            String decryptedText = EncryptDecrypt.decrypt(text, i);
            int matchCount = countMatches(decryptedText);

            if (matchCount > maxMatchCount) {
                maxMatchCount = matchCount;
                bestDecryptedText = decryptedText + "\n\n" + "Ключ расшифровки равен " + i + ".";
            }
        }
        return bestDecryptedText;
    }

    private static int countMatches(String text) {
        String[] words = text.split(" ");
        int matchCount = 0;
        for (String word : words) {
            if (DICTIONARY.contains(word.toUpperCase())) matchCount++;
        }
        return matchCount;
    }
}

