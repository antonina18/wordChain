package com.word.chain.app;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DictionaryLoader {

    private String path;

    public DictionaryLoader(String path) {
        this.path = path;
    }

    public Map<Integer, List<String>> load() {
        try (Stream<String> stream = Files.lines(Paths.get(path), Charset.forName("ISO-8859-1"))) {
            return stream
                    .filter(line -> !line.trim().isEmpty())
                    .collect(Collectors.groupingBy(String::length));
        } catch (IOException e) {
            System.out.println(String.format("Cannot read dictionary file: %s", e.fillInStackTrace()));
        }
        return Collections.emptyMap();
    }
}
