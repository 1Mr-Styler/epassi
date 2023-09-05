package com.jerry.epassi.controller;

import com.jerry.epassi.data.WordCount;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

@RestController
public class WordController {
    @PostMapping("/counter")
    @Cacheable(value = "kWords", key = "#file.name + '-' + #k")
    public List<WordCount> getKWords(@RequestParam("file") MultipartFile file,
                                     @RequestParam("k") int k) throws Exception {

        Map<String, Integer> frequencyMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Arrays.stream(line.split("\\W+"))
                        .map(String::toLowerCase)
                        .filter(word -> !word.isBlank())
                        .forEach(word -> frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1));
            }
        }

        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        List<WordCount> topKWords = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            Map.Entry<String, Integer> entry = minHeap.poll();
            topKWords.add(0, new WordCount(entry.getKey(), entry.getValue()));
        }

        return topKWords;
    }
}
