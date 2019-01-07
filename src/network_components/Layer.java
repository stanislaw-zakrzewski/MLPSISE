package network_components;

import functions.Function;

import java.util.LinkedList;
import java.util.List;

public class Layer extends LinkedList<Neuron> {
    public Layer(int neuronCount, int inputCount, Function function) {
        for (int i = 0; i < neuronCount; i++) {
            add(new Neuron(inputCount, function));
        }
    }

    public List<Double> work(List<Double> inputs) {
        List<Double> ret = new LinkedList<>();
        for (Neuron neuron : this) {
            ret.add(neuron.work(inputs));
        }
        return ret;
    }
}
