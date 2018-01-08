package bakneuroph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.learning.error.MeanSquaredError;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.TransferFunctionType;

/**
 * Multi layer perceptron neural network with additional functions. 
 */
public class MultiLayerPerceptronNet extends MultiLayerPerceptron {
    private static MomentumBackpropagation train = null;
    
    DataSet dataSet = null;
    DataSet[] trainingAndTestSet = null;
    
    /**
     * Constructor for MultiLayerPerceptron network.
     * 
     * @param inputLayer Number of input neurons
     * @param hiddenLayer Number of neurons in hidden layer
     * @param outputLayer Number of neurons in output layer
     */
    public MultiLayerPerceptronNet(int inputLayer, int hiddenLayer,int outputLayer){
        super(TransferFunctionType.SIGMOID, inputLayer, hiddenLayer, outputLayer);
    }
    
    /**
     * Setting attributes for Momentum backpropagation learning.
     * 
     * @param rate Learning rate
     * @param momentum Momentum
     * @param maxError Maximum Error
     */
    public void setAtributesMomentumBackpropagation(int rate, double momentum, double maxError){
        this.train = new MomentumBackpropagation();
        this.train.setLearningRate(rate);
        this.train.setMomentum(momentum);
        this.train.setMaxError(maxError);
        super.setLearningRule(this.train);
    }
    
    /**
     * Set Data for learning.
     * 
     * @param dataSet data set
     */
    public void setTrainingSet(DataSet dataSet){
        this.dataSet = dataSet;
    }
    
    /**
     * Divide data to training and test sets.
     *
     * @param train train data in %
     * @param test test data in %
     */
    public void divideDataSet(int train, int test){
        this.trainingAndTestSet = this.dataSet.createTrainingAndTestSubsets(train, test);
    }
    
    /**
     * Train neural network.
     * 
     * @param maxIterations set maximum iteration 
     */
    public void trainNet(int maxIterations){
        this.train.setMaxIterations(maxIterations);
        this.train.learn(trainingAndTestSet[0]);
    }
    
    /**
     * Calculate Mean Squared Error.
     * 
     * @return MSE
     */
    public double getMSE(){
        MeanSquaredError mse = new MeanSquaredError();
        for(DataSetRow dataRow : trainingAndTestSet[0].getRows()) {
            this.setInput(dataRow.getInput());
            this.calculate();
            double[ ] networkOutput = this.getOutput();
            mse.addPatternError(networkOutput, dataRow.getDesiredOutput());
        }
        return mse.getTotalError();
    }
    
    /**
     * Calculate and print Input, Output from network and in which category it belongs.
     */
    public void getInputClassOutput(){
        Integer index;
        for(DataSetRow dataRow : trainingAndTestSet[0].getRows()) {
            this.setInput(dataRow.getInput());
            this.calculate();
            double[ ] networkOutput = this.getOutput();
            ArrayList<Double> output = new ArrayList<Double>();
            for(double i:networkOutput){
                output.add(i);
            }
            index = output.indexOf(Collections.max(output));
            System.out.print("Input: " + Arrays.toString(dataRow.getInput()) );
            System.out.print(" class: " + (index+1));
            System.out.println(" Output: " + Arrays.toString(networkOutput)  );
        }
    }
    
}
