package org.example.y2023.answer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day3 {

    final String examplePathPart1 = "src/main/java/org/example/y2023/data/Day3_part1_example.txt";
    final String examplePathPart2 = "src/main/java/org/example/y2023/data/Day3_part2_example.txt";
    final String inputPath = "src/main/java/org/example/y2023/data/Day3_input.txt";

    public void part1 () throws FileNotFoundException {
        System.out.println("---------------");
        System.out.println("Day 3 - part 1\n");

        File file = new File(inputPath);
        Scanner scanner = new Scanner(file);

        int sum = 0;
        int lp = 1;
        List<String> validNumbers = new ArrayList<>();
        List<String> lineList = new ArrayList<>();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            lineList.add(line);
        }

        for (int i = 0; i < lineList.size(); i++) {
            int numberLength = 0;
            boolean previousExists = false;
            boolean nextExists = false;
            StringBuilder numberStringBuilder = new StringBuilder();
            for (int j = 0; j < lineList.get(i).length(); j++) {
                if ((int) lineList.get(i).charAt(j) >= 48 && (int) lineList.get(i).charAt(j) <= 57) {
                    numberStringBuilder.append(lineList.get(i).charAt(j));
                    numberLength++;
                }
                if ((int) lineList.get(i).charAt(j) < 48 && numberLength > 0 || (int) lineList.get(i).charAt(j) > 57 && numberLength > 0 || j == lineList.get(i).length()-1 && numberLength > 0) {

                    String previousLine = "";
                    String currentLine = lineList.get(i);
                    String nextLine = "";

                    if (i > 0) {
                        previousExists = true;
                        previousLine = lineList.get(i-1);
                    }
                    if (i < lineList.size()-1) {
                        nextExists = true;
                        nextLine = lineList.get(i+1);
                    }

//                    System.out.println("previousLine - " + previousLine);
//                    System.out.println("currentLine - " + currentLine);
//                    System.out.println("nextLine - " + nextExists);

//                    if (j == lineList.get(i).length()-1) {
//                        numberLength -= 1;
//                    }

                    for (int k = j-numberLength-1; k <= j; k++) {

                        System.out.println("k: " + k);

                        if (k < 0) { k = 0; }

//                        System.out.println("previos - " + previousLine.charAt(k) + " - int - " + (int) previousLine.charAt(k));
//                        System.out.println("current - " + currentLine.charAt(k) + " - int - " + (int) currentLine.charAt(k));
//                        System.out.println("next - " + nextLine.charAt(k) + " - int - " + (int) nextLine.charAt(k));

                        if ((int) currentLine.charAt(k) < 48 && currentLine.charAt(k) != '.' || (int) currentLine.charAt(k) > 57 && currentLine.charAt(k) != '.') {
                            sum += Integer.parseInt(numberStringBuilder.toString());
                            System.out.println(lp + ". " + i + 1 + ": * in c? Number - " + numberStringBuilder + " sum - " + sum);
                            validNumbers.add(numberStringBuilder.toString());
                            lp++;
                            break;
                        }
                        else if (previousExists && (int) previousLine.charAt(k) < 48 && previousLine.charAt(k) != '.' || previousExists && (int) previousLine.charAt(k) > 57 && previousLine.charAt(k) != '.') {
                            sum += Integer.parseInt(numberStringBuilder.toString());
                            System.out.println(lp + ". " + i + 1 + ": * in p? Number - " + numberStringBuilder + " sum - " + sum);
                            validNumbers.add(numberStringBuilder.toString());
                            lp++;
                            break;
                        }
                        else if (nextExists && (int) nextLine.charAt(k) < 48 && nextLine.charAt(k) != '.' || nextExists && (int) nextLine.charAt(k) > 57 && nextLine.charAt(k) != '.') {
                            sum += Integer.parseInt(numberStringBuilder.toString());
                            System.out.println(lp + ". " + (i+1) + ": * in n? Number - " + numberStringBuilder + " sum - " + sum);
                            validNumbers.add(numberStringBuilder.toString());
                            lp++;
                            break;
                        }
                    }
                    numberStringBuilder = new StringBuilder();
                    numberLength = 0;
                }
            }
        }
        System.out.println();
        System.out.println("----------------------");
        System.out.println("Valid numbers: " + validNumbers.stream().map(String::valueOf).collect(Collectors.joining(", ")));
        System.out.println("FINAL ANSWEAR: " + sum);
        System.out.println("----------------------");
    }

    public void part2 () throws FileNotFoundException {
        System.out.println("---------------");
        System.out.println("Day 3 - part 1\n");

        File file = new File(inputPath);
        Scanner scanner = new Scanner(file);
        long sum = 0;

        List<String> lines = new ArrayList<>();
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (lines.get(i).charAt(j) == '*') {
                    System.out.print((i+1) + " - ");
                    String previousLine = lines.get(i-1);
                    String currentLine = lines.get(i);
                    String nextLine = lines.get(i+1);

                    List<String> gears = new ArrayList<>();
                    StringBuilder stringBuilder = new StringBuilder();
                    int from = Math.max(j - 3, 0);
                    int to = Math.min(j + 3, lines.get(i).length());

                    // current right
                    for (int k = j+1; k <= to; k++) {
                        if ((int) currentLine.charAt(k) >= 48 && (int) currentLine.charAt(k) <= 57) {
                            stringBuilder.append(currentLine.charAt(k));
                        }
                        else {
                            break;
                        }
                    }
                    if (!stringBuilder.isEmpty()) {
                        gears.add(stringBuilder.toString());
                    }
                    stringBuilder = new StringBuilder();

                    // current left
                    for (int k = j-1; k >= from; k--) {
                        if ((int) currentLine.charAt(k) >= 48 && (int) currentLine.charAt(k) <= 57) {
                            stringBuilder.append(currentLine.charAt(k));
                        }
                        else {
                            break;
                        }
                    }
                    if (!stringBuilder.isEmpty()) {
                        gears.add(stringBuilder.reverse().toString());
                    }

//                    System.out.println("stringBuilder: " + stringBuilder);
//                    System.out.println("gears: " + gears);

                    stringBuilder = new StringBuilder();

                    // previous
                    for (int k = from; k <= to; k++) {
                        if ((int) previousLine.charAt(k) >= 48 && (int) previousLine.charAt(k) <= 57) {
                            stringBuilder.append(previousLine.charAt(k));
                        }
                        else {
                            if (!stringBuilder.isEmpty() && k <= j-1) {
                                stringBuilder = new StringBuilder();
                            }
                            else if (!stringBuilder.isEmpty() && k == j) {
                                gears.add(stringBuilder.toString());
                                stringBuilder = new StringBuilder();
                            }
                            else if (!stringBuilder.isEmpty() && k == j+1) {
                                gears.add(stringBuilder.toString());
                                stringBuilder = new StringBuilder();
                                break;
                            }
                            else if (stringBuilder.isEmpty() && k > j) {
                                break;
                            }
                        }
                        if (stringBuilder.length() == 3) {
                            gears.add(stringBuilder.toString());
                            stringBuilder = new StringBuilder();
                        }
                    }
                    if (!stringBuilder.isEmpty()) {
                        gears.add(stringBuilder.toString());
                    }

//                    System.out.println("stringBuilder: " + stringBuilder);
//                    System.out.println("gears: " + gears);

                    stringBuilder = new StringBuilder();

                    // next
                    for (int k = from; k <= to; k++) {
                        if ((int) nextLine.charAt(k) >= 48 && (int) nextLine.charAt(k) <= 57) {
                            stringBuilder.append(nextLine.charAt(k));
                        }
                        else {
                            if (!stringBuilder.isEmpty() && k <= j-1) {
                                stringBuilder = new StringBuilder();
                            }
                            else if (!stringBuilder.isEmpty() && k == j) {
                                gears.add(stringBuilder.toString());
                                stringBuilder = new StringBuilder();
                            }
                            else if (!stringBuilder.isEmpty() && k == j+1) {
                                gears.add(stringBuilder.toString());
                                stringBuilder = new StringBuilder();
                                break;
                            }
                            else if (stringBuilder.isEmpty() && k > j) {
                                break;
                            }
                        }

                        if (stringBuilder.length() == 3) {
                            gears.add(stringBuilder.toString());
                            stringBuilder = new StringBuilder();
                        }
                    }
                    if (!stringBuilder.isEmpty()) {
                        gears.add(stringBuilder.toString());
                    }

//                    System.out.println("stringBuilder: " + stringBuilder);
//                    System.out.println("gears: " + gears);

                    int ratio = 0;
                    if (gears.size() == 2) {
                        ratio = Integer.parseInt(gears.get(0)) * Integer.parseInt(gears.get(1));
                    }
                    System.out.println("gears: " + gears);
                    gears.clear();

                    sum += ratio;

                    System.out.println("ratio: " + ratio);
                    System.out.println("sum: "+ sum);
                }
            }
        }
        System.out.println();
        System.out.println("----------------------");
        System.out.println("FINAL ANSWEAR: " + sum);
        System.out.println("----------------------");
    }
}
