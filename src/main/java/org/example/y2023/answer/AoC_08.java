package org.example.y2023.answer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AoC_08 {

    public static void main(String[] args){

//        Import file as array per line
        final String examplePathPart1 = "src/main/java/org/example/data/Day8_part1_example.txt";
        final String examplePathPart1v2 = "src/main/java/org/example/data/Day8_part1_example2.txt";
        final String inputPath = "src/main/java/org/example/data/Day8_input.txt";

//        Import file as array per line
        ArrayList<String> inputLines = fileToArray(inputPath);

//        Patterns for recognizing 3 letters
        Pattern pattern = Pattern.compile("[A-Z]{3}");
        Matcher matcher;

//        Converts directions to an array with ints for indexes 0 = left, 1 = right.
        int[] directions = new int[inputLines.get(0).length()];
        int iteraton = 0;
        for (Character c : inputLines.get(0).toCharArray()){
            if (c.equals('L')){
                directions[iteraton] = 0;
            } else if (c.equals('R')){
                directions[iteraton] = 1;
            }
            iteraton++;
        }

//        Converts input array to hashmap, first set of letters as keys and the two others in array as value.
        HashMap<String, String[]> map = new HashMap<>();
        for (int i = 2; i < inputLines.size(); i++) {
            matcher = pattern.matcher(inputLines.get(i));
            matcher.find();
            String key = matcher.group();
            matcher.find();
            String left = matcher.group();
            matcher.find();
            String right = matcher.group();

            map.put(key, new String[]{left, right});
        }

//        Finds the number of steps between AAA and ZZZ via two loops
        String nextKey = "AAA";
        boolean end = false;
        int numberOfSteps = 0;
        while (!end){

//            Direction/step loop
            for (int direction : directions) {
                String[] destionations = map.get(nextKey);
                nextKey = destionations[direction];
                numberOfSteps++;
            }
            if (nextKey.equals("ZZZ")) end = true;
        }

//        Prints put number of steps
        System.out.println(numberOfSteps);
    }

    public static ArrayList<String> fileToArray(String path){

//        Creates scanner and array
        Scanner scanner = null;
        ArrayList<String> inputLines = new ArrayList<>();

//        Loads file with text to scanner
        File inputFile = new File(path);
        try {
            scanner = new Scanner(inputFile);
        } catch (Exception e) {
            System.out.println("File not found: " + e);
        }

//        Puts lines in file in to array and returns it
        while (scanner.hasNextLine()){
            inputLines.add(scanner.nextLine());
        }
        return inputLines;
    }

}
