import algorithms.BackPropagation;
import dataManagement.Example;
import dataManagement.Examples;
import network_components.Network;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Network network = new Network(1,1,2,20);
        Examples examples = new Examples("data.txt");

        for(int i = 0; i < 100; i++) {
            Example e = examples.getRandom();
            System.out.println(BackPropagation.train(network, e.getInputs(), e.getOutputs(), 0.01).get(0));
        }

        List<Double> inputs = new ArrayList<>();
        inputs.add(49.0);

        System.out.println(network.work(inputs));
    }
}
