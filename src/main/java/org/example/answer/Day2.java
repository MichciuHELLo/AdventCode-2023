package org.example.answer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {

    final String examplePath = "src/main/java/org/example/data/Day2_part1_example.txt";
    final String inputPath = "src/main/java/org/example/data/Day2_input.txt";

    public void part1() throws FileNotFoundException {
        System.out.println("---------------");
        System.out.println("Day 2 - part 1\n");

        File file = new File(inputPath);
        Scanner scanner = new Scanner(file);

        int sum = 0;
        int id = 0;

        while (scanner.hasNext()) {
            id++;
            System.out.print(id + " - ");

            String line = scanner.nextLine().replaceAll(" ", "");
            String[] splitGame = line.split(":");
            System.out.print(splitGame[1]);
            boolean isPossible = true;

            String[] splitLine = splitGame[1].split(";");
            for (int i = 0; i < splitLine.length; i++) {

                String[] splitSubset = splitLine[i].split(",");
                for (int j = 0; j < splitSubset.length; j++) {
                    String number = "";
                    if (splitSubset[j].contains("red")) {
                        number = splitSubset[j].substring(0, splitSubset[j].length()-3);
                        int numberInt = Integer.parseInt(number);
                        if (numberInt > 12) {
                            isPossible = false;
                        }
                    }
                    if (splitSubset[j].contains("green")) {
                        number = splitSubset[j].substring(0, splitSubset[j].length()-5);
                        int numberInt = Integer.parseInt(number);
                        if (numberInt > 13) {
                            isPossible = false;
                        }
                    }
                    if (splitSubset[j].contains("blue")) {
                        number = splitSubset[j].substring(0, splitSubset[j].length()-4);
                        int numberInt = Integer.parseInt(number);
                        if (numberInt > 14) {
                            isPossible = false;
                        }
                    }
                }
            }
            System.out.println(" - " + isPossible);

            if (isPossible) {
                sum += id;
            }
        }
        System.out.println("----------------------");
        System.out.println("FINAL ANSWEAR: " + sum);
        System.out.println("----------------------");
    }

    public void part2() throws FileNotFoundException {
        System.out.println("---------------");
        System.out.println("Day 2 - part 2\n");

        File file = new File(inputPath);
        Scanner scanner = new Scanner(file);

        int sum = 0;

        while (scanner.hasNext()) {
            String line = scanner.nextLine().replaceAll(" ", "");
            String[] splitGame = line.split(":");
            System.out.println(splitGame[1]);

            int red = 0;
            int green = 0;
            int blue = 0;

            String[] splitLine = splitGame[1].split(";");
            for (int i = 0; i < splitLine.length; i++) {
                String[] splitSubset = splitLine[i].split(",");
                for (int j = 0; j < splitSubset.length; j++) {
                    String number = "";
                    if (splitSubset[j].contains("red")) {
                        number = splitSubset[j].substring(0, splitSubset[j].length()-3);
                        int numberInt = Integer.parseInt(number);
                        if (numberInt > red) {
                            red = numberInt;
                        }
                    }
                    if (splitSubset[j].contains("green")) {
                        number = splitSubset[j].substring(0, splitSubset[j].length()-5);
                        int numberInt = Integer.parseInt(number);
                        if (numberInt > green) {
                            green = numberInt;
                        }
                    }
                    if (splitSubset[j].contains("blue")) {
                        number = splitSubset[j].substring(0, splitSubset[j].length()-4);
                        int numberInt = Integer.parseInt(number);
                        if (numberInt > blue) {
                            blue = numberInt;
                        }
                    }
                }
            }
            sum += red * green * blue;
        }
        System.out.println("----------------------");
        System.out.println("FINAL ANSWEAR: " + sum);
        System.out.println("----------------------");
    }

}
