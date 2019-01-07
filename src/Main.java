import algorithms.BackPropagation;
import network_components.Network;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Network network = new Network(1,1,1,2);
        List<Double> inputs = new ArrayList<>();
        inputs.add(1.0);
        List<Double> desiredOutputs = new ArrayList<>();
        desiredOutputs.add(1.0);
        BackPropagation.train(network, inputs,desiredOutputs, 0.01);
    }
}
