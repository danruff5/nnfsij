package ca.danruff5.nnfsij.main.activations;

import java.util.List;

public interface ActivationFunction {
    public static enum Types {
        LINEAR, STEP, SIGMOID, RELU, SOFTMAX
    }
    
    Types getType();
    
    List<Double> activate(List<Double> values);
}
