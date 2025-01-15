package org.example.y2024.answers;

import org.example.y2024.Welcome2024;
import org.example.DaterReader;

import java.util.ArrayList;
import java.util.List;

public class Day01_2024 {

    final String examplePathPart1 = "src/main/java/org/example/y2024/data/d01/Day01_2024_part1_example.txt";
    final String inputPath = "src/main/java/org/example/y2024/data/d01/Day1_2024_input.txt";

    public void part1() {
        Welcome2024.welcome();
        var inputStringList = DaterReader.getInputData(inputPath);

        List<String> splitLineLeft = new ArrayList<>();
        List<String> splitLineRight = new ArrayList<>();

        for (String currentLine : inputStringList) {
            var splitLine = currentLine.split(" {3}");
            splitLineLeft.add(splitLine[0]);
            splitLineRight.add(splitLine[1]);
        }

        splitLineLeft.sort(String::compareTo);
        splitLineRight.sort(String::compareTo);

        int sumOfDiff = 0;
        for (int i = 0; i < splitLineLeft.size(); i++) {
            Integer left = Integer.valueOf(splitLineLeft.get(i));
            Integer right = Integer.valueOf(splitLineRight.get(i));

            sumOfDiff += Math.abs(left - right);
        }

        System.out.println("=====================================");
        System.out.println("Ans Day01_2024_part1 = " + sumOfDiff);
        System.out.println("=====================================");
    }

    public void part2() {
        Welcome2024.welcome();
        var inputStringList = DaterReader.getInputData(inputPath);

        List<String> splitLineLeft = new ArrayList<>();
        List<String> splitLineRight = new ArrayList<>();

        for (String currentLine : inputStringList) {
            var splitLine = currentLine.split(" {3}");
            splitLineLeft.add(splitLine[0]);
            splitLineRight.add(splitLine[1]);
        }

        int similarityScore = 0;
        for (String wanted : splitLineLeft) {
            int times = 0;
            for (String right : splitLineRight) {
                if (wanted.equals(right)) {
                    times++;
                }
            }
            similarityScore += Integer.parseInt(wanted) * times;
        }

        System.out.println("=====================================");
        System.out.println("Ans Day01_2024_part2 = " + similarityScore);
        System.out.println("=====================================");
    }

}
