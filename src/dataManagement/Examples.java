package dataManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Examples extends LinkedList<Example> {
    private Random random = new Random();

    public Examples(String path) {
        try {
            Scanner scanner = new Scanner(new File(path));
            scanner.useLocale(Locale.US);
            while (scanner.hasNextDouble()) {
                List<Double> inputs = new ArrayList<>();
                inputs.add(scanner.nextDouble());
                List<Double> outputs = new ArrayList<>();
                outputs.add(scanner.nextDouble());
                add(new Example(inputs, outputs));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Example getRandom() {
        return get(random.nextInt(size()-1));
    }
}
