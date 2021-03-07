package ca.danruff5.nnfsij.main.activations;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SoftMaxActivation implements ActivationFunction {

    @Override
    public Types getType() {
        return Types.SOFTMAX;
    }

    @Override
    public List<Double> activate(List<Double> values) {
        Double max = values.stream().max(Comparator.naturalOrder()).get();
        List<Double> exp_values = new ArrayList<>(values.size());
        
        for (Double v : values) {
            exp_values.add(Math.pow(Math.E, v - max));
        }
        
        Double sum = exp_values.stream().mapToDouble(Double::doubleValue).sum();
        List<Double> probabilities = new ArrayList<>(values.size());
        for (Double v : exp_values) {
            probabilities.add(v / sum);
        }
        
        return probabilities;
    }
}
