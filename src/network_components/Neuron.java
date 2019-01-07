package network_components;

import functions.Function;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Neuron extends LinkedList<Double> {
    private double bias;
    private double fValue;
    private double dValue;
    private Function function;

    public Neuron(int weightCount, Function function) {
        this.function = function;
        Random random = new Random();
        for (int i = 0; i < weightCount; i++) {
            add(random.nextDouble());
        }
        bias = random.nextDouble();
    }

    public double work(List<Double> inputs) {
        double sum = 0;
        for (int i = 0; i < inputs.size(); i++) {
            sum += get(i) * inputs.get(i);
        }
        sum += bias;
        fValue = function.functionValue(sum);
        dValue = function.derivativeValue(sum);
        return fValue;
    }

    public double getfValue() {
        return fValue;
    }

    public double getdValue() {
        return dValue;
    }
}
