package com.eVision.file_matcher;

import com.eVision.file_matcher.service.FileMatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileMatcherApplication  implements CommandLineRunner {

	@Autowired
	private FileMatchingService fileMatchingService;

	public static void main(String[] args) {
		SpringApplication.run(FileMatcherApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		fileMatchingService.matchFiles();
	}
}
