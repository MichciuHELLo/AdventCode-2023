package org.example.y2023.answer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day7 {

    final String examplePathPart1 = "src/main/java/org/example/y2023/data/Day7_part1_example.txt";
    final String inputPath = "src/main/java/org/example/y2023/data/Day7_input.txt";

    /*

    A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, 2

    Five of a kind: AAAAA
    Four of a kind: AA8AA
    Full house: 23332
    Three of a kind: TTT98
    Two pair: 23432
    One pair: A23A4
    High card: 23456

    */

    public void part1() throws FileNotFoundException {
        System.out.println("---------------");
        System.out.println("Day 7 - part 1\n");

        File file = new File(inputPath);
        Scanner scanner = new Scanner(file);

        List<String> fiveKind = new ArrayList<>();
        List<String> fourKind = new ArrayList<>();
        List<String> fullHouse = new ArrayList<>();
        List<String> threeKind = new ArrayList<>();
        List<String> twoPair = new ArrayList<>();
        List<String> onePair = new ArrayList<>();
        List<String> highCard = new ArrayList<>();

        HashMap<Character, Integer> cardStrengthMap = getCharacterIntegerHashMap();

        while (scanner.hasNext()) {

            boolean isFiveKind = false;
            boolean isFourKind = false;
            boolean isFullHouse = false;
            boolean isThreeKind = false;
            boolean isTwoPair = false;
            boolean isOnePair = false;

            String line = scanner.nextLine();

            var splitLine = line.split(" ");
            System.out.print(splitLine[0] + " - ");
            char[] tempArray = splitLine[0].toCharArray();
            Arrays.sort(tempArray);

            StringBuilder stringBuilder = new StringBuilder();
            for (char c : tempArray) {
                stringBuilder.append(c);
            }
            String hand = stringBuilder.toString();

            for (int i = 0; i < 5; ) {
                int count = 0;
                for (int j = i; j < 5; j++) {
                    if (hand.charAt(i) == hand.charAt(j)) {
                        count++;
                    }
                }
                i += count;

                if (count == 2 && isOnePair) {
                    isOnePair = false;
                    isTwoPair = true;
                }
                if (count == 2 && !isTwoPair) {
                    isOnePair = true;
                }
                if (count == 3) {
                    isThreeKind = true;
                }
                if (isOnePair && isThreeKind) {
                    isOnePair = false;
                    isThreeKind = false;
                    isFullHouse = true;
                }
                if (count == 4) {
                    isFourKind = true;
                }
                if (count == 5) {
                    isFiveKind = true;
                }
            }

            if (isOnePair) {
                System.out.println("onePair");
                sortArray(onePair, line, cardStrengthMap);
            } else if (isTwoPair) {
                System.out.println("twoPair");
                sortArray(twoPair, line, cardStrengthMap);
            } else if (isThreeKind) {
                sortArray(threeKind, line, cardStrengthMap);
                System.out.println("threeKind");
            } else if (isFullHouse) {
                sortArray(fullHouse, line, cardStrengthMap);
                System.out.println("fullHouse");
            } else if (isFourKind) {
                sortArray(fourKind, line, cardStrengthMap);
                System.out.println("fourKind");
            } else if (isFiveKind) {
                sortArray(fiveKind, line, cardStrengthMap);
                System.out.println("fiveKind");
            } else {
                sortArray(highCard, line, cardStrengthMap);
                System.out.println("highCard");
            }

        }

        List<String> allHands = new ArrayList<>();

        highCard = highCard.reversed();
        onePair = onePair.reversed();
        twoPair = twoPair.reversed();
        threeKind = threeKind.reversed();
        fullHouse = fullHouse.reversed();
        fourKind = fourKind.reversed();
        fiveKind = fiveKind.reversed();

        System.out.println();
        System.out.println("onePair - " + onePair);
        System.out.println("twoPair - " + twoPair);
        System.out.println("threeKind - " + threeKind);
        System.out.println("fullHouse - " + fullHouse);
        System.out.println("fourKind - " + fourKind);
        System.out.println("fiveKind - " + fiveKind);
        System.out.println("highCard - " + highCard);

        allHands.addAll(highCard);
        allHands.addAll(onePair);
        allHands.addAll(twoPair);
        allHands.addAll(threeKind);
        allHands.addAll(fullHouse);
        allHands.addAll(fourKind);
        allHands.addAll(fiveKind);

        int sum = 0;
        System.out.println();
        for (int i = 0; i < allHands.size(); i++) {
            String[] splitHand = allHands.get(i).split(" ");
            int power = Integer.parseInt(splitHand[1]);

            System.out.print((i + 1) + " * " + power + " = " + (i + 1) * power + " sum = ");
            sum += (i + 1) * power;
            System.out.println(sum);
        }
        System.out.println();
        System.out.println("----------------------");
        System.out.println("FINAL ANSWEAR: " + sum);
        System.out.println("----------------------");
    }

    public void part2() throws FileNotFoundException {
        System.out.println("---------------");
        System.out.println("Day 7 - part 2\n");

        File file = new File(inputPath);
        Scanner scanner = new Scanner(file);

        List<String> fiveKind = new ArrayList<>();
        List<String> fourKind = new ArrayList<>();
        List<String> fullHouse = new ArrayList<>();
        List<String> threeKind = new ArrayList<>();
        List<String> twoPair = new ArrayList<>();
        List<String> onePair = new ArrayList<>();
        List<String> highCard = new ArrayList<>();

        HashMap<Character, Integer> cardStrengthMap = getCharacterIntegerHashMapForPart2();

        while (scanner.hasNext()) {
            boolean isFiveKind = false;
            boolean isFourKind = false;
            boolean isFullHouse = false;
            boolean isThreeKind = false;
            boolean isTwoPair = false;
            boolean isOnePair = false;

            String line = scanner.nextLine();

            var splitLine = line.split(" ");
            System.out.print(splitLine[0] + " - ");
            char[] tempArray = splitLine[0].toCharArray();
            Arrays.sort(tempArray);

            StringBuilder stringBuilder = new StringBuilder();
            for (char c : tempArray) {
                stringBuilder.append(c);
            }
            String hand = stringBuilder.toString();
            System.out.println("hand: " + hand + " - ");

            int max = 0;
            char maxChar = 0;
            for (int i = 0; i < 5; ) {
                int count = 0;
                for (int j = i; j < 5; j++) {
                    if (hand.charAt(i) == hand.charAt(j)) {
                        count++;
                    }
                }
                if (max < count && hand.charAt(i) != 'J') {
                    max = count;
                    maxChar = hand.charAt(i);
                }
                i += count;
            }

            String tempHand = hand.replace('J', maxChar);
            tempArray = tempHand.toCharArray();
            Arrays.sort(tempArray);

            stringBuilder = new StringBuilder();
            for (char c : tempArray) {
                stringBuilder.append(c);
            }
            tempHand = stringBuilder.toString();

            System.out.println("hand - " + hand + " | " + tempHand + " - tempHand");

            for (int i = 0; i < 5; ) {
                var x = tempHand;
                int count = 0;
                for (int j = i; j < 5; j++) {
                    if (tempHand.charAt(i) == tempHand.charAt(j)) {
                        count++;
                    }
                }
                i += count;

                if (count == 2 && isOnePair) {
                    isOnePair = false;
                    isTwoPair = true;
                }
                if (count == 2 && !isTwoPair) {
                    isOnePair = true;
                }
                if (count == 3) {
                    isThreeKind = true;
                }
                if (isOnePair && isThreeKind) {
                    isOnePair = false;
                    isThreeKind = false;
                    isFullHouse = true;
                }
                if (count == 4) {
                    isFourKind = true;
                }
                if (count == 5) {
                    isFiveKind = true;
                }
            }

            if (isOnePair) {
                System.out.println("onePair\n");
                sortArray(onePair, line, cardStrengthMap);
            } else if (isTwoPair) {
                System.out.println("twoPair\n");
                sortArray(twoPair, line, cardStrengthMap);
            } else if (isThreeKind) {
                sortArray(threeKind, line, cardStrengthMap);
                System.out.println("threeKind\n");
            } else if (isFullHouse) {
                sortArray(fullHouse, line, cardStrengthMap);
                System.out.println("fullHouse\n");
            } else if (isFourKind) {
                sortArray(fourKind, line, cardStrengthMap);
                System.out.println("fourKind\n");
            } else if (isFiveKind) {
                sortArray(fiveKind, line, cardStrengthMap);
                System.out.println("fiveKind\n");
            } else {
                sortArray(highCard, line, cardStrengthMap);
                System.out.println("highCard\n");
            }

        }

        List<String> allHands = new ArrayList<>();

        highCard = highCard.reversed();
        onePair = onePair.reversed();
        twoPair = twoPair.reversed();
        threeKind = threeKind.reversed();
        fullHouse = fullHouse.reversed();
        fourKind = fourKind.reversed();
        fiveKind = fiveKind.reversed();

        System.out.println();
        System.out.println("onePair - " + onePair);
        System.out.println("twoPair - " + twoPair);
        System.out.println("threeKind - " + threeKind);
        System.out.println("fullHouse - " + fullHouse);
        System.out.println("fourKind - " + fourKind);
        System.out.println("fiveKind - " + fiveKind);
        System.out.println("highCard - " + highCard);

        allHands.addAll(highCard);
        allHands.addAll(onePair);
        allHands.addAll(twoPair);
        allHands.addAll(threeKind);
        allHands.addAll(fullHouse);
        allHands.addAll(fourKind);
        allHands.addAll(fiveKind);

        int sum = 0;
        System.out.println();
        for (int i = 0; i < allHands.size(); i++) {
            String[] splitHand = allHands.get(i).split(" ");
            int power = Integer.parseInt(splitHand[1]);

            System.out.print((i + 1) + " * " + power + " = " + (i + 1) * power + " sum = ");
            sum += (i + 1) * power;
            System.out.println(sum);
        }
        System.out.println();
        System.out.println("----------------------");
        System.out.println("FINAL ANSWEAR: " + sum);
        System.out.println("----------------------");
    }


    private static HashMap<Character, Integer> getCharacterIntegerHashMap() {
        HashMap<Character, Integer> cardStrengthMap = new HashMap<>();
        cardStrengthMap.put('A', 13);
        cardStrengthMap.put('K', 12);
        cardStrengthMap.put('Q', 11);
        cardStrengthMap.put('J', 10);
        cardStrengthMap.put('T', 9);
        cardStrengthMap.put('9', 8);
        cardStrengthMap.put('8', 7);
        cardStrengthMap.put('7', 6);
        cardStrengthMap.put('6', 5);
        cardStrengthMap.put('5', 4);
        cardStrengthMap.put('4', 3);
        cardStrengthMap.put('3', 2);
        cardStrengthMap.put('2', 1);
        return cardStrengthMap;
    }

    private static HashMap<Character, Integer> getCharacterIntegerHashMapForPart2() {
        HashMap<Character, Integer> cardStrengthMap = new HashMap<>();
        cardStrengthMap.put('A', 13);
        cardStrengthMap.put('K', 12);
        cardStrengthMap.put('Q', 11);
        cardStrengthMap.put('J', 0);
        cardStrengthMap.put('T', 9);
        cardStrengthMap.put('9', 8);
        cardStrengthMap.put('8', 7);
        cardStrengthMap.put('7', 6);
        cardStrengthMap.put('6', 5);
        cardStrengthMap.put('5', 4);
        cardStrengthMap.put('4', 3);
        cardStrengthMap.put('3', 2);
        cardStrengthMap.put('2', 1);
        return cardStrengthMap;
    }

    private void sortArray(List<String> goalArray, String line, Map<Character, Integer> cardStrengthMap) {
        boolean added = false;
        if (!goalArray.isEmpty()) {
            for (int i = 0; i < goalArray.size(); i++) {
                for (int j = 0; j < 5; j++) {
                    if (cardStrengthMap.get(line.charAt(j)) > cardStrengthMap.get(goalArray.get(i).charAt(j))) {
                        goalArray.add(i, line);
                        added = true;
                        break;
                    } else if (cardStrengthMap.get(line.charAt(j)) < cardStrengthMap.get(goalArray.get(i).charAt(j))) {
                        break;
                    }
                }
                if (added) {
                    break;
                }
            }
        }
        if (goalArray.isEmpty() || !added) {
            goalArray.add(line);
        }
    }
}
