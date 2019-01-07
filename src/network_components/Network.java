package network_components;

import functions.Function;
import functions.LinearFunction;
import functions.SigmoidFunction;

import java.util.LinkedList;
import java.util.List;

public class Network extends LinkedList<Layer> {
    private static final Function hiddenFunction = new SigmoidFunction();
    private static final Function outputFunction = new LinearFunction();

    public Network(int inputCount, int outputCount, int hiddenLayersCount, int neuronOnHiddenLayerCount) {
        add(new Layer(neuronOnHiddenLayerCount, inputCount, hiddenFunction));
        for (int i = 0; i < hiddenLayersCount - 2; i++) {
            add(new Layer(neuronOnHiddenLayerCount, neuronOnHiddenLayerCount, hiddenFunction));
        }
        add(new Layer(outputCount, neuronOnHiddenLayerCount, outputFunction));
    }

    public List<Double> work(List<Double> inputs) {
        for (Layer layer : this) {
            inputs = layer.work(inputs);
        }
        return inputs;
    }
}
