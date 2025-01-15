package org.example.y2023.answer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AoC_09 {
    public static void main(String[] args) throws FileNotFoundException {
        final String examplePathPart1 = "src/main/java/org/example/data/Day9_part1_example.txt";
        final String inputPath = "src/main/java/org/example/data/Day9_input.txt";

//        Import file as array per line
//        ArrayList<String> inputLines = fileToArray("src\\day_9\\input.txt");


        File file = new File(inputPath);
        Scanner scanner = new Scanner(file);
        List<String> inputLines = new ArrayList<>();

        while (scanner.hasNext()) {
            inputLines.add(scanner.nextLine());
        }

        long sum = 0;

        for (String inputLine : inputLines) {

//            Splits string to array and converts to int array
            String[] sSequence = inputLine.split(" ");

            int[] sequence = new int[sSequence.length];
            for (int i = 0; i < sSequence.length; i++) {
                sequence[i] = Integer.parseInt(sSequence[i]);
            }

            System.out.println(Arrays.toString(sequence));

//            Calculates next step via recursive method
            int nextValue = sequence[sequence.length-1] + rowCalc(sequence);
            System.out.println(nextValue);

//            Calculates sum
            sum += nextValue;
        }

//        Prints sum
        System.out.println("Sum: " + sum);
    }

    //    Calculating next row, returns calculated next step
    public static int rowCalc(int[] inputArray){
        int[] calcArray = new int[inputArray.length-1];
        for (int i = 1; i < inputArray.length; i++) {
            calcArray[i-1] = inputArray[i] - inputArray[i-1];
        }
        System.out.println(Arrays.toString(calcArray));

        boolean allZero = Arrays.stream(calcArray).allMatch((i) -> i == 0);
        if (allZero){
            return 0;
        } else {
            int nextValue = calcArray[calcArray.length-1] + rowCalc(calcArray);
            System.out.println(nextValue);
            return nextValue;
        }
    }
}
