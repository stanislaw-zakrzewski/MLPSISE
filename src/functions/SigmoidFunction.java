package functions;

public class SigmoidFunction implements Function {
    @Override
    public double functionValue(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    @Override
    public double derivativeValue(double x) {
        return functionValue(x) * (1- functionValue(x));
    }
}
