package org.example.y2024.answers;

import org.example.DaterReader;
import org.example.y2024.Welcome2024;

import java.util.ArrayList;
import java.util.List;

public class Day02_2024 {

    final String examplePathPart1 = "src/main/java/org/example/y2024/data/d02/Day02_2024_part1_example.txt";
    final String inputPath = "src/main/java/org/example/y2024/data/d02/Day2_2024_input.txt";


    /*
    7 6 4 2 1: Bezpieczny, ponieważ poziomy wszystkie maleją o 1 lub 2.
    1 2 7 8 9: Niebezpieczny, ponieważ różnica między 2 a 7 wynosi 5.
    9 7 6 2 1: Niebezpieczny, ponieważ różnica między 6 a 2 wynosi 4.
    1 3 2 4 5: Niebezpieczny, ponieważ 1 → 3 rośnie, ale 3 → 2 maleje.
    8 6 4 4 1: Niebezpieczny, ponieważ 4 → 4 nie jest ani wzrostem, ani spadkiem.
    1 3 6 7 9: Bezpieczny, ponieważ poziomy wszystkie rosną o 1, 2 lub 3.
     */

    public void part1() {
        Welcome2024.welcome();
        var inputStringList = DaterReader.getInputData(inputPath);

        int safeLines = 0;
        for (String currentLine : inputStringList) {
            System.out.print(currentLine);

            String[] splitLine = currentLine.split(" ");

            boolean safe = true;
            int direction = checkDirection(splitLine, 0, 1);
            if (direction == 0) {
                safe = false;
            } else if (direction == 404) {
                safe = false;
            }

            if (safe) {
                for (int i = 2; i < splitLine.length; i++) {
                    int score = checkDirection(splitLine, i - 1, i);
                    if (score == 404) {
                        safe = false;
                        break;
                    }
                    if (score == 0) {
                        safe = false;
                        break;
                    }
                    if (score != direction) {
                        safe = false;
                        System.out.println(" - Unsafe because of changed direction.");
                        break;
                    }
                }
                if (safe) {
                    safeLines++;
                    System.out.println(" - Safe because the levels are all decreasing by 1, 2 or 3. Safe lines = " + safeLines);
                }
            }
        }
        System.out.println("=====================================");
        System.out.println("Ans Day02_2024_part1 = " + safeLines);
        System.out.println("=====================================");
    }

    public void part2() {
        Welcome2024.welcome();
        var inputStringList = DaterReader.getInputData(inputPath);

        int safeLines = 0;
        for (String currentLine : inputStringList) {
            System.out.print(currentLine);

            String[] splitLine = currentLine.split(" ");

            List<Integer> badIndex = new ArrayList<>();
            boolean safe = checkLine(splitLine, badIndex);
            if (!safe) {
                for (int i = 0; i < 2; i++) {
                    String[] lineWithRemovedLvl = new String[splitLine.length - 1];
                    for (int k = 0, j = 0; j < splitLine.length; j++) {
                        if (j != badIndex.get(i)) {
                            lineWithRemovedLvl[k++] = splitLine[j];
                        }
                    }

                    safe = checkLine(lineWithRemovedLvl, badIndex);
                    if (safe) {
                        break;
                    }
                }
            }
            if (safe) {
                safeLines++;
                System.out.println(" - Safe because the levels are all decreasing by 1, 2 or 3. Safe lines = " + safeLines);
            }
            badIndex.clear();
        }
        System.out.println("=====================================");
        System.out.println("Ans Day02_2024_part2 = " + safeLines);
        System.out.println("=====================================");
    }

    private int checkDirection(String[] splitLine, int x1, int x2) {
        int left = Integer.parseInt(splitLine[x1]);
        int right = Integer.parseInt(splitLine[x2]);
        int score = left - right;

        if (Math.abs(score) > 3) {  // CHANGE MORE THAN 3
            System.out.println(" - Unsafe because " + left + " " + right + " is change of " + score + ".");
            return 404;
        }

        if (score > 0) {
            // decrease
            return -1;
        } else if (score < 0) {
            // grow
            return 1;

        } else {
            // equal
            System.out.println(" - Unsafe because " + left + " " + right + " is neither an increase or a decrease. Score = " + score);
            return 0;
        }
    }

    private int checkDirection2(String[] splitLine, int x1, int x2) {
        int left = Integer.parseInt(splitLine[x1]);
        int right = Integer.parseInt(splitLine[x2]);
        int score = left - right;

//        boolean safe = true;
        if (Math.abs(score) > 3) {  // CHANGE MORE THAN 3
            System.out.println(" - Unsafe because " + left + " " + right + " is change of " + score + ".");
            return 404;
//            return false;
        }
        if (score > 0) {            // decrease
            return -1;
//            return true;
        } else if (score < 0) {     // grow
            return 1;
//            return true;
        } else {                    // equal
            System.out.println(" - Unsafe because " + left + " " + right + " is neither an increase or a decrease. Score = " + score);
            return 0;
//            return false;
        }
    }

    private boolean checkLine(String[] splitLine, List<Integer> badIndex) {
        int primeDirection = 0;
        for (int i = 1; i < splitLine.length; i++) {
            int direction = checkDirection2(splitLine, i - 1, i);
            if (direction == 404) {
                badIndex.add(i - 1);
                badIndex.add(i);
                return false;
            }
            if (direction == 0) {
                badIndex.add(i - 1);
                badIndex.add(i);
                return false;
            }
            if (i == 1) {
                primeDirection = direction;
            }
            if (direction != primeDirection) {
                System.out.println(" - Unsafe because of changed direction.");
                badIndex.add(i - 1);
                badIndex.add(i);
                return false;
            }
        }
        return true;
    }

    private void tryRemoveLevel(List<Integer> badIndexList) {
        for (int i = 0; i < badIndexList.size(); i++) {

        }

//        return "tryRemoveLevel";
    }
}
