package org.example.answer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day9 {
    final String examplePathPart1 = "src/main/java/org/example/data/Day9_part1_example.txt";
    final String inputPath = "src/main/java/org/example/data/Day9_input.txt";

    public void part1() throws FileNotFoundException {
        System.out.println("---------------");
        System.out.println("Day 9 - part 1\n");

        File file = new File(inputPath);
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();

        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }

        long answer = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            line += " ";
            StringBuilder stringBuilder = new StringBuilder();
            List<Integer> numbers = new ArrayList<>();
            for (int j = 0; j < line.length(); j++) {
                if ((int) line.charAt(j) >= 48 && (int) line.charAt(j) <= 57) {
                    stringBuilder.append(line.charAt(j));
                } else if (line.charAt(j) == '-') {
                    stringBuilder.append(line.charAt(j));
                } else if (line.charAt(j) == ' ' && !stringBuilder.isEmpty()) {
                    numbers.add(Integer.parseInt(stringBuilder.toString()));
                    stringBuilder = new StringBuilder();
                }
            }
            System.out.println("numbers: " + numbers);

            List<Integer> operationNumbers = new ArrayList<>();
            for (int j = 0; j < numbers.size() - 1; j++) {
                int lp1 = numbers.get(j);
                int lp2 = numbers.get(j + 1);
                int difference = lp2 - lp1;

                if (lp2 > lp1) {
                    operationNumbers.add(Math.abs(difference));
                } else {
                    operationNumbers.add(difference);
                }
            }
            System.out.println("operationNumbers: " + operationNumbers);

            boolean foundZeros = false;
            int operationNumbersStartSize = operationNumbers.size();
            int start = 0;
            int round = 1;

            while (!foundZeros) {
                Zero zeroClass = findZeros(start, operationNumbers.size(), operationNumbers);

                if (zeroClass.zeros == zeroClass.numbersAdded) {
                    foundZeros = true;
                } else {
                    start = operationNumbers.size() - operationNumbersStartSize + round;
                    round++;
                }
            }
            System.out.println("operationNumbers: " + operationNumbers);

            long sum = 0;
            int index = operationNumbersStartSize;
            for (int j = 1; j <= round; j++) {
                sum += operationNumbers.get(index - 1);
                index += operationNumbersStartSize - j;
            }
            sum += numbers.getLast();
            System.out.println("sum: " + sum);
            System.out.println();

            answer += sum;
        }
        System.out.println("----------------------");
        System.out.println("FINAL ANSWEAR: " + answer);
        System.out.println("----------------------");
    }

    public void part2() throws FileNotFoundException {
        System.out.println("---------------");
        System.out.println("Day 9 - part 2\n");

        File file = new File(inputPath);
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();

        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }

        long answer = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            line += " ";
            StringBuilder stringBuilder = new StringBuilder();
            List<Integer> numbers = new ArrayList<>();
            for (int j = 0; j < line.length(); j++) {
                if ((int) line.charAt(j) >= 48 && (int) line.charAt(j) <= 57) {
                    stringBuilder.append(line.charAt(j));
                } else if (line.charAt(j) == '-') {
                    stringBuilder.append(line.charAt(j));
                } else if (line.charAt(j) == ' ' && !stringBuilder.isEmpty()) {
                    numbers.add(Integer.parseInt(stringBuilder.toString()));
                    stringBuilder = new StringBuilder();
                }
            }
            System.out.println("numbers: " + numbers);

            List<Integer> operationNumbers = new ArrayList<>();
            for (int j = 0; j < numbers.size() - 1; j++) {
                int lp1 = numbers.get(j);
                int lp2 = numbers.get(j + 1);
                int difference = lp2 - lp1;

                if (lp2 > lp1) {
                    operationNumbers.add(Math.abs(difference));
                } else {
                    operationNumbers.add(difference);
                }
            }
            System.out.println("operationNumbers: " + operationNumbers);

            boolean foundZeros = false;
            int operationNumbersStartSize = operationNumbers.size();
            int start = 0;
            int round = 1;

            while (!foundZeros) {
                Zero zeroClass = findZeros(start, operationNumbers.size(), operationNumbers);

                if (zeroClass.zeros == zeroClass.numbersAdded) {
                    foundZeros = true;
                } else {
                    start = operationNumbers.size() - operationNumbersStartSize + round;
                    round++;
                }
            }
            System.out.println("operationNumbers: " + operationNumbers);

            int startNumber = 0;
            for (int j = round - 1; j >= 0; j--) {
                int index = operationNumbersStartSize;
                for (int k = 1; k <= j; k++) {
                    index += operationNumbersStartSize - k;
                }
                index -= operationNumbersStartSize - j;
                startNumber = operationNumbers.get(index) - startNumber;
            }
            startNumber = numbers.getFirst() - startNumber;

            System.out.println("Extrapolated value: " + startNumber);
            System.out.println();

            answer += startNumber;
        }
        System.out.println("----------------------");
        System.out.println("FINAL ANSWEAR: " + answer);
        System.out.println("----------------------");
    }

    private Zero findZeros(int start, int stop, List<Integer> operationNumbers) {
        int zeros = 0;
        int numbersAdded = 0;
        for (int i = start; i < stop - 1; i++) {
            int lp1 = operationNumbers.get(i);
            int lp2 = operationNumbers.get(i + 1);
            int difference = lp2 - lp1;

            if (difference == 0) {
                zeros++;
            }

            if (lp2 > lp1) {
                operationNumbers.add(Math.abs(difference));
                numbersAdded++;
            } else {
                operationNumbers.add(difference);
                numbersAdded++;
            }
        }

        return new Zero(zeros, numbersAdded, operationNumbers);
    }

    private static class Zero {
        private int zeros;
        int numbersAdded;
        private List<Integer> operationNumbers;

        public Zero(int zeros, int numbersAdded, List<Integer> operationNumbers) {
            this.zeros = zeros;
            this.numbersAdded = numbersAdded;
            this.operationNumbers = operationNumbers;
        }

        public int getZeros() {
            return zeros;
        }

        public void setZeros(int zeros) {
            this.zeros = zeros;
        }

        public int getNumbersAdded() {
            return numbersAdded;
        }

        public void setNumbersAdded(int numbersAdded) {
            this.numbersAdded = numbersAdded;
        }

        public List<Integer> getOperationNumbers() {
            return operationNumbers;
        }

        public void setOperationNumbers(List<Integer> operationNumbers) {
            this.operationNumbers = operationNumbers;
        }
    }
}
