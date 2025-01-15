package org.example.y2023.answer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day11 {
    final String examplePathPart1_0 = "src/main/java/org/example/y2023/data/Day11_part1.0_example.txt";
    final String inputPath = "src/main/java/org/example/y2023/data/Day11_input.txt";

    public void part1() throws FileNotFoundException {

        long start = System.currentTimeMillis();

        System.out.println("---------------");
        System.out.println("Day 11 - part 1\n");

        File file = new File(inputPath);
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();

        int count = 0;
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
            count++;
        }

        System.out.println();
        System.out.println("MAIN grid");
        for (int i = 0; i < lines.size(); i++) {
            System.out.println(lines.get(i));
        }

        // expand horizontal
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            int countOfGalaxyInHorizon = 0;
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '#') {
                    countOfGalaxyInHorizon++;
                }
            }
            if (countOfGalaxyInHorizon == 0) {
                count++;
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0; j < line.length(); j++) {
                    stringBuilder.append('.');
                }
                lines.add(i, stringBuilder.toString());
                i++;
            }
        }

        System.out.println();
        System.out.println("EXPANDED horizontal");
        for (int i = 0; i < lines.size(); i++) {
            System.out.println(lines.get(i));
        }

        // expand vertical
        for (int i = 0; i < lines.getFirst().length(); i++) {
            int countOfGalaxyInVertical = 0;
            for (int j = 0; j < lines.size(); j++) {
                if (lines.get(j).charAt(i) == '#') {
                    countOfGalaxyInVertical++;
                }
            }
            if (countOfGalaxyInVertical == 0) {
                for (int j = 0; j < lines.size(); j++) {
                    lines.set(j, lines.get(j).substring(0, i) + "." + lines.get(j).substring(i));
                }
                i++;
            }
        }

        System.out.println();
        System.out.println("EXPANDED vertical");
        for (int i = 0; i < lines.size(); i++) {
            System.out.println(lines.get(i));
        }

        char[][] extendedGrid = new char[count][lines.getFirst().length()];
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                extendedGrid[y][x] = line.charAt(x);
            }
        }
        System.out.println();
        System.out.println("TAB");
        for (int y = 0; y < extendedGrid.length; y++) {
            for (int x = 0; x < extendedGrid[y].length; x++) {
                System.out.print(extendedGrid[y][x]);
            }
            System.out.println();
        }

        Map<Integer, String> coordinates = new HashMap<>();
        int countOfGalaxy = 0;
        for (int y = 0; y < extendedGrid.length; y++) {
            for (int x = 0; x < extendedGrid[y].length; x++) {
                if (extendedGrid[y][x] == '#') {
                    countOfGalaxy++;
                    coordinates.put(countOfGalaxy, y + "," + x);
                }
            }
        }
        System.out.println();
        System.out.println("coordinates: " + coordinates);

        int pairs = 0;
        for (int i = 1; i < countOfGalaxy; i++) {
            pairs += i;
        }
        System.out.println();
        System.out.println("Number of pairs: " + pairs);
        System.out.println();

        int countOfPairs = countOfGalaxy--;
        int sum = 0;
        for (int i = 1; i <= countOfGalaxy; i++) {
            for (int j = i + 1; j <= countOfPairs; j++) {
                String cord1 = coordinates.get(i);
                String cord2 = coordinates.get(j);

                String[] cord1Split = cord1.split(",");
                String[] cord2Split = cord2.split(",");
                int x1 = Integer.parseInt(cord1Split[1]);
                int y1 = Integer.parseInt(cord1Split[0]);
                int x2 = Integer.parseInt(cord2Split[1]);
                int y2 = Integer.parseInt(cord2Split[0]);

                int moves = y2 - y1;
                if (x2 > x1) {
                    moves += x2 - x1;
                } else {
                    moves += x1 - x2;
                }
                sum += moves;
                System.out.println(" - Between galaxy " + i + " and galaxy " + j + ": " + moves);
            }
        }

        long finish = System.currentTimeMillis();
        long time = finish - start;

        System.out.println("----------------------");
        System.out.println("FINAL ANSWEAR: " + sum + " time: " + time + "ms");
        System.out.println("----------------------");
    }

    public void part2() throws FileNotFoundException {

        long start = System.currentTimeMillis();

        System.out.println("---------------");
        System.out.println("Day 11 - part 2\n");

        File file = new File(inputPath);
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();

        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }

        System.out.println();
        System.out.println("MAIN grid");
        for (int i = 0; i < lines.size(); i++) {
            System.out.println(lines.get(i));
        }

        int timesBigger = 999999;
        Map<Integer, String> coordinates = new HashMap<>();

        // expand horizontal
        int horizontalExtencion = 0;
        int countOfGalaxy = 0;
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            int countOfGalaxyInHorizon = 0;
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == '#') {
                    countOfGalaxy++;
                    countOfGalaxyInHorizon++;
                    coordinates.put(countOfGalaxy, y + horizontalExtencion + "," + x);
                }
            }
            if (countOfGalaxyInHorizon == 0) {
                horizontalExtencion += timesBigger;
            }
        }

        // expand vertical
        List<Integer> verticalToExpand = new ArrayList<>();
        for (int x = 0; x < lines.getFirst().length(); x++) {
            int countOfGalaxyInVertical = 0;
            for (int y = 0; y < lines.size(); y++) {
                if (lines.get(y).charAt(x) == '#') {
                    countOfGalaxyInVertical++;
                }
            }
            if (countOfGalaxyInVertical == 0) {
                verticalToExpand.add(x);
            }
        }
        System.out.println("verticalToExpand: " + verticalToExpand);

        for (int i = 1; i <= coordinates.size(); i++) {
            String[] cordSplit = coordinates.get(i).split(",");
            int y = Integer.parseInt(cordSplit[0]);
            int x = Integer.parseInt(cordSplit[1]);
            for (int j = verticalToExpand.size() - 1; j >= 0; j--) {
                if (x > verticalToExpand.get(j)) {
                    x += timesBigger;
                    coordinates.put(i, y + "," + x);
                }
            }
        }

        System.out.println();
        System.out.println("coordinates: " + coordinates);

        int pairs = 0;
        for (int i = 1; i < countOfGalaxy; i++) {
            pairs += i;
        }
        System.out.println();
        System.out.println("Number of pairs: " + pairs);
        System.out.println();

        int countOfPairs = countOfGalaxy--;
        long sum = 0;
        for (int i = 1; i <= countOfGalaxy; i++) {
            for (int j = i + 1; j <= countOfPairs; j++) {
                String cord1 = coordinates.get(i);
                String cord2 = coordinates.get(j);

                String[] cord1Split = cord1.split(",");
                String[] cord2Split = cord2.split(",");
                int x1 = Integer.parseInt(cord1Split[1]);
                int y1 = Integer.parseInt(cord1Split[0]);
                int x2 = Integer.parseInt(cord2Split[1]);
                int y2 = Integer.parseInt(cord2Split[0]);

                int moves = y2 - y1;
                if (x2 > x1) {
                    moves += x2 - x1;
                } else {
                    moves += x1 - x2;
                }
                System.out.println(moves);
                sum += moves;
            }
        }

        long finish = System.currentTimeMillis();
        long time = finish - start;

        System.out.println("----------------------");
        System.out.println("FINAL ANSWEAR: " + sum + " time: " + time + "ms");
        System.out.println("----------------------");
    }
}
