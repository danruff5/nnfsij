package ca.danruff5.nnfsij.main.activations;

public class ActivationFunctionFactory {
    
    public static ActivationFunction create(String function) {
        switch (function) {
            default:
            case "LINEAR":
                return new LinearActivation();
            case "STEP":
                return new StepActivation();
            case "RELU":
                return new RectifiedLinearActivation();
            case "SOFTMAX":
                return new SoftMaxActivation();
        }
                
    }
}
