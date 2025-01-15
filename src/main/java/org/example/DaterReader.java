package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DaterReader {

    public static List<String> getInputData(String inputPath) {

        List<String> inputStringList = new ArrayList<>();
        try {
            File file = new File(inputPath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                inputStringList.add(scanner.nextLine());
            }

        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Problem occurred with Scanner: " + fileNotFoundException);
        }

        return inputStringList;
    }

}
