package com.word.chain.app;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.out;

public class UserConsole {

    private static final String EXIT_CHAR = "y";
    private static final String SAME_LENGTH_VIOLATION_MSG = "Words should have same length";
    private static final String WORDS_LENGTH_VIOLATION_MSG = "Words should have length >= 2";
    private static final String WORD_NOT_FOUND_MSG = "Word '%s' not found in the dictionary";

    private final Map<Integer, List<String>> dictionary;
    private final ChainCalculator chainCalculator;

    public UserConsole(Map<Integer, List<String>> dictionary) {
        this.dictionary = dictionary;
        this.chainCalculator = new ChainCalculator(dictionary);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("Start word: ");
            String from = scanner.nextLine().trim();
            System.out.println("Target word: ");
            String to = scanner.nextLine().trim();
            if (validateInput(from, to)) {
                chainCalculator
                        .calculate(from, to)
                        .forEach(System.out::println);
            }
            System.out.println("Exit ? (y/n) : ");
            exit = EXIT_CHAR.equalsIgnoreCase(scanner.nextLine().trim());
        }
    }

    private boolean validateInput(String from, String to) {
        if (from.length() != to.length()) {
            out.println(SAME_LENGTH_VIOLATION_MSG);
            return false;
        }
        if (from.length() < 2) {
            out.println(WORDS_LENGTH_VIOLATION_MSG);
            return false;
        }
        if (!wordExistInDictionary(from)) {
            out.println(String.format(WORD_NOT_FOUND_MSG, from));
            return false;
        }
        if (!wordExistInDictionary(to)) {
            out.println(String.format(WORD_NOT_FOUND_MSG, to));
            return false;
        }
        return true;
    }

    private boolean wordExistInDictionary(String word) {
        return dictionary.values()
                .stream()
                .flatMap(Collection::stream)
                .anyMatch(element -> element.equals(word));
    }


}
