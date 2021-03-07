package ca.danruff5.nnfsij.main.activations;

import java.util.ArrayList;
import java.util.List;

public class RectifiedLinearActivation implements ActivationFunction {

    @Override
    public Types getType() {
        return Types.RELU;
    }

    @Override
    public List<Double> activate(List<Double> values) {
        List<Double> outputs = new ArrayList<>(values.size());
        for (Double value : values) {
            outputs.add(Math.max(0, value));
        }
        return outputs;
    }
}
