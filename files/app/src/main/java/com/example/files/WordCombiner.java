package com.example.files;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordCombiner {

    public static List<String> combineWords(List<String> words) {
        List<String> results = new ArrayList<>();
        Map<String, List<String>> prefixMap = new HashMap<>();
        Set<String> generated = new HashSet<>();

        for (String word : words) {
            if (word.length() >= 4) {
                String prefix = word.substring(0, 4).toLowerCase();
                prefixMap.computeIfAbsent(prefix, k -> new ArrayList<>()).add(word);
            }
        }

        for (int i = 0; i < words.size(); i++) {
            String word1 = words.get(i);
            if (word1.length() < 4) continue;

            String suffix = word1.substring(word1.length() - 4).toLowerCase();
            List<String> matches = prefixMap.get(suffix);
            if (matches != null) {
                for (String word2 : matches) {
                    if (!word1.equals(word2)) {
                        String newWord = word1 + word2.substring(4);
                        if (!generated.contains(newWord)) {
                            String formatted = word1 + " + " + word2 + " = " + newWord;
                            results.add(formatted);
                            generated.add(newWord);

                            if (results.size() >= 100) {
                                return results;
                            }
                        }
                    }
                }
            }
        }

        return results;
    }
}