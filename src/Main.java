import algorithms.BackPropagation;
import dataManagement.Example;
import dataManagement.Examples;
import network_components.Network;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Network network = new Network(1,1,1,2);
        Examples examples = new Examples("data.txt");

        for(int i = 0; i < 10000; i++) {
            Example e = examples.getRandom();
            BackPropagation.train(network, e.getInputs(), e.getOutputs(), 0.0001);
        }

        List<Double> inputs = new ArrayList<>();
        inputs.add(4.0);

        System.out.println(network.work(inputs));
    }
}
