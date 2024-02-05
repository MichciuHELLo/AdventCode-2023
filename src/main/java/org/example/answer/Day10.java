package org.example.answer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day10 {
    final String examplePathPart1_0 = "src/main/java/org/example/data/Day10_part1.0_example.txt";
    final String examplePathPart1_1 = "src/main/java/org/example/data/Day10_part1.1_example.txt";
    final String examplePathPart1_2 = "src/main/java/org/example/data/Day10_part1.2_example.txt";
    final String examplePathPart1_3 = "src/main/java/org/example/data/Day10_part1.3_example.txt";
    final String inputPath = "src/main/java/org/example/data/Day10_input.txt";
    final String inputPath_subGrid = "src/main/java/org/example/data/Day10_input_subGrid.txt";
    final String examplePathPart2_0 = "src/main/java/org/example/data/Day10_part2.0_example.txt";
    final String examplePathPart2_1 = "src/main/java/org/example/data/Day10_part2.1_example.txt";
    final String examplePathPart2_2 = "src/main/java/org/example/data/Day10_part2.2_example.txt";
    final String examplePathPart2_3 = "src/main/java/org/example/data/Day10_part2.3_example.txt";

    public void part1() throws FileNotFoundException {
        System.out.println("---------------");
        System.out.println("Day 10 - part 1\n");

        File file = new File(inputPath);
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();

        int count = 0;
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
            count++;
        }

        int locX = 0;
        int locY = 0;
        char[][] tab = new char[lines.getFirst().length()][count];
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                tab[i][j] = line.charAt(j);
                if (line.charAt(j) == 'S') {
                    locY = i;
                    locX = j;
                }
            }
        }
        System.out.println("Start locX: " + locX + " - locY: " + locY);
        System.out.println();

        /*

        | is a vertical pipe connecting north and south.
        - is a horizontal pipe connecting east and west.
        L is a 90-degree bend connecting north and east.
        J is a 90-degree bend connecting north and west.
        7 is a 90-degree bend connecting south and west.
        F is a 90-degree bend connecting south and east.
        . is ground; there is no pipe in this tile.
        S is the starting position of the animal; there is a pipe on this tile, but your sketch doesn't show what shape the pipe has.

         */

        List<Character> topPipes = new ArrayList<>();
        List<Character> leftSidePipes = new ArrayList<>();
        List<Character> rightSidePipes = new ArrayList<>();
        List<Character> bottomSidePipes = new ArrayList<>();

        topPipes.add('|');
        topPipes.add('7');
        topPipes.add('F');

        leftSidePipes.add('-');
        leftSidePipes.add('L');
        leftSidePipes.add('F');

        rightSidePipes.add('-');
        rightSidePipes.add('J');
        rightSidePipes.add('7');

        bottomSidePipes.add('|');
        bottomSidePipes.add('L');
        bottomSidePipes.add('J');

        int pipeLocX;
        int pipeLocY;
        while (true) {
            pipeLocX = locX;
            pipeLocY = Math.max(0, locY - 1);
            char pipe = tab[pipeLocY][pipeLocX];
            System.out.println("pipe: " + pipe);
            if (topPipes.contains(pipe)) {
                break;
            }

            pipeLocX = Math.min(lines.getFirst().length() - 1, locX + 1);
            pipeLocY = locY;
            pipe = tab[pipeLocY][pipeLocX];
            System.out.println("pipe: " + pipe);
            if (rightSidePipes.contains(pipe)) {
                break;
            }

            pipeLocX = locX;
            pipeLocY = Math.min(lines.size() - 1, locY + 1);
            pipe = tab[pipeLocY][pipeLocX];
            System.out.println("pipe: " + pipe);
            if (bottomSidePipes.contains(pipe)) {
                break;
            }

            pipeLocX = Math.max(0, locX - 1);
            pipeLocY = locY;
            pipe = tab[pipeLocY][pipeLocX];
            System.out.println("pipe: " + pipe);
            if (leftSidePipes.contains(pipe)) {
                break;
            }
        }
        System.out.println("Start pipeLocX: " + pipeLocX + " pipeLocY: " + pipeLocY);

        char currentPipe = tab[pipeLocY][pipeLocX];
        int step = 1;
        int prevX = locX;
        int prevY = locY;
        System.out.println(step + ". " + currentPipe);
        while (currentPipe != 'S') {
            if (currentPipe == '-') {
                if (prevX < pipeLocX) {
                    prevX = pipeLocX;
                    pipeLocX++;
                } else {
                    prevX = pipeLocX;
                    prevY = pipeLocY;
                    pipeLocX--;
                }
            } else if (currentPipe == '|') {
                if (prevY < pipeLocY) {
                    prevY = pipeLocY;
                    pipeLocY++;
                } else {
                    prevX = pipeLocX;
                    prevY = pipeLocY;
                    pipeLocY--;
                }
            } else if (currentPipe == '7') {
                if (prevX < pipeLocX) {
                    prevX = pipeLocX;
                    pipeLocY++;
                } else {
                    prevX = pipeLocX;
                    prevY = pipeLocY;
                    pipeLocX--;
                }
            } else if (currentPipe == 'L') {
                if (prevX > pipeLocX) {
                    prevX = pipeLocX;
                    pipeLocY--;
                } else {
                    prevX = pipeLocX;
                    prevY = pipeLocY;
                    pipeLocX++;
                }
            } else if (currentPipe == 'F') {
                if (prevY > pipeLocY) {
                    prevY = pipeLocY;
                    pipeLocX++;
                } else {
                    prevX = pipeLocX;
                    prevY = pipeLocY;
                    pipeLocY++;
                }
            } else if (currentPipe == 'J') {
                if (prevY < pipeLocY) {
                    prevY = pipeLocY;
                    pipeLocX--;
                } else {
                    prevX = pipeLocX;
                    prevY = pipeLocY;
                    pipeLocY--;
                }
            }
            currentPipe = tab[pipeLocY][pipeLocX];
            step++;

            System.out.println(step + ". " + currentPipe);
        }
        System.out.println("----------------------");
        System.out.println("FINAL ANSWEAR: " + step / 2);
        System.out.println("----------------------");

