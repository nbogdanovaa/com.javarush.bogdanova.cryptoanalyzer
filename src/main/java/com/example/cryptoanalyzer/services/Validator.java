package com.example.cryptoanalyzer.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Validator {
    public static void isFileValid(String filePath) throws IOException {
        Path path = Path.of(filePath);
        if (!Files.isRegularFile(path)) throw new IOException("Указанный путь не является файлом.");
        if (Files.size(path) == 0) throw new IOException("Файл пуст.");
        if (!Files.isReadable(path)) throw new IOException("Нет доступа для чтения файла.");
    }
}
