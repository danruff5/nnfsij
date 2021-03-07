package ca.danruff5.nnfsij.main.activations;

import java.util.Arrays;
import java.util.List;

public class SigmoidActivation implements ActivationFunction {

    @Override
    public Types getType() {
        return Types.SIGMOID;
    }

    @Override
    public List<Double> activate(List<Double> values) {
        return Arrays.asList(0.0);
    }
}