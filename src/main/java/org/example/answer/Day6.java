package org.example.answer;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day6 {

    final String examplePathPart1 = "src/main/java/org/example/data/Day6_part1_example.txt";
    final String inputPath = "src/main/java/org/example/data/Day6_input.txt";

    public void part1() throws FileNotFoundException {
        System.out.println("---------------");
        System.out.println("Day 6 - part 1\n");

        File file = new File(inputPath);
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();

        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }

        StringBuilder stringBuilder = new StringBuilder();

        String[] timeLine = lines.getFirst().split(":");
        timeLine[1] += " ";
        List<Integer> times = new ArrayList<>();
        for (int i = 0; i < timeLine[1].length(); i++) {
            if ((int) timeLine[1].charAt(i) >= 48 && (int) timeLine[1].charAt(i) <= 57) {
                stringBuilder.append(timeLine[1].charAt(i));
            } else if (timeLine[1].charAt(i) == ' ' && !stringBuilder.isEmpty()) {
                times.add(Integer.parseInt(stringBuilder.toString()));
                stringBuilder = new StringBuilder();
            }
        }
        System.out.println("times: " + times);

        String[] distanceLine = lines.get(1).split(":");
        distanceLine[1] += " ";
        List<Integer> distances = new ArrayList<>();
        for (int i = 0; i < distanceLine[1].length(); i++) {
            if ((int) distanceLine[1].charAt(i) >= 48 && (int) distanceLine[1].charAt(i) <= 57) {
                stringBuilder.append(distanceLine[1].charAt(i));
            } else if (distanceLine[1].charAt(i) == ' ' && !stringBuilder.isEmpty()) {
                distances.add(Integer.parseInt(stringBuilder.toString()));
                stringBuilder = new StringBuilder();
            }
        }
        System.out.println("distances: " + distances);

        int answer = 1;
        for (int i = 0; i < times.size(); i++) {
            int waysToWin = 0;
            for (int holdTime = 0; holdTime <= times.get(i); holdTime++) {
                int swam = (times.get(i) - holdTime) * holdTime;

                if (swam > distances.get(i)) {
                    waysToWin++;
                }
            }
            System.out.println("In " + times.get(i) + " you can beat " + distances.get(i) + " distance in " + waysToWin + " ways.");
            answer *= waysToWin;
        }
        System.out.println();
        System.out.println("----------------------");
        System.out.println("FINAL ANSWEAR: " + answer);
        System.out.println("----------------------");
    }

    public void part2() throws FileNotFoundException {
        System.out.println("---------------");
        System.out.println("Day 6 - part 2\n");

        File file = new File(inputPath);
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();

        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }

        String[] timeLine = lines.getFirst().split(":");
        BigInteger time = new BigInteger(timeLine[1].replaceAll(" ", ""));
        System.out.println("time: " + time);

        String[] distanceLine = lines.get(1).split(":");
        BigInteger distance = new BigInteger(distanceLine[1].replaceAll(" ", ""));
        System.out.println("distance: " + distance);

        int answer = 1;
        int waysToWin = 0;
        for (BigInteger holdTime = BigInteger.ZERO; holdTime.compareTo(time) <= 0; holdTime = holdTime.add(BigInteger.ONE)) {
            BigInteger swam = time.subtract(holdTime).multiply(holdTime);

            if (swam.compareTo(distance) > 0) {
                waysToWin++;
            }
        }
        System.out.println("In " + time + " you can beat " + distance + " distance in " + waysToWin + " ways.");
        answer *= waysToWin;

        System.out.println();
        System.out.println("----------------------");
        System.out.println("FINAL ANSWEAR: " + answer);
        System.out.println("----------------------");
    }
}
