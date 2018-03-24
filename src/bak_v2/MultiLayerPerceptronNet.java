package bak_v2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
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
    public MultiLayerPerceptronNet(int inputLayer, int hiddenLayer,int outputLayer, TransferFunctionType function){
        super(function, inputLayer, hiddenLayer, outputLayer);
    }
    
    /**
     * Setting attributes for Momentum backpropagation learning.
     * 
     * @param rate Learning rate
     * @param momentum Momentum
     * @param maxError Maximum Error
     */
    public void setAtributesMomentumBackpropagation(double rate, double momentum, double maxError){
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
    
    public void mixTrainAndTestData() {
        Random rand = new Random();
        int n1, n2;
        int trainSize = this.trainingAndTestSet[0].size();
        int testSize = this.trainingAndTestSet[1].size();
        int size;
        DataSetRow tmp;
        for(int i = 0; i < 150; i++) {
            n1 = rand.nextInt(trainSize);
            n2 = rand.nextInt(testSize);
            tmp = this.trainingAndTestSet[0].get(n1);
            this.trainingAndTestSet[0].set(n1, this.trainingAndTestSet[1].get(n2));
            this.trainingAndTestSet[1].set(n2, tmp);
        }
    }
    
    /**
     * Calculate Mean Squared Error.
     * 
     * @return MSE
     */
    public double[] getMSE(){
        double[] result = new double[2];
        MeanSquaredError mse = new MeanSquaredError();
        for(DataSetRow dataRow : trainingAndTestSet[0].getRows()) {
            this.setInput(dataRow.getInput());
            this.calculate();
            double[ ] networkOutput = this.getOutput();
            mse.addPatternError(networkOutput, dataRow.getDesiredOutput());
        }
        result[0] = mse.getTotalError();
        
        MeanSquaredError mse2 = new MeanSquaredError();
        for(DataSetRow dataRow : trainingAndTestSet[1].getRows()) {
            this.setInput(dataRow.getInput());
            this.calculate();
            double[ ] networkOutput = this.getOutput();
            mse2.addPatternError(networkOutput, dataRow.getDesiredOutput());
        }    
        result[1] = mse2.getTotalError();
        return result;
    }
    
//    public double getCE() {
//        double result = 0;
//        int size = this.trainingAndTestSet[1].size();
//        List<DataSetRow> dataRow = this.trainingAndTestSet[1].getRows();
//        DataSetRow tmp;
//        for(int i = 0; i < size;i++) {
//            tmp = dataRow.get(i);
//            this.setInput(tmp.getInput());
//            this.calculate();
//            double[ ] networkOutput = this.getOutput();
//            result += (tmp.getDesiredOutput()*Math.log10())
//        }
//        
//        return result;
//    }
    
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
    
    public int[] getClassOutputTest() {
        Integer index;
        int size = trainingAndTestSet[1].getRows().get(0).getDesiredOutput().length;
        int result[] = new int[size];
        for(DataSetRow dataRow : trainingAndTestSet[1].getRows()) {
            this.setInput(dataRow.getInput());
            this.calculate();
            double[ ] networkOutput = this.getOutput();
            ArrayList<Double> output = new ArrayList<Double>();
            for(double i:networkOutput){
                output.add(i);
            }
            index = output.indexOf(Collections.max(output));
            result[index]++;
        }
        return result;
    }
    
    public int[] getClassOutputTrain() {
        Integer index;
        int size = trainingAndTestSet[0].getRows().get(0).getDesiredOutput().length;
        int result[] = new int[size];
        for(DataSetRow dataRow : trainingAndTestSet[0].getRows()) {
            this.setInput(dataRow.getInput());
            this.calculate();
            double[ ] networkOutput = this.getOutput();
            ArrayList<Double> output = new ArrayList<Double>();
            for(double i:networkOutput){
                output.add(i);
            }
            index = output.indexOf(Collections.max(output));
            result[index]++;
        }
        return result;
    }
    
    public int[] getClassDesireOutputTest() {
        Integer index;
        int size = trainingAndTestSet[1].getRows().get(0).getDesiredOutput().length;
        int result[] = new int[size];
        for(DataSetRow dataRow : trainingAndTestSet[1].getRows()) {
            double[] networkOutput = dataRow.getDesiredOutput();
            for(int i = 0; size > i; i++) {
                if(networkOutput[i] == 1.0) {
                    result[i]++;
                }
            }
        }
        return result;
    }
    
    public int[] getClassDesireOutputTrain() {
        Integer index;
        int size = trainingAndTestSet[0].getRows().get(0).getDesiredOutput().length;
        int result[] = new int[size];
        for(DataSetRow dataRow : trainingAndTestSet[0].getRows()) {
            double[] networkOutput = dataRow.getDesiredOutput();
            for(int i = 0; size > i; i++) {
                if(networkOutput[i] == 1.0) {
                    result[i]++;
                }
            }
        }
        return result;
    }
    
}
