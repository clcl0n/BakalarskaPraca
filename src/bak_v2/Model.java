package bak_v2;

import java.util.List;
import neuComponents.ArrhythmiaDat;
import neuComponents.BreastCancerDat;
import neuComponents.CustomData;
import neuComponents.CustomTrainData;
import neuComponents.DermatologyDat;
import neuComponents.ParkinsonDat;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.neuroph.core.Neuron;
import org.neuroph.core.data.DataSet;
import org.neuroph.util.TransferFunctionType;
import org.neuroph.util.data.norm.MaxMinNormalizer;

public class Model {
    
    private boolean end = false;
    
    private int iteration = 0;
    private double errTrain = 0.0;
    
    private XYSeries dataSeries;
    private XYSeriesCollection seriesCollection;
    
    private XYSeries testSeries;
    
    private XYSeries dataSeriesSuccess;
    private XYSeriesCollection seriesCollectionSuccess;
    
    private int inNeu, hidNeu = 5, outNeu;
    private int maxIt = 100;
    private double rate = 0.1, momentum = 0.8, maxErr = 0.02;
    
    private TransferFunctionType function;
    
    private boolean isParkinson, isArrhythmia, isBreastCancer, isDermatology, isCustom;
    
    private String fileDataPath;
    
    private double lastSuccess, lastTestMSE, lastTrainMSE; 
    
    //Default training data
    private ParkinsonDat parkinsonData; 
    private ArrhythmiaDat arrhytmiaData;
    private DermatologyDat dermatologyData;
    private BreastCancerDat breastCancerData;
    private CustomTrainData customData;
    
    private DataSet trainingSet;
    private MultiLayerPerceptronNet net;
    
    private int testDiv = 20, trainDiv = 80;
    private int packages = 0;

    public void clear() {
        dataSeries.clear();
        dataSeriesSuccess.clear();
        testSeries.clear();
        packages = 0;
        testDiv = 20;
        trainDiv = 80;
        trainingSet = null;
        net = null;
        parkinsonData = null;
        arrhytmiaData = null;
        dermatologyData = null;
        breastCancerData = null;
        customData = null;
        lastSuccess = 0.0; 
        lastTestMSE = 0.0; 
        lastTrainMSE = 0.0;
        fileDataPath = null;
        isParkinson = false; 
        isArrhythmia = false; 
        isBreastCancer = false; 
        isDermatology = false; 
        isCustom = false;
        rate = 0.1; 
        momentum = 0.8; 
        maxErr = 0.02;
        inNeu = 0;
        hidNeu = 5;
        outNeu = 0;
        maxIt = 100;
        errTrain = 0.0;
        iteration = 0;
        end = false;
    }
    
    public void saveNet() {
        net.save("neuralNet.nnet");
    }
    
    public double getLastSuccess() {
        return lastSuccess;
    }

    public void setLastSuccess(double lastSuccess) {
        this.lastSuccess = lastSuccess;
    }

    public double getLastTestMSE() {
        return lastTestMSE;
    }

    public void setLastTestMSE(double lastTestMSE) {
        this.lastTestMSE = lastTestMSE;
    }

    public double getLastTrainMSE() {
        return lastTrainMSE;
    }

    public void setLastTrainMSE(double lastTrainMSE) {
        this.lastTrainMSE = lastTrainMSE;
    }
    
    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
    
    public int getTestDiv() {
        return testDiv;
    }

    public void setTestDiv(int testDiv) {
        this.testDiv = testDiv;
    }

    public int getTrainDiv() {
        return trainDiv;
    }

    public void setTrainDiv(int trainDiv) {
        this.trainDiv = trainDiv;
    }
    
    public int[] getClassDesireOutputTest() {
        return net.getClassDesireOutputTest();
    } 
    
    public int[] getClassDesireOutputTrain() {
        return net.getClassDesireOutputTrain();
    }    
    
    public int[] getClassOutputTest() {
        return net.getClassOutputTest();
    }
    
    public int[] getClassOutputTrain() {
        return net.getClassOutputTrain();
    }
    
    public int numOfRecordsTest() {
        return this.net.trainingAndTestSet[1].size();
    }
    
    public int numOfRecordsTrain() {
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
    
    public boolean createCustomNeuNet() {
        if(createCustomData()) {
            trainingSet = customData.getTrainingSet();
            setupNeuNet();
        }
        else {
            return false;
        }
        return true;
    }
    
    private boolean createCustomData() {
        try {
            customData = new CustomTrainData(fileDataPath, this.inNeu, this.outNeu);
        }
        catch(Exception ex) {
            System.out.println(ex);
            return false;
        }
        return true;        
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
        net.divideDataSet(this.trainDiv, this.testDiv);
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

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public double getErrTrain() {
        return errTrain;
    }

    public void setErrTrain(double errTrain) {
        this.errTrain = errTrain;
    }
    
    public void addTestValues(int x, double y) {
        this.testSeries.add(x, y);
    }
    
    public XYSeriesCollection getGraphData() {
        return this.seriesCollection;
    }
    
    public void addSuccessValues(int x, double y) {
        this.dataSeriesSuccess.add(x, y);
    }
    
    public XYSeriesCollection getSuccessData() {
        return this.seriesCollectionSuccess;
    }
    
    public Model() {
        this.dataSeries = new XYSeries("Trénovanie");
        this.seriesCollection = new XYSeriesCollection();
        this.seriesCollection.addSeries(this.dataSeries);
        this.testSeries = new XYSeries("Testovanie");
        this.seriesCollection.addSeries(this.testSeries);
        this.dataSeriesSuccess = new XYSeries("Úspešnosť");
        this.seriesCollectionSuccess = new XYSeriesCollection();
        this.seriesCollectionSuccess.addSeries(this.dataSeriesSuccess);
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
    
    public boolean getIsCustom() {
        return this.isCustom;
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
    
    public double calculateSuccessTest() {
        int records = numOfRecordsTest();
        int[] results = getClassOutputTest();
        int outClass = results.length;
        int[] desireResult = getClassDesireOutputTest();
        int wrong = 0, good = 0, sum = 0;
        double result = 0.0;
        for(int r = 0; r < outClass; r++) {
            wrong += Math.abs(desireResult[r] - results[r]);
            good += desireResult[r] - wrong;
        }
        result += ((good * 1.0) / records ) * 100.0;
        return result;
    }
    
    public double calculateSuccessTrain() {
        int records = numOfRecordsTrain();
        int[] results = getClassOutputTrain();
        int outClass = results.length;
        int[] desireResult = getClassDesireOutputTrain();
        int wrong = 0, good = 0, sum = 0;
        double result = 0.0;
        for(int r = 0; r < outClass; r++) {
            wrong += Math.abs(desireResult[r] - results[r]);
            good += desireResult[r] - wrong;
        }
        result += ((good * 1.0) / records ) * 100.0;
        return result;
    }
    
}
