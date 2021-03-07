package ca.danruff5.nnfsij.main.activations;

import java.util.ArrayList;
import java.util.List;

public class StepActivation implements ActivationFunction {

    @Override
    public Types getType() {
        return ActivationFunction.Types.STEP;
    }
    
    @Override
    public List<Double> activate(List<Double> values) {
        List<Double> outputs = new ArrayList<>(values.size());
        for (Double value : values) {
            if (value > 0) {
                outputs.add(1.0);
            } else {
                outputs.add(0.0);
            }
        }
        return outputs;
    }
}
