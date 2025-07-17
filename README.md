# File-Matcher-App-Spring-Boot
A performance-efficient Spring Boot application that compares a reference text file with a directory of text files, and calculates the similarity score (based on word matching) for each file.

# Features

- Calculates similarity between a reference file and a pool of files based on words.
-  Ignores word order and is case-insensitive.
-  Only alphabetic words are considered.
-  Exact word match (no more, no less) scores 100%.
-  Configurable file paths via application.properties.
- Highlights the best matching file.

# Configuration
 Set the correct paths for:
 ```properties
 reference.file.path=PUT_ABSOLUTE_PATH_TO_REFERENCE_FILE
 pool.directory.path=PUT_ABSOLUTE_PATH_TO_DIRECTORY_CONTAINING_FILES
 ```

 **Make sure to provide the full absolute paths**. Example:
 ```properties
 reference.file.path=C:/Users/Michael/Desktop/reference.txt
 pool.directory.path=C:/Users/Michael/Desktop/pool
 ```

# Testing

To run unit tests:

```bash
./mvnw test
```

Unit tests use test files from `src/test/resources`:
- `reference.txt`
- `pool/` directory containing test cases
---
