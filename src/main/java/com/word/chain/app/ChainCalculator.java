package com.word.chain.app;

import java.util.*;
import java.util.stream.Collectors;

public class ChainCalculator {

    private static final int MAX_DEPTH = 10;
    private final Map<Integer, List<String>> dictionary;

    public ChainCalculator(Map<Integer, List<String>> dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> calculate(String from, String to) {
        for (int i = 0; i < MAX_DEPTH; i++) {
            LinkedList<String> stack = new LinkedList<>();
            recurse(from, to, stack, i);
            if (stack.size() != 1) {
                return stack;
            }
        }
        return Collections.emptyList();
    }

    private boolean recurse(String from, String to, Deque<String> stack, int maxDepth) {
        stack.push(from);
        if (stack.size() > maxDepth) {
            return false;
        }
        if (from.equals(to)) {
            return true;
        }
        for (String word : getWordsDifferentByOneLetter(from)) {
            if (recurse(word, to, stack, maxDepth)) {
                return true;
            }
            stack.pop();
        }
        return false;
    }

    private List<String> getWordsDifferentByOneLetter(String word) {
        return dictionary.get(word.length())
                .stream()
                .filter(element -> isDifferenceBetweenWordsEqualOne(word, element))
                .collect(Collectors.toList());
    }

    private boolean isDifferenceBetweenWordsEqualOne(String word, String compareTo) {
        int difference = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != compareTo.charAt(i)) {
                difference++;
            }
        }
        return difference == 1;
    }
}
