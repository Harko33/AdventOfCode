package com.harkonnen.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class AOC {

    protected List<String> inputs = new ArrayList<>();

    protected void init() throws IOException {
        try(Stream<String> stream = Files.lines(Paths.get("inputList.txt"))) {
            stream.forEach(inputs::add);
        }
    }

    protected abstract void run();
}
