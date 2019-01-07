import algorithms.BackPropagation;
import dataManagement.Example;
import dataManagement.Examples;
import network_components.Network;
import plot.Plot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Network network = new Network(1,1,2,20);
        Examples examples = new Examples("data.txt");

        List<Double> errorX = new ArrayList<>();
        List<Double> errorY = new ArrayList<>();

        for(int i = 0; i < 100; i++) {
            errorX.add(Double.valueOf(Integer.toString(i)));
            Example e = examples.getRandom();
            Double d = BackPropagation.train(network, e.getInputs(), e.getOutputs(), 0.00001).get(0);
            errorY.add(d);
            System.out.println(d);
        }

        List<Double> inputs = new ArrayList<>();
        inputs.add(9.0);

        System.out.println(network.work(inputs));

        //Plot Drawing
        Plot plot = Plot.plot(null).yAxis("Błąd", Plot.axisOpts().range(0, 10)) .series("Błąd", Plot.data().xy(errorX, errorY), null);
        try {
            plot.save("ErrorPlot", "png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
