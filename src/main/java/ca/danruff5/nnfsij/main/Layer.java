package ca.danruff5.nnfsij.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Layer {
    private final List<Neuron> neurons;
    
    public Layer(Neuron... neurons) {
        this.neurons = Arrays.asList(neurons);
    }
    
    public List<Double> output(List<Double> inputs) {
        List<Double> outputs = new ArrayList<>(this.neurons.size());
        for (Neuron n : this.neurons) {
            outputs.add(n.output(inputs));
        }
        return outputs;
    }
    
    public List<List<Double>> outputBatch(List<Double>... inputs) {
       return this.outputBatch(Arrays.asList(inputs));
    }
    
    public List<List<Double>> outputBatch(List<List<Double>> inputs) {
        List<List<Double>> outputs = new ArrayList<>(inputs.size());
        for (List<Double> i : inputs) {
            outputs.add(this.output(i));
        }
        return outputs;
    }
}
