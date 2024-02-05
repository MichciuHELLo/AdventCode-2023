package org.example.answer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day8 {
    final String examplePathPart1 = "src/main/java/org/example/data/Day8_part1_example.txt";
    final String examplePathPart1v2 = "src/main/java/org/example/data/Day8_part1_example2.txt";
    final String examplePathPart2 = "src/main/java/org/example/data/Day8_part2_example.txt";
    final String inputPath = "src/main/java/org/example/data/Day8_input.txt";

    public void part1() throws FileNotFoundException {
        System.out.println("---------------");
        System.out.println("Day 8 - part 1\n");

        File file = new File(inputPath);
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();

        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }

        HashMap<String, String> directionMap = new HashMap<>();
        for (int i = 2; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] spitLine = line.split("=");
            String key = spitLine[0].replace(" ", "");
            String value = spitLine[1].replaceAll(" ", "");
            value = value.replace("(", "");
            value = value.replace(")", "");

            directionMap.put(key, value);
        }

        String directionLine = lines.getFirst();
        String start = "AAA";
        int directionIndex = 0;
        int steps = 0;
        boolean searching = true;
        while (searching) {
            steps++;
            char direction = directionLine.charAt(directionIndex);

            String ways = directionMap.get(start);
            String[] splitWays = ways.split(",");

            System.out.print(steps + ". " + start + " -> ");

            if (direction == 'L') {
                start = splitWays[0];
            } else if (direction == 'R') {
                start = splitWays[1];
            }

            System.out.println(start);

            directionIndex++;
            if (directionIndex >= directionLine.length()) {
                directionIndex = 0;
            }

            if (Objects.equals(start, "ZZZ")) {
                searching = false;
            }

        }
        System.out.println();
        System.out.println("----------------------");
        System.out.println("FINAL ANSWEAR: " + steps);
        System.out.println("----------------------");
    }

    public void part2() throws FileNotFoundException {
        System.out.println("---------------");
        System.out.println("Day 8 - part 2\n");

        File file = new File(inputPath);
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();

        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }

        List<String> keyList = new ArrayList<>();
        HashMap<String, String> directionMap = new HashMap<>();
        for (int i = 2; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] spitLine = line.split("=");
            String key = spitLine[0].replace(" ", "");
            String value = spitLine[1].replaceAll(" ", "");
            value = value.replace("(", "");
            value = value.replace(")", "");

            keyList.add(key);
            directionMap.put(key, value);
        }

        List<String> startList = new ArrayList<>();
        for (int i = 0; i < keyList.size(); i++) {
            if (keyList.get(i).charAt(2) == 'A') {
                startList.add(keyList.get(i));
            }
        }

        System.out.println("Start list: " + startList);
        System.out.println();

        List<Integer> stepsList = new ArrayList<>();
        for (String start : startList) {
            String directionLine = lines.getFirst();
            int directionIndex = 0;
            int steps = 0;
            boolean searching = true;
            while (searching) {
                steps++;
                char direction = directionLine.charAt(directionIndex);

                String ways = directionMap.get(start);
                String[] splitWays = ways.split(",");

                System.out.print(steps + ". " + start + " -> ");

                if (direction == 'L') {
                    start = splitWays[0];
                } else if (direction == 'R') {
                    start = splitWays[1];
                }

                System.out.println(start);

                directionIndex++;
                if (directionIndex >= directionLine.length()) {
                    directionIndex = 0;
                }

                if (start.charAt(2) == 'Z') {
                    stepsList.add(steps);
                    searching = false;
                }
            }
        }

        System.out.println();
        for (int i = 0; i < stepsList.size(); i++) {
            int lookingNumber = stepsList.get(i);
            System.out.print("Prime numbers of " + lookingNumber + " are:");
            int lp = 2;
            int e = (int)(Math.sqrt(lookingNumber));
            while (lp <= e) {
                while ((lookingNumber % lp) == 0) {
                    lookingNumber /= lp;
                    e = (int)(Math.sqrt(lookingNumber));
                    System.out.print(" " + lp);
                }
                lp++;
            }
            if (lookingNumber > 1) {
                System.out.print(" " + lookingNumber);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("----------------------");
        System.out.println("FINAL ANSWEAR: LCM ALGORITHM");
        System.out.println("----------------------");
    }
}
