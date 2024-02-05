package org.example.answer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day12 {
    final String examplePathPart1_0 = "src/main/java/org/example/data/Day12_part1.0_example.txt";
    final String inputPath = "src/main/java/org/example/data/Day12_input.txt";

    public void part1() throws FileNotFoundException {
        System.out.println("---------------");
        System.out.println("Day 12 - part 1\n");

        File file = new File(examplePathPart1_0);
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();

        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }

        System.out.println();
        System.out.println("MAIN grid");
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] splitData = line.split(" ");
            String[] splitNumbers = splitData[1].split(",");
            int arrangements = 0;

            for (int j = 0; j < line.length(); j++) {
                
            }

            System.out.println(lines.get(i) + " - " + arrangements +  " arrangements");
        }
    }
}
