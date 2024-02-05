package org.example.answer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1 {

    final String examplePathPart1 = "src/main/java/org/example/data/Day1_part1_example.txt";
    final String examplePathPart2 = "src/main/java/org/example/data/Day1_part2_example.txt";
    final String inputPath = "src/main/java/org/example/data/Day1_input.txt";

    public void part1() throws FileNotFoundException {
        System.out.println("---------------");
        System.out.println("Day 1 - part 1\n");

        File file = new File(inputPath);
        Scanner scanner = new Scanner(file);

        int sum = 0;

        while (scanner.hasNext()) {
            StringBuilder answer = new StringBuilder();
            String line = scanner.nextLine();
            System.out.println(line);
            int count = 0;
            while (count < 1) {
                for (int i = 0; i < line.length(); i++) {
                    char charAt = line.charAt(i);

                    if ((int) charAt >= 40 && (int) charAt <= 57) {
                        answer.append(charAt);
                        count++;
                        break;
                    }
                }
            }

            while (count < 2) {
                for (int i = line.length() - 1; i >= 0; i--) {
                    char charAt = line.charAt(i);

                    if ((int) charAt >= 40 && (int) charAt <= 57) {
                        answer.append(charAt);
                        count++;
                        break;
                    }
                }
            }

            System.out.println("answear: " + answer);
            System.out.println();

            sum += Integer.parseInt(answer.toString());
        }
        System.out.println("----------------------");
        System.out.println("FINAL ANSWEAR: " + sum);
        System.out.println("----------------------");
    }

    public void part2() throws FileNotFoundException {
        System.out.println("---------------");
        System.out.println("Day 1 - part 2\n");

        String[] numbersTab = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        File file = new File(inputPath);
        Scanner scanner = new Scanner(file);
        int sum = 0;

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            System.out.print(line + " - ");

            StringBuilder answer = new StringBuilder();
            int count = 0;

            for (int i = 0; i <= line.length(); i++) {
                String potNumber = line.substring(0, i);
//                System.out.println(potNumber);
                for (int j = 0; j < numbersTab.length; j++) {
                    if (potNumber.contains(numbersTab[j])) {
                        count++;
                        answer.append(wordToNumber(numbersTab[j]));
                        break;
                    }
                }
                if (count > 0) {
                    break;
                }
            }

            for (int i = line.length(); i >= 0; i--) {
                String potNumber = line.substring(i);
//                System.out.println(potNumber);
                for (int j = 0; j < numbersTab.length; j++) {
                    if (potNumber.contains(numbersTab[j])) {
                        count++;
                        answer.append(wordToNumber(numbersTab[j]));
                        break;
                    }
                }
                if (count > 1) {
                    break;
                }
            }
            System.out.println(answer);
            sum += Integer.parseInt(answer.toString());
        }
        System.out.println("----------------------");
        System.out.println("FINAL ANSWEAR: " + sum);
        System.out.println("----------------------");
    }

    private String wordToNumber(String word) {
        return switch (word) {
            case "one" -> "1";
            case "two" -> "2";
            case "three" -> "3";
            case "four" -> "4";
            case "five" -> "5";
            case "six" -> "6";
            case "seven" -> "7";
            case "eight" -> "8";
            case "nine" -> "9";
            default -> word;
        };
    }
}
