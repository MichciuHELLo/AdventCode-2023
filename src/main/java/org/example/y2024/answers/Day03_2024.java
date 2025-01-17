package org.example.y2024.answers;

import org.example.DaterReader;
import org.example.y2024.Welcome2024;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03_2024 {

    private final String examplePathPart1 = "src/main/java/org/example/y2024/data/d03/Day03_2024_example_part1.txt";
    private final String examplePathPart2 = "src/main/java/org/example/y2024/data/d03/Day03_2024_example_part2.txt";
    private final String inputPath = "src/main/java/org/example/y2024/data/d03/Day3_2024_input.txt";

    public void part1() {
        Welcome2024.welcome();
        var inputStringList = DaterReader.getInputData(inputPath);

        int sum = 0;
        for (String currentLine : inputStringList) {
            System.out.println(currentLine);
            String patternMul = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
            var matchesMap = get_matches(currentLine, patternMul);

            for (int i = 0; i < matchesMap.get("aList").size(); i++) {
                int a = matchesMap.get("aList").get(i);
                int b = matchesMap.get("bList").get(i);
                int times = a * b;
                System.out.println(a + " * " + b + " = " + times + " | sum: " + sum);
                sum += times;
            }
        }
        System.out.println("=====================================");
        System.out.println("Ans Day03_2024_part1 = " + sum);
        System.out.println("=====================================");
    }

    public void part2() {
        Welcome2024.welcome();
        var inputStringList = DaterReader.getInputData(inputPath);

        StringBuilder stringBuilder = new StringBuilder();
        for (String currentLine : inputStringList) {
            stringBuilder.append(currentLine);
        }
        String inputData = stringBuilder.toString();
        Matcher matchers = Pattern.compile("mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\)").matcher(inputData);

        int sum = 0;
        boolean enabled = true;
        while (matchers.find()) {

            System.out.println(matchers.group(0));

            if (matchers.group().equals("do()")) {
                enabled = true;
                System.out.println(enabled);
            } else if (matchers.group().equals("don't()")) {
                enabled = false;
                System.out.println(enabled);
            } else {
                if (enabled) {
                    int a = Integer.parseInt(matchers.group(1));
                    int b = Integer.parseInt(matchers.group(2));
                    System.out.println(a + " * " + b + " = " + a * b + " | sum: " + sum);
                    sum += a * b;
                }
            }

        }
        System.out.println("=====================================");
        System.out.println("Ans Day03_2024_part2_1 = " + sum);
        System.out.println("=====================================");
    }

    private Map<String, List<Integer>> get_matches(String s, String pattern) {

        List<Integer> matchesGr1 = new ArrayList<>();
        List<Integer> matchesGr2 = new ArrayList<>();
        List<Integer> matchesStart = new ArrayList<>();
        List<Integer> matchesEnd = new ArrayList<>();

        Matcher matcher = Pattern.compile(pattern).matcher(s);
        while (matcher.find()) {
            matchesGr1.add(Integer.parseInt(matcher.group(1)));
            matchesGr2.add(Integer.parseInt(matcher.group(2)));
            matchesStart.add(matcher.start());
            matchesEnd.add(matcher.end());
        }

        Map<String, List<Integer>> map = new HashMap<>();
        map.put("aList", matchesGr1);
        map.put("bList", matchesGr2);
        map.put("startList", matchesStart);
        map.put("endList", matchesEnd);

        return map;
    }
}
