package com.example.cryptoanalyzer.services;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileManager {
    public static String readFile(String filePath) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            while (bufferedReader.ready()) {
                stringBuilder.append(bufferedReader.readLine());
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }

    public static void writeFile(String content, String filePath) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8))) {
            bufferedWriter.write(content);
        }
    }
}
