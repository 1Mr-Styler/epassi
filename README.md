# Epassi Spring Boot Word Count API

A Spring Boot application to find the top K-most frequent words from an uploaded text file.

Given the nature of the problem, I decided to use MinHeap because it provides a good trade-off between time and space complexity.
While inserting and extracting using Trie might seem efficient, the sorting step could be the bottleneck, especially if N is large.
The time complexity for my approach is O(NlogN).


## Features

- Upload a text file and specify a value for K to get the K most frequent words.
- Efficient algorithm using a Min-Heap to retrieve the top K frequent words.
- Caching mechanism to avoid re-calculating for the same file and K value.
- Streams the file content for memory-efficient processing of large files.
- User authentication using Spring Security with basic authentication.

## Prerequisites

- Java 17
- Maven

## Setup & Running the Application

1. **Clone the Repository**

   ```bash
   git clone https://github.com/1Mr-Styler/epassi.git
   cd epassi
   ```

2. **Build the Application**

   ```bash
   mvn clean install
   ```

3. **Run the Application**

   ```bash
   mvn spring-boot:run
   ```

   After executing the above command, the application should be running and accessible via `http://localhost:8080`.

## Using the Application

1. Send a `multipart/form-data` POST request to:

   ```
   http://localhost:8080/counter
   ```

   The POST request should include a multipart `file` (the text file) and a parameter `k` which represents the top K frequent words you want to retrieve.

2. Authentication:
   
   Use the following default credentials for basic authentication:
   
   ```
   Username: user
   Password: pass
   ```


## Running Tests

1. To run tests, simply use the Maven test command:

   ```bash
   mvn test
   ```
