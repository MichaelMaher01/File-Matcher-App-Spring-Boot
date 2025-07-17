package com.eVision.file_matcher;

import com.eVision.file_matcher.result.FileSimilarityResult;
import com.eVision.file_matcher.service.FileMatchingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileMatchingServiceTest {
    private FileMatchingService fileMatchingService;

    @BeforeEach
    void setup() {
        fileMatchingService = new FileMatchingService();
        ReflectionTestUtils.setField(fileMatchingService, "referenceFilePath", "src/test/resources/reference.txt");
        ReflectionTestUtils.setField(fileMatchingService, "poolDirectoryPath", "src/test/resources/pool");
    }

    @Test
    void testCalculateSimilarities() {
        List<FileSimilarityResult> results = fileMatchingService.calculateSimilarities();

        assertEquals(3, results.size());

        FileSimilarityResult topMatch = results.stream()
                .max((a, b) -> Double.compare(a.similarityPercentage(), b.similarityPercentage()))
                .orElseThrow();

        assertEquals("match.txt", topMatch.fileName());
        assertEquals(100.0, topMatch.similarityPercentage(), 0.01);

        FileSimilarityResult partialMatch = results.stream()
                .filter(r -> r.fileName().equals("partial.txt"))
                .findFirst()
                .orElseThrow();

        assertEquals(50.0, partialMatch.similarityPercentage(), 0.01);
    }
}
