package com.eVision.file_matcher.service;

import com.eVision.file_matcher.result.FileSimilarityResult;
import com.eVision.file_matcher.util.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class FileMatchingService {

    @Value("${reference.file.path}")
    private String referenceFilePath;

    @Value("${pool.directory.path}")
    private String poolDirectoryPath;

    public List<FileSimilarityResult> calculateSimilarities() {
        File referenceFile = new File(referenceFilePath);
        File poolDirectory = new File(poolDirectoryPath);

        if (!referenceFile.exists() || !poolDirectory.exists() || !poolDirectory.isDirectory()) {
            System.out.println("Invalid file or directory path.");
            return Collections.emptyList();
        }

        Set<String> referenceWords = FileUtils.extractWords(referenceFile);
        List<FileSimilarityResult> results = new ArrayList<>();

        for (File file : Objects.requireNonNull(poolDirectory.listFiles())) {
            if (file.isFile()) {
                Set<String> fileWords = FileUtils.extractWords(file);

                double score;

                if (fileWords.equals(referenceWords)) {
                    score = 100.0;
                } else {
                    long commonWords = fileWords.stream()
                            .filter(referenceWords::contains)
                            .count();

                    score = (referenceWords.isEmpty()) ? 0.0 :
                            (commonWords * 100.0 / referenceWords.size());
                }

                results.add(new FileSimilarityResult(file.getName(), score));
            }
        }

        return results;
    }

    public void matchFiles() {
        List<FileSimilarityResult> results = calculateSimilarities();

        results.sort((a, b) -> Double.compare(b.similarityPercentage(), a.similarityPercentage()));

        for (FileSimilarityResult result : results) {
            System.out.printf("File: %s | Score: %.2f%%%n", result.fileName(), result.similarityPercentage());
        }

        results.stream()
                .filter(r -> r.similarityPercentage() > 0)
                .max(Comparator.comparing(FileSimilarityResult::similarityPercentage))
                .ifPresentOrElse(
                        match -> System.out.println("Best match: " + match.fileName()),
                        () -> System.out.println("No matching file found.")
                );
    }


}
