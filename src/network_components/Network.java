package network_components;

import functions.Function;
import functions.LinearFunction;

import java.util.LinkedList;
import java.util.List;

public class Network extends LinkedList<Layer> {
    private static final Function hiddenFunction = new LinearFunction();
    private static final Function outpurFunction = new LinearFunction();

    public Network(int inputCount, int outputCount, int hiddenLayersCount, int neuronOnHuddenLayerCount) {
        add(new Layer(neuronOnHuddenLayerCount, inputCount, hiddenFunction));
        for (int i = 0; i < hiddenLayersCount - 2; i++) {
            add(new Layer(neuronOnHuddenLayerCount, neuronOnHuddenLayerCount, hiddenFunction));
        }
        add(new Layer(outputCount, neuronOnHuddenLayerCount, outpurFunction));
    }

    public List<Double> work(List<Double> inputs) {
        for (Layer layer : this) {
            inputs = layer.work(inputs);
        }
        return inputs;
    }
}
