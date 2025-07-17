package com.eVision.file_matcher.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class FileUtils {

    public static Set<String> extractWords(File file) {
        Set<String> words = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] tokens = line.split("[^A-Za-z]+");
                for (String token : tokens) {
                    if (!token.isBlank()) {
                        words.add(token.toLowerCase());
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading file: " + file.getName(), e);
        }

        return words;
    }
}
