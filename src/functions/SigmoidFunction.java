package functions;

public class SigmoidFunction implements Function {
    @Override
    public double functionValue(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    @Override
    public double derivativeValue(double x) {
        return (Math.exp(x) / Math.pow((Math.exp(x) + 1), 2));
    }
}
