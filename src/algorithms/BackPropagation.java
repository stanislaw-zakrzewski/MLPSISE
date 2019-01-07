package algorithms;

import dataManagement.Example;
import dataManagement.Examples;
import network_components.Layer;
import network_components.Network;
import network_components.Neuron;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BackPropagation {
    public static List<Double> train(Network network, Examples examples, double learningRate, double momentum) {
        Collections.shuffle(examples);
        List<Double> errors = new ArrayList<>();
        for(int i = 0; i < examples.getFirst().getOutputs().size(); i++) {
            errors.add(0.0);
        }
        for(Example e : examples) {
            network.work(e.getInputs());
            List<Double> temp = calculateErrors(network, e.getOutputs());
            for(int i = 0; i < temp.size(); i++) {
                errors.set(i, errors.get(i) + temp.get(i));
            }
            updateWeights(network, e.getInputs(), learningRate, momentum);
        }
        for(int i = 0; i < errors.size(); i++) {
            errors.set(i, errors.get(i)/examples.size());
        }
        return errors;
    }

    private static List<Double> calculateErrors(Network network, List<Double> desiredOutputs) {
        List<Double> errors = new ArrayList<>();
        Layer currentLayer = network.getLast();
        for (int i = 0; i < currentLayer.size(); i++) {
            double error = desiredOutputs.get(i) - currentLayer.get(i).getfValue();
            errors.add(Math.pow(error, 2));
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
        return errors;
    }

    private static void updateWeights(Network network, List<Double> inputs, double learning_rate, double momentum) {
        for(Layer layer : network) {
            List<Double> newInputs = new ArrayList<>();
            for(Neuron neuron : layer) {
                for(int i = 0; i < neuron.size(); i++) {
                    double momentumWeight = momentum * (neuron.get(i) - neuron.getPreviousWeights().get(i));
                    neuron.getPreviousWeights().set(i, neuron.get(i));
                    neuron.updateWeight(i, learning_rate * neuron.getError() * inputs.get(i));
                    neuron.updateWeight(i, momentumWeight);
                }
                double momentumBias = momentum * (neuron.getBias() - neuron.getPreviousBias());
                neuron.setPreviousBias(neuron.getBias());
                neuron.updateBias(learning_rate * neuron.getError());
                neuron.updateBias(momentumBias);
                newInputs.add(neuron.getfValue());
            }
            inputs = newInputs;
        }
    }
}
