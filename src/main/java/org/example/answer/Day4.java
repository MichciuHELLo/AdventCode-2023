package org.example.answer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4 {

    final String examplePathPart1 = "src/main/java/org/example/data/Day4_part1_example.txt";
    final String inputPath = "src/main/java/org/example/data/Day4_input.txt";

    public void part1() throws FileNotFoundException {
        System.out.println("---------------");
        System.out.println("Day 4 - part 1\n");

        File file = new File(inputPath);
        Scanner scanner = new Scanner(file);

        int sum = 0;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(":");
            String[] splitNumbers = splitLine[1].split("\\|");

            List<String> winningNumbers = new ArrayList<>();
            List<String> yourNumbers = new ArrayList<>();

            StringBuilder stringBuilder = new StringBuilder();
            splitNumbers[0] += " ";
            for (int i = 0; i < splitNumbers[0].length(); i++) {
                if ((int) splitNumbers[0].charAt(i) >= 48 && (int) splitNumbers[0].charAt(i) <= 57) {
                    stringBuilder.append(splitNumbers[0].charAt(i));
                } else if (splitNumbers[0].charAt(i) == ' ' && !stringBuilder.isEmpty()) {
                    winningNumbers.add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                }
            }

            stringBuilder = new StringBuilder();
            splitNumbers[1] += " ";
            for (int i = 0; i < splitNumbers[1].length(); i++) {
                if ((int) splitNumbers[1].charAt(i) >= 48 && (int) splitNumbers[1].charAt(i) <= 57) {
                    stringBuilder.append(splitNumbers[1].charAt(i));
                } else if (splitNumbers[1].charAt(i) == ' ' && !stringBuilder.isEmpty()) {
                    yourNumbers.add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                }
            }

            int won = 0;
            for (int i = 0; i < winningNumbers.size(); i++) {
                if (yourNumbers.contains(winningNumbers.get(i))) {
                    won++;
                }
            }
            sum += 1 * Math.pow(2, won-1);
        }
        System.out.println();
        System.out.println("----------------------");
        System.out.println("FINAL ANSWEAR: " + sum);
        System.out.println("----------------------");
    }

    public void part2() throws FileNotFoundException {
        System.out.println("---------------");
        System.out.println("Day 4 - part 2\n");

        File file = new File(inputPath);
        Scanner scanner = new Scanner(file);

        List<String> lines = new ArrayList<>();
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }

        List<Integer> duplicates = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            duplicates.add(1);
        }
        System.out.println(duplicates);

        int sum = 0;
        int turn = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] splitLine = line.split(":");

            List<String> winningNumbers = new ArrayList<>();
            List<String> yourNumbers = new ArrayList<>();

            boolean yourNumbersCheck = false;
            StringBuilder stringBuilder = new StringBuilder();
            splitLine[1] += " ";
            for (int j = 0; j < splitLine[1].length(); j++) {
                if ((int) splitLine[1].charAt(j) >= 48 && (int) splitLine[1].charAt(j) <= 57) {
                    stringBuilder.append(splitLine[1].charAt(j));
                } else if (splitLine[1].charAt(j) == ' ' && !stringBuilder.isEmpty()) {
                    if (yourNumbersCheck) {
                        yourNumbers.add(stringBuilder.toString());
                    } else {
                        winningNumbers.add(stringBuilder.toString());
                    }
                    stringBuilder = new StringBuilder();
                } else if (splitLine[1].charAt(j) == '|') {
                    yourNumbersCheck = true;
                }
            }

            int won = 0;
            for (int j = 0; j < winningNumbers.size(); j++) {
                if (yourNumbers.contains(winningNumbers.get(j))) {
                    won++;
                }
            }

            System.out.println("turn " + (turn+1) + " - won = " + won);

            for (int j = 0; j < duplicates.get(turn); j++) {
                for (int k = turn+1; k <= turn+won; k++) {
                    duplicates.set(k, duplicates.get(k)+1);
                }
            }
            turn++;
        }

        System.out.println(duplicates);
        for (int i = 0; i < duplicates.size(); i++) {
            sum += duplicates.get(i);
        }

        System.out.println();
        System.out.println("----------------------");
        System.out.println("FINAL ANSWEAR: " + sum);
        System.out.println("----------------------");
    }
}
