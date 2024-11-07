package com.example.cryptoanalyzer.services;

import java.util.Arrays;
import java.util.List;

public class EncryptDecrypt {
    private static final List<Character> ALPHABET = Arrays.asList('а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' ');

    public static List getALPHABET() {
        return ALPHABET;
    }

    public static String encrypt(String text, int key) {
        return processText(text, key);
    }

    public static String decrypt(String text, int key) {
        return processText(text, -key);
    }

    public static String processText(String text, int key) {
        StringBuilder stringBuilder = new StringBuilder(text.length());

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (ALPHABET.contains(c)) {
                int index = ALPHABET.indexOf(c);
                int newIndex = (index + key + ALPHABET.size()) % ALPHABET.size();
                if (newIndex < 0) newIndex = newIndex + ALPHABET.size();
                stringBuilder.append(ALPHABET.get(newIndex));
            } else stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }
}

