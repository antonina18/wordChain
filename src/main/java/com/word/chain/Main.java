package com.word.chain;

import com.word.chain.app.DictionaryLoader;
import com.word.chain.app.UserConsole;

import java.util.List;
import java.util.Map;

public class Main {

    private static final String DICTIONARY_FILE_PATH_REQUIRED_MSG = "Dictionary file path required.";

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println(DICTIONARY_FILE_PATH_REQUIRED_MSG);
            return;
        }

        Map<Integer, List<String>> dictionary = new DictionaryLoader(args[0]).load();
        if (!dictionary.isEmpty()) {
            UserConsole userConsole = new UserConsole(dictionary);
            userConsole.run();
        }
    }

}
