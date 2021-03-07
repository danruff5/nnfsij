package ca.danruff5.nnfsij.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Neuron {
    private final List<Double> weights;
    private final Double bias;
    
    public Neuron(Double bias, List<Double> weights) {
        this.bias = bias;
        this.weights = weights;
    }
    
    public Neuron(Double bias, Double... weights) {
        this.bias = bias;
        this.weights = Arrays.asList(weights);
    }
    
    public Neuron(String string) {
        String[] values = string.split(",");
        this.bias = Double.parseDouble(values[0]);
        this.weights = new ArrayList<>();
        for (int i = 1; i < values.length; i++) {
            this.weights.add(Double.parseDouble(values[i]));
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.bias.doubleValue());
        for (Double w : this.weights) {
            sb.append(',');
            sb.append(w.doubleValue());
        }
        return sb.toString();
    }
    
    
    public Double output(Double... inputs) {
        return this.outputInternal(Arrays.asList(inputs));
    }
    
    public Double output(List<Double> inputs) {
        return this.outputInternal(inputs);
    }
    
    private Double outputInternal(List<Double> inputs) {
        Double output = bias;
        for (int i = 0; i < inputs.size(); i++) {
            output += inputs.get(i) * weights.get(i);
        }
        
        return output;
    }
}
