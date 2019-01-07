package dataManagement;

import functions.FunctionToApproximate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DatasetGenerator {
    private static final int examplesCount = 1000;

    public static void main(String[] args) {
        Random random = new Random();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"));
            for(int i = 0; i < examplesCount; i++) {
                double x = (random.nextDouble()*99) + 1;
                writer.write(x + " " + FunctionToApproximate.gatValue(x));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
