package bak_v2;

import java.util.List;
import neuComponents.ArrhythmiaDat;
import neuComponents.BreastCancerDat;
import neuComponents.DermatologyDat;
import neuComponents.ParkinsonDat;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.neuroph.core.Neuron;
import org.neuroph.core.data.DataSet;
import org.neuroph.util.TransferFunctionType;
import org.neuroph.util.data.norm.MaxMinNormalizer;

public class Model {
    
    private XYSeries dataSeries;
    private XYSeriesCollection seriesCollection;
    
    private XYSeries testSeries;
    
    private int inNeu, hidNeu = 5, outNeu;
    private int maxIt = 10000;
    private double rate = 0.1, momentum = 0.8, maxErr = 0.02;
    
    private TransferFunctionType function;
    
    private boolean isParkinson, isArrhythmia, isBreastCancer, isDermatology, isCustom;
    
    private String fileDataPath;
    
    //Default training data
    private ParkinsonDat parkinsonData; 
    private ArrhythmiaDat arrhytmiaData;
    private DermatologyDat dermatologyData;
    private BreastCancerDat breastCancerData;
    
    private DataSet trainingSet;
    private MultiLayerPerceptronNet net;
    
    public int[] getClassDesireOutput() {
        return net.getClassDesireOutput();
    }    
    
    public int[] getClassOutput() {
        return net.getClassOutput();
    }
    
    public int numOfRecords() {
        return this.net.trainingAndTestSet[0].size();
    }
    
    public void setFnToGaussian() {
        function = TransferFunctionType.GAUSSIAN;
    }
    
    public void setFnToTanh() {
        function = TransferFunctionType.TANH;
    }
    
    public void setFnToSigmoid() {
        function = TransferFunctionType.SIGMOID;
    }
    
    public double[] netTrain() {
        net.trainNet(1);
        return net.getMSE();
    }
    
    public boolean createBreastCancerNeuNet() {
        if(createBreastCancerData()) {
            trainingSet = breastCancerData.getTrainingSet();
            setupNeuNet();
        }
        else {
            return false;
        }
        return true;
    }
    
    private boolean createBreastCancerData() {
        try {
            breastCancerData = new BreastCancerDat(fileDataPath);
        }
        catch(Exception ex) {
            System.out.println(ex);
            return false;
        }
        return true;        
    }        
    
    public boolean createDermatologyNeuNet() {
        if(createDermatologyData()) {
            trainingSet = dermatologyData.getTrainingSet();
            setupNeuNet();
        }
        else {
            return false;
        }
        return true;
    }
    
    private boolean createDermatologyData() {
        try {
            dermatologyData = new DermatologyDat(fileDataPath);
        }
        catch(Exception ex) {
            System.out.println(ex);
            return false;
        }
        return true;        
    }    
    
    public boolean createArrhythmiaNeuNet() {
        if(createArrhythmiaData()) {
            trainingSet = arrhytmiaData.getTrainingSet();
            setupNeuNet();
        }
        else {
            return false;
        }
        return true;
    }
    
    private boolean createArrhythmiaData() {
        try {
            arrhytmiaData = new ArrhythmiaDat(fileDataPath);
        }
        catch(Exception ex) {
            System.out.println(ex);
            return false;
        }
        return true;        
    }
    
    private void setupNeuNet() {
        net = new MultiLayerPerceptronNet(inNeu, hidNeu, outNeu, function);
        net.setAtributesMomentumBackpropagation(rate, momentum, maxErr);
        normalizeData();
        net.setTrainingSet(trainingSet);
        net.divideDataSet(90, 10);
    }
    
    public boolean createParkinsonNeuNet() {
        if(createParkinsonData()) {
            trainingSet = parkinsonData.getTrainingSet();
            setupNeuNet();
        }
        else {
            return false;
        }
        return true;
    }
    
    private void normalizeData() {
        MaxMinNormalizer norm = new MaxMinNormalizer();
        norm.normalize(trainingSet);        
    }
    
    private boolean createParkinsonData() {
        try {
            parkinsonData = new ParkinsonDat(fileDataPath);
        }
        catch(Exception ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }
    
    public void addValues(int x, double y){
        dataSeries.add(x, y);
    }
    
    public void addTestValues(int x, double y) {
        this.testSeries.add(x, y);
    }
    
    public XYSeriesCollection getGraphData() {
        return this.seriesCollection;
    }
    
    public Model() {
        this.dataSeries = new XYSeries("Trénovanie");
        this.seriesCollection = new XYSeriesCollection();
        this.seriesCollection.addSeries(this.dataSeries);
        this.testSeries = new XYSeries("Testovanie");
        this.seriesCollection.addSeries(this.testSeries);
    }
    
    public void setFileDataPath(String str) {
        this.fileDataPath = str;
    }
    
    public void setInNeu(int num) {
        this.inNeu = num;
    }
    
    public void setHidNeu(int num) {
        this.hidNeu = num;
    }    
    
    public void setOutNeu(int num) {
        this.outNeu = num;
    }    
    
    public void setMaxIt(int num) {
        this.maxIt = num;
    }    
    
    public void setMomentum(double num) {
        this.momentum = num;
    }    
    
    public void setRate(double num) {
        this.rate = num;
    }    
    
    public void setMaxErr(double num) {
        this.maxErr = num;
    }    
    
    public void setIsParkinson(boolean bool) {
        this.isParkinson = bool;
    }

    public void setIsArrhythmia(boolean bool) {
        this.isArrhythmia = bool;
    }

    public void setIsBreastCancer(boolean bool) {
        this.isBreastCancer = bool;
    }

    public void setIsDermatology(boolean bool) {
        this.isDermatology = bool;
    }

    public void setIsCustom(boolean bool) {
        this.isCustom = bool;
    }    
    
    public int getMaxIt() {
        return this.maxIt;
    }
    
    public boolean getIsParkinson() {
        return this.isParkinson;
    }

    public boolean getIsArrhythmia() {
        return this.isArrhythmia;
    }

    public boolean getIsBreastCancer() {
        return this.isBreastCancer;
    }

    public boolean getIsDermatology() {
        return this.isDermatology;
    }

    public boolean setIsCustom() {
        return this.isCustom;
    }   
    
    public double getMaxErr() {
        return this.maxErr;
    }
    
    public int getInNeu() {
        return this.inNeu;
    }
    
    public int getHidNeu() {
        return this.hidNeu;
    }
    
    public int getOutNeu() {
        return this.outNeu;
    }
    
    public List<Neuron> getInLayer() {
        return net.getInputNeurons();
    }
    
    public List<Neuron> getOutLayer() {
        return net.getOutputNeurons();
    }
    
    public double getRate() {
        return this.rate;
    }
    
    public double getMomentum() {
        return this.momentum;
    }
    
    public void setFnSigmoid() {
        this.function = TransferFunctionType.SIGMOID;
    }
    
    public void setFnTanh() {
        this.function = TransferFunctionType.TANH;
    }    
    
    public void setFnGaussian() {
        this.function = TransferFunctionType.GAUSSIAN;
    }    

    public void mix() {
        this.net.mixTrainAndTestData();
    }
    
}