//        System.out.println();
//        for (int i = 0; i < tab.length; i++) {
//            for (int j = 0; j < tab[i].length; j++) {
//                System.out.print(tab[i][j]);
//            }
//            System.out.println();
//        }

    }

    public void part2() throws FileNotFoundException {
        System.out.println("---------------");
        System.out.println("Day 10 - part 2\n");

        File file = new File(inputPath);
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();

        int count = 0;
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
            count++;
        }

        char[][] subGrid = new char[lines.getFirst().length()][count];
        System.out.println();
        for (char[] chars : subGrid) {
            Arrays.fill(chars, '.');
        }

        int locX = 0;
        int locY = 0;
        System.out.println(lines.getFirst().length() + " - " + count);
        char[][] mainGrid = new char[lines.getFirst().length()][count];
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                mainGrid[i][j] = line.charAt(j);
                System.out.print(line.charAt(j));
                if (line.charAt(j) == 'S') {
                    locY = i;
                    locX = j;
                }
            }
            System.out.println();
        }
        System.out.println("Start locX: " + locX + " - locY: " + locY);
        System.out.println();
        subGrid[locY][locX] = 'S';

        /*

        | is a vertical pipe connecting north and south.
        - is a horizontal pipe connecting east and west.
        L is a 90-degree bend connecting north and east.
        J is a 90-degree bend connecting north and west.
        7 is a 90-degree bend connecting south and west.
        F is a 90-degree bend connecting south and east.
        . is ground; there is no pipe in this tile.
        S is the starting position of the animal; there is a pipe on this tile, but your sketch doesn't show what shape the pipe has.

         */

        List<Character> topPipes = new ArrayList<>();
        List<Character> leftSidePipes = new ArrayList<>();
        List<Character> rightSidePipes = new ArrayList<>();
        List<Character> bottomSidePipes = new ArrayList<>();

        topPipes.add('|');
        topPipes.add('7');
        topPipes.add('F');

        leftSidePipes.add('-');
        leftSidePipes.add('L');
        leftSidePipes.add('F');

        rightSidePipes.add('-');
        rightSidePipes.add('J');
        rightSidePipes.add('7');

        bottomSidePipes.add('|');
        bottomSidePipes.add('L');
        bottomSidePipes.add('J');

        int pipeLocX;
        int pipeLocY;
        while (true) {
            pipeLocX = locX;
            pipeLocY = Math.max(0, locY - 1);
            char pipe = mainGrid[pipeLocY][pipeLocX];
            System.out.println("pipe: " + pipe);
            if (topPipes.contains(pipe)) {
                break;
            }

            pipeLocX = Math.min(lines.getFirst().length() - 1, locX + 1);
            pipeLocY = locY;
            pipe = mainGrid[pipeLocY][pipeLocX];
            System.out.println("pipe: " + pipe);
            if (rightSidePipes.contains(pipe)) {
                break;
            }

            pipeLocX = locX;
            pipeLocY = Math.min(lines.size() - 1, locY + 1);
            pipe = mainGrid[pipeLocY][pipeLocX];
            System.out.println("pipe: " + pipe);
            if (bottomSidePipes.contains(pipe)) {
                break;
            }

            pipeLocX = Math.max(0, locX - 1);
            pipeLocY = locY;
            pipe = mainGrid[pipeLocY][pipeLocX];
            System.out.println("pipe: " + pipe);
            if (leftSidePipes.contains(pipe)) {
                break;
            }
        }
        System.out.println("Start pipeLocX: " + pipeLocX + " pipeLocY: " + pipeLocY);

        char currentPipe = mainGrid[pipeLocY][pipeLocX];
        int prevX = locX;
        int prevY = locY;
        while (currentPipe != 'S') {
            if (currentPipe == '-') {
                subGrid[pipeLocY][pipeLocX] = '-';
                if (prevX < pipeLocX) {
                    prevX = pipeLocX;
                    pipeLocX++;
                } else {
                    prevX = pipeLocX;
                    prevY = pipeLocY;
                    pipeLocX--;
                }
            } else if (currentPipe == '|') {
                subGrid[pipeLocY][pipeLocX] = '|';
                if (prevY < pipeLocY) {
                    prevY = pipeLocY;
                    pipeLocY++;
                } else {
                    prevX = pipeLocX;
                    prevY = pipeLocY;
                    pipeLocY--;
                }
            } else if (currentPipe == '7') {
                subGrid[pipeLocY][pipeLocX] = '7';
                if (prevX < pipeLocX) {
                    prevX = pipeLocX;
                    pipeLocY++;
                } else {
                    prevX = pipeLocX;
                    prevY = pipeLocY;
                    pipeLocX--;
                }
            } else if (currentPipe == 'L') {
                subGrid[pipeLocY][pipeLocX] = 'L';
                if (prevX > pipeLocX) {
                    prevX = pipeLocX;
                    pipeLocY--;
                } else {
                    prevX = pipeLocX;
                    prevY = pipeLocY;
                    pipeLocX++;
                }
            } else if (currentPipe == 'F') {
                subGrid[pipeLocY][pipeLocX] = 'F';
                if (prevY > pipeLocY) {
                    prevY = pipeLocY;
                    pipeLocX++;
                } else {
                    prevX = pipeLocX;
                    prevY = pipeLocY;
                    pipeLocY++;
                }
            } else if (currentPipe == 'J') {
                subGrid[pipeLocY][pipeLocX] = 'J';
                if (prevY < pipeLocY) {
                    prevY = pipeLocY;
                    pipeLocX--;
                } else {
                    prevX = pipeLocX;
                    prevY = pipeLocY;
                    pipeLocY--;
                }
            }
            currentPipe = mainGrid[pipeLocY][pipeLocX];
        }
        System.out.println();
        System.out.println("SUBGRID");
        for (int i = 0; i < subGrid.length; i++) {
            for (int j = 0; j < subGrid[i].length; j++) {
                System.out.print(subGrid[i][j]);
            }
            System.out.println();
        }

        List<String> subList = new ArrayList<>();
        for (int i = 0; i < subGrid.length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < subGrid[i].length; j++) {
                stringBuilder.append(subGrid[i][j]);
            }
            subList.add(stringBuilder.toString());
        }

        /*
        | is a vertical pipe connecting north and south.
        - is a horizontal pipe connecting east and west.
        L is a 90-degree bend connecting north and east.
        J is a 90-degree bend connecting north and west.
        7 is a 90-degree bend connecting south and west.
        F is a 90-degree bend connecting south and east.
        . is ground; there is no pipe in this tile.
        S is the starting position of the animal; there is a pipe on this tile, but your sketch doesn't show what shape the pipe has.

        L7
        FJ

         */

        int outside = 0;
        int inside = 0;
        boolean isL = false;
        boolean isF = false;
        for (int i = 0; i < subList.size(); i++) {
            String line = subList.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '.') {
                    int walls = 0;
                    for (int k = j; k < line.length(); k++) {
                        if (line.charAt(k) == '|') {
                            walls++;
                        } else if (line.charAt(k) == 'L') {
                            isL = true;
                        } else if (line.charAt(k) == 'J' && isL) {
                            isL = false;
                        } else if (line.charAt(k) == '7' && isL) {
                            walls++;
                            isL = false;
                        } else if (line.charAt(k) == 'F') {
                            isF = true;
                        } else if (line.charAt(k) == '7' && isF) {
                            isF = false;
                        } else if (line.charAt(k) == 'J' && isF) {
                            walls++;
                            isF = false;
                        }
                    }
                    if (walls % 2 == 0) {
                        outside++;
                        subGrid[i][j] = '0';
                    } else {
                        inside++;
                        subGrid[i][j] = 'I';
                    }
                }
            }
        }
        System.out.println();
        System.out.println("CHANGED SUBGRID");
        for (int i = 0; i < subGrid.length; i++) {
            for (int j = 0; j < subGrid[i].length; j++) {
                System.out.print(subGrid[i][j]);
            }
            System.out.println();
        }

        System.out.println("----------------------");
        System.out.println("FINAL ANSWEAR - inside: " + inside + " outside: " + outside);
        System.out.println("----------------------");
    }
}
