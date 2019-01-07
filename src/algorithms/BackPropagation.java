package algorithms;

import network_components.Layer;
import network_components.Network;
import network_components.Neuron;

import java.util.ArrayList;
import java.util.List;

public class BackPropagation {
    public static void train(Network network, List<Double> inputs, List<Double> desiredOutputs, double learningRate) {
        network.work(inputs);
        calculateErrors(network, desiredOutputs);
        updateWeights(network, inputs, learningRate);
    }

    private static void calculateErrors(Network network, List<Double> desiredOutputs) {
        Layer currentLayer = network.getLast();
        for (int i = 0; i < currentLayer.size(); i++) {
            double error = desiredOutputs.get(i) - currentLayer.get(i).getfValue();
            currentLayer.get(i).setError(error * currentLayer.get(i).getdValue());
        }

        Layer nextLayer = currentLayer;
        for (int i = network.size() - 2; i > 0; i--) {
            currentLayer = network.get(i);
            for (int j = 0; j < currentLayer.size(); j++) {
                double error = 0;
                for (Neuron neuron : nextLayer) {
                    error += neuron.get(j) * neuron.getError();
                }
                currentLayer.get(j).setError(error * currentLayer.get(j).getdValue());
            }
        }
    }

    private static void updateWeights(Network network, List<Double> inputs, double learning_rate) {
        for(Layer layer : network) {
            List<Double> newInputs = new ArrayList<>();
            for(Neuron neuron : layer) {
                for(int i = 0; i < neuron.size(); i++) {
                    neuron.updateWeight(i, 2 * learning_rate * neuron.getError() * inputs.get(i));
                }
                neuron.updateBias(2 * learning_rate * neuron.getError());
                newInputs.add(neuron.getfValue());
            }
            inputs = newInputs;
        }
    }
}
