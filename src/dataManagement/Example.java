package dataManagement;

import java.util.List;

public class Example {
    private List<Double> inputs;
    private List<Double> outputs;

    public Example(List<Double> inputs, List<Double> outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public List<Double> getInputs() {
        return inputs;
    }

    public List<Double> getOutputs() {
        return outputs;
    }
}
