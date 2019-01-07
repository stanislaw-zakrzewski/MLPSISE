package functions;

public class LinearFunction implements Function {
    @Override
    public double functionValue(double x) {
        return x;
    }

    @Override
    public double derivativeValue(double x) {
        return 1;
    }
}
