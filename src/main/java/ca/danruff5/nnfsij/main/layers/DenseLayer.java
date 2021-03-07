package ca.danruff5.nnfsij.main.layers;

import ca.danruff5.nnfsij.main.Neuron;
import ca.danruff5.nnfsij.main.activations.ActivationFunction;
import ca.danruff5.nnfsij.main.activations.ActivationFunctionFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DenseLayer {
    
    private final List<Neuron> neurons;
    private final ActivationFunction function;
    
    public DenseLayer (int numInputs, int numNeurons) {
        this(ActivationFunctionFactory.create("VALUE"), numInputs, numNeurons);
    }
    
    public DenseLayer (ActivationFunction function, int numInputs, int numNeurons) {
        this.neurons = new ArrayList<>(numNeurons);
        Random r = new Random(0);
        this.function = function;
        
        for (int n = 0; n < numNeurons; n++) {
            List<Double> values = r.doubles(numInputs, -1.0, 1.0).collect(
                    () -> new ArrayList<>(numInputs),
                    (c, e) -> c.add(e),
                    (left, right) -> left.addAll(right)
            );
            this.neurons.add(new Neuron(0.0, values));
        }
    }
    
    public DenseLayer(String string) {
        String[] nStrings = string.split("\\|");
        this.function = ActivationFunctionFactory.create(nStrings[0]);
        this.neurons = new ArrayList<>();
        for (int i = 1; i < nStrings.length; i++) {
            this.neurons.add(new Neuron(nStrings[i]));
        }
    }
    
    public List<List<Double>> forward(List<List<Double>> X) {
        List<List<Double>> Y = new ArrayList<>(X.size());
        for (List<Double> inputs : X) {
            
            List<Double> outputs = new ArrayList<>(this.neurons.size());
            for (Neuron n : this.neurons) {
                outputs.add(n.output(inputs));
            }
            
            Y.add(function.activate(outputs));
        }
        return Y;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.function.getType().toString());
        for (Neuron n : this.neurons) {
            sb.append(n.toString());
            sb.append('|');
        }
        return sb.toString();
    }
}
