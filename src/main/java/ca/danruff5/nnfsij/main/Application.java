package ca.danruff5.nnfsij.main;

import ca.danruff5.nnfsij.main.activations.ActivationFunctionFactory;
import ca.danruff5.nnfsij.main.layers.DenseLayer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Application {
    
    
    public static void main(String[] args) {
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
