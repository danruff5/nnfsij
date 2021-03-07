package ca.danruff5.nnfsij.main;

import ca.danruff5.nnfsij.main.activations.ActivationFunction;
import ca.danruff5.nnfsij.main.activations.ActivationFunctionFactory;
import ca.danruff5.nnfsij.main.layers.DenseLayer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Application {
    
    
    public static void main(String[] args) {
        /*List<Double> inputs1 = Arrays.asList(
                1.0, 
                2.0, 
                3.0, 
                2.5
        );
        List<Double> inputs2 = Arrays.asList(
                2.0, 
                5.0, 
                -1.0, 
                2.0
        );
        List<Double> inputs3 = Arrays.asList(
                -1.5, 
                2.7, 
                3.3, 
                -0.8
        );
        List<List<Double>> X = Arrays.asList(inputs1, inputs2, inputs3);*/
        
        /*Layer layer1 = new Layer(
                new Neuron(2.0, 0.2, 0.8, -0.5, 1.0),
                new Neuron(3.0, 0.5, -0.91, 0.26, -0.5),
                new Neuron(0.5, -0.26, -0.27, 0.17, 0.87)
        );
        List<List<BigDecimal>> layer1_outputs = layer1.outputBatch(inputs1, inputs2, inputs3);
        Layer layer2 = new Layer(
                new Neuron(-1.0, 0.1, -0.14, 0.5),
                new Neuron(2.0, -0.5, 0.12, -0.33),
                new Neuron(-0.5, -0.44, 0.73, -0.13)
        );
        System.out.println(layer2.outputBatch(layer1_outputs));*/
        
        /*DenseLayer layer1a = new DenseLayer("RELU|2.0,0.2,0.8,-0.5,1.0|3.0,0.5,-0.91,0.26,-0.5|0.5,-0.26,-0.27,0.17,0.87|");
        DenseLayer layer2a = new DenseLayer("RELU|-1.0,0.1,-0.14,0.5|2.0,-0.5,0.12,-0.33|-0.5,-0.44,0.73,-0.13|");
        
        //DenseLayer layer1 = new DenseLayer(4, 3);
        List<List<Double>> layer1_output = layer1a.forward(X);
        //DenseLayer layer2 = new DenseLayer(3, 3);
        List<List<Double>> layer2_output = layer2a.forward(layer1_output);
        
        System.out.println(layer1_output.get(0));

        ActivationFunction function = ActivationFunctionFactory.create("SOFTMAX");
        List<Double> softMaxOut = function.activate(layer1_output.get(0));
        System.out.println(softMaxOut);
        System.out.println(softMaxOut.stream().mapToDouble(Double::doubleValue).sum());*/
        
        Dataset dataset = new Dataset();

        DenseLayer dense1 = new DenseLayer(ActivationFunctionFactory.create("RELU"), 2, 3);
        DenseLayer dense2 = new DenseLayer(ActivationFunctionFactory.create("SOFTMAX"), 3, 3);
        
        List<List<Double>> X = dataset.create_data2(10, 3);
        List<Integer> y = dataset.getY();
        List<List<Double>> dense1_output = dense1.forward(X);
        List<List<Double>> dense2_output = dense2.forward(dense1_output);
        Random r = new Random(0);
        
        
        for (int i = 0; i < 5; i++) {
            int a = r.nextInt(dense1_output.size());
            
            System.out.println(a + " " + dense2_output.get(a) + " " + y.get(a) + " " + -Math.log(dense2_output.get(a).get(y.get(a))));
        }
    }
    
    private static class Dataset {
        Random random = new Random(0);
        private double[][] X;
        private int[] Y;

        public void create_data(int points, int classes) {
            X = new double[points * classes][2];
            Y = new int[points * classes];
            int ix = 0;
            for (int class_number = 0; class_number < classes; class_number++) {
                double r = 0;
                double t = class_number * 4;
                while (r <= 1 && t <= (class_number + 1) * 4) {
                    double random_t = t + random.nextInt(points) * 0.2;
                    X[ix][0] = r * Math.sin(random_t * 2.5);
                    X[ix][1] = r * Math.cos(random_t * 2.5);
                    Y[ix] = class_number;
                    r += 1.0 / (points - 1);
                    t += 4.0 / (points - 1);
                    ix++;
                }
            }
        }
        
        public List<List<Double>> create_data2(int points, int classes) {
            create_data(points, classes);
            List<List<Double>> x = new ArrayList<>();
            for (double[] a : X) {
                
                List<Double> values = new ArrayList<>();
                for (double v : a) {
                    values.add(v);
                }
                x.add(values);
            }
            
            return x;
        }
        
        public List<Integer> getY() {
            List<Integer> y = new ArrayList<>();
            for (int v : Y) {
                y.add(v);
            }
            
            return y;
        }
    }
}
