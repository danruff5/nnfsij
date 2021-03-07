package ca.danruff5.nnfsij.main.activations;

import java.util.List;

public class LinearActivation implements ActivationFunction {

    @Override
    public Types getType() {
        return Types.LINEAR;
    }

    @Override
    public List<Double> activate(List<Double> values) {
        return values;
    }
}
