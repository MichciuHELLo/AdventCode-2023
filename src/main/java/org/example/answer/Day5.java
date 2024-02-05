package org.example.answer;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day5 {

    final String examplePathPart1 = "src/main/java/org/example/data/Day5_part1_example.txt";
    final String inputPath = "src/main/java/org/example/data/Day5_input.txt";

    public void part1() throws FileNotFoundException {
        System.out.println("---------------");
        System.out.println("Day 5 - part 1\n");

        File file = new File(inputPath);
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();

        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }

        List<BigInteger> seeds = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        var splitSeeds = lines.getFirst().split(":");
        splitSeeds[1] += " ";
        for (int i = 0; i < splitSeeds[1].length(); i++) {
            if ((int) splitSeeds[1].charAt(i) >= 48 && (int) splitSeeds[1].charAt(i) <= 57) {
                stringBuilder.append(splitSeeds[1].charAt(i));
            } else if (splitSeeds[1].charAt(i) == ' ' && !stringBuilder.isEmpty()) {
                seeds.add(new BigInteger(stringBuilder.toString()));
                stringBuilder = new StringBuilder();
            }
        }
        System.out.println("seeds bef: " + seeds);

        for (int i = 0; i < seeds.size(); i++) {
            boolean foundSeed = false;
            for (int j = 3; j < lines.size(); j++) {
                String line = lines.get(j);
                BigInteger soil1 = new BigInteger("1");
                BigInteger soil2 = new BigInteger("1");
                BigInteger soil3 = new BigInteger("1");
                int spaces = 0;
                if (!line.isEmpty() && (int) line.charAt(0) >= 48 && (int) line.charAt(0) <= 57 && !foundSeed) {
                    line += " ";
                    stringBuilder = new StringBuilder();
                    for (int k = 0; k < line.length(); k++) {
                        if ((int) line.charAt(k) >= 48 && (int) line.charAt(k) <= 57) {
                            stringBuilder.append(line.charAt(k));
                        } else if (line.charAt(k) == ' ') {
                            spaces++;
                            if (spaces == 1) {
                                soil1 = new BigInteger(stringBuilder.toString());
                            } else if (spaces == 2) {
                                soil2 = new BigInteger(stringBuilder.toString());
                            } else if (spaces == 3) {
                                soil3 = new BigInteger(stringBuilder.toString());
                            }
                            stringBuilder = new StringBuilder();
                        }
                    }

                    BigInteger seed = seeds.get(i);
                    if (seed.compareTo(soil2) >= 0 && seed.compareTo(soil2.add(soil3).subtract(BigInteger.ONE)) <= 0) {
                        seeds.set(i, seed.add(soil1).subtract(soil2));
                        foundSeed = true;
                    }
                } else if (line.isEmpty()) {
                    foundSeed = false;
                }
            }
        }
        System.out.println("seeds aft: " + seeds);

        BigInteger minLocation = BigInteger.valueOf(Integer.MAX_VALUE);
        for (int i = 0; i < seeds.size(); i++) {
            if (seeds.get(i).compareTo(minLocation) < 0) {
                minLocation = seeds.get(i);
            }
        }
        System.out.println();
        System.out.println("----------------------");
        System.out.println("FINAL ANSWEAR: " + minLocation);
        System.out.println("----------------------");
    }

    public void part2() throws FileNotFoundException {
        System.out.println("---------------");
        System.out.println("Day 5 - part 2\n");

        File file = new File(inputPath);
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();

        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }

        int pair = 0;
//        List<BigInteger> seeds = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        var splitSeeds = lines.getFirst().split(":");
        splitSeeds[1] += " ";
        BigInteger previousNr = new BigInteger("1");
        BigInteger minLocation = BigInteger.valueOf(Long.MAX_VALUE);
        for (int i = 1; i < splitSeeds[1].length(); i++) {
            if ((int) splitSeeds[1].charAt(i) >= 48 && (int) splitSeeds[1].charAt(i) <= 57) {
                stringBuilder.append(splitSeeds[1].charAt(i));
            } else if (splitSeeds[1].charAt(i) == ' ' && !stringBuilder.isEmpty()) {
                pair++;
                if (pair % 2 == 0) {
//                    BigInteger j = BigInteger.ONE;
                    BigInteger j = BigInteger.ZERO;
                    BigInteger bi = new BigInteger(stringBuilder.toString());
                    System.out.println();
                    System.out.println("previousNr: " + previousNr);
                    System.out.println();
                    while (j.compareTo(bi) < 0) {
//                        seeds.add(previousNr.add(j));
                        BigInteger added = previousNr.add(j);
                        System.out.println(added);
                        BigInteger location = findLocation(lines, added);
                        if (location.compareTo(minLocation) < 0) {
                            minLocation = location;
                        }

                        System.out.print("adding: " + previousNr.add(j));
                        System.out.print(" - location: " + location);
                        System.out.println(" - minLocation: " + minLocation);

                        j = j.add(BigInteger.ONE);
                    }
                } else {
                    previousNr = new BigInteger(stringBuilder.toString());
//                    seeds.add(new BigInteger(stringBuilder.toString()));
                }
                stringBuilder = new StringBuilder();
            }
        }
        System.out.println();
        System.out.println("----------------------");
        System.out.println("FINAL ANSWEAR: " + minLocation);
        System.out.println("----------------------");
    }

    private BigInteger findLocation(List<String> lines, BigInteger seed) {
        boolean foundSeed = false;
        BigInteger location = new BigInteger("1");
        BigInteger soil1 = new BigInteger("1");
        BigInteger soil2 = new BigInteger("1");
        BigInteger soil3 = new BigInteger("1");
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 3; j < lines.size(); j++) {
            String line = lines.get(j);
            int spaces = 0;
            if (!line.isEmpty() && (int) line.charAt(0) >= 48 && (int) line.charAt(0) <= 57 && !foundSeed) {
                line += " ";
                for (int k = 0; k < line.length(); k++) {
                    if ((int) line.charAt(k) >= 48 && (int) line.charAt(k) <= 57) {
                        stringBuilder.append(line.charAt(k));
                    } else if (line.charAt(k) == ' ') {
                        spaces++;
                        if (spaces == 1) {
                            soil1 = new BigInteger(stringBuilder.toString());
                        } else if (spaces == 2) {
                            soil2 = new BigInteger(stringBuilder.toString());
                        } else if (spaces == 3) {
                            soil3 = new BigInteger(stringBuilder.toString());
                        }
                        stringBuilder = new StringBuilder();
                    }
                }

//                BigInteger seed = seeds.get(i);
                if (seed.compareTo(soil2) >= 0 && seed.compareTo(soil2.add(soil3).subtract(BigInteger.ONE)) <= 0) {
//                    System.out.println("--- " + soil1 + ", " + soil2 + ", " + soil3 + " ---");
                    location = seed.add(soil1).subtract(soil2);
                    seed = location;
//                    System.out.println((j+1) + ". answer: " + location);
                    foundSeed = true;
                }
            } else if (line.isEmpty()) {
                foundSeed = false;
            }
        }
        return location;
    }

}
