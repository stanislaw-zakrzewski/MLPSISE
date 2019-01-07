package network_components;

import functions.LinearFunction;
import functions.SigmoidFunction;

import java.util.LinkedList;
import java.util.List;

public class Network extends LinkedList<Layer> {
    private static final SigmoidFunction sigmoidFunction = new SigmoidFunction();
    private static final LinearFunction linearFunction = new LinearFunction();

    public Network(int inputCount, int outputCount, int hiddenLayersCount, int neuronOnHuddenLayerCount) {
        add(new Layer(neuronOnHuddenLayerCount, inputCount, sigmoidFunction));
        for (int i = 0; i < hiddenLayersCount - 2; i++) {
            add(new Layer(neuronOnHuddenLayerCount, neuronOnHuddenLayerCount, sigmoidFunction));
        }
        add(new Layer(outputCount, outputCount, linearFunction));
    }

    public List<Double> work(List<Double> inputs) {
        for (Layer layer : this) {
            inputs = layer.work(inputs);
        }
        return inputs;
    }
}
