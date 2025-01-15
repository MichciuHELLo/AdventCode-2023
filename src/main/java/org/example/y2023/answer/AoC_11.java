package org.example.y2023.answer;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class AoC_11 {
    final static String examplePathPart1_0 = "src/main/java/org/example/data/Day11_part1.0_example.txt";
    final static String inputPath = "src/main/java/org/example/data/Day11_input.txt";

    public static String[][] fileTo2DArray(String path) {

//        Import file as array per line
        ArrayList<String> inputLines = fileToArray(path);

//        Create an 2D array of inputlines
        String[][] coordinate2DArray = new String[inputLines.size()][];

        for (int i = 0; i < inputLines.size(); i++) {
            coordinate2DArray[i] = (inputLines.get(i).split(""));
        }

        return coordinate2DArray;
    }

    public static ArrayList<String> fileToArray(String path) {

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
        while (scanner.hasNextLine()) {
            inputLines.add(scanner.nextLine());
        }
        return inputLines;
    }

    public static void main(String[] args) {

//        Import file as 2D array
        String[][] spaceCoordinates= fileTo2DArray(inputPath);

        long sum = 0;

//        Arrays for columns and rows where space has expanded
        ArrayList<Integer> expSpaceX = new ArrayList<>();
        ArrayList<Integer> expSpaceY = new ArrayList<>();

//        Finds where space is expanded horizontally
        for (int x = 0; x < spaceCoordinates[0].length; x++) {
            boolean galaxies = false;
            for (String[] rowArray : spaceCoordinates) {
                if (rowArray[x].equals("#")) {
                    galaxies = true;
                    break;
                }
            }
//            If empty column, saves it to array
            if (!galaxies) {
                expSpaceX.add(x);
            }
        }

//        Finds where space is expanded vertically
        for (int y = 0; y < spaceCoordinates.length; y++) {
            boolean galaxies = false;
            for (int x = 0; x < spaceCoordinates[y].length; x++) {
                if (spaceCoordinates[y][x].equals("#")) {
                    galaxies = true;
                    break;
                }
            }
//            If empty row, saves it to array
            if (!galaxies) {
                expSpaceY.add(y);
            }
        }

//        Prints expanded space rows columns and rows
//        System.out.println("Columns with expanded space: " + expSpaceX);
//        System.out.println("Rows with expanded space: " + expSpaceY);

//        Creates array with coordinates of the galaxies
        ArrayList<Point> galaxyCoordinates = new ArrayList<>();

        for (int y = 0; y < spaceCoordinates.length; y++) {
            for (int x = 0; x < spaceCoordinates[y].length; x++) {
                if (spaceCoordinates[y][x].equals("#")){
                    galaxyCoordinates.add(new Point(x,y));
                }
            }
        }

//        Prints GalaxyCoordinates
//        System.out.println(galaxyCoordinates);

//        Calculates distance between all galaxies in array
        for (int i = 0; i < galaxyCoordinates.size(); i++) {
            for (int j = i+1; j < galaxyCoordinates.size(); j++) {

//                Adds extra length for every expanded space line it crosses.
                int extraSpace = 0;
//                Optimization
                int yFirstGalaxy = (int) galaxyCoordinates.get(i).getY();
                int ySecondGalaxy = (int) galaxyCoordinates.get(j).getY();
                int xFirstGalaxy = (int) galaxyCoordinates.get(i).getX();
                int xSecondGalaxy = (int) galaxyCoordinates.get(j).getX();

                for (Integer yExpSpace : expSpaceY) {
                    if ((yExpSpace < ySecondGalaxy && yExpSpace > yFirstGalaxy)) {
                        extraSpace += 999999;
                    }
                }
                for (Integer xExpSpace : expSpaceX) {
                    if ((xExpSpace > xSecondGalaxy && xExpSpace < xFirstGalaxy) ||
                            (xExpSpace < xSecondGalaxy && xExpSpace > xFirstGalaxy)) {
                        extraSpace+= 999999;
                    }
                }

                var x = Math.abs(yFirstGalaxy-ySecondGalaxy) + Math.abs(xFirstGalaxy-xSecondGalaxy) + extraSpace;
                System.out.println(x);
//                Calculates sum
                sum += x;
            }
        }

//        Prints sum
        System.out.println("Sum: " + sum);
    }
}

