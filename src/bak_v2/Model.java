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
    
    private int iteration = 0;
    private double errTrain = 0.0;
    
    private XYSeries dataSeries;
    private XYSeriesCollection seriesCollection;
    
    private XYSeries testSeries;
    
    private XYSeries dataSeriesSuccess;
    private XYSeriesCollection seriesCollectionSuccess;
    
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
    
    private int testDiv = 20, trainDiv = 80;
    private int packages;
    
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
        net.divideDataSet(this.testDiv, this.trainDiv);
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
    
    public double calculateSuccess() {
        int records = numOfRecords();
        int[] results = getClassOutput();
        int outClass = results.length;
        int[] desireResult = getClassDesireOutput();
            int dimension = outClass + 1;
            double [][]resultTop = new double[dimension][dimension];
            double [][]resultBot = new double[dimension][dimension];
            int wrong, good;
            for(int r = 0; r < outClass; r++) {
                wrong = Math.abs(desireResult[r] - results[r]);
                good = desireResult[r] - wrong;
                if(good > 0) {
                    //good
                    resultTop[r][r] = good;//Math.abs(desireResult[r] - Math.abs(desireResult[r] - results[r]));
                    //goodP
                    resultBot[r][r] = (resultTop[r][r] / (records * 1.0) ) * 100.0;                        
                }
                //sumGood
                resultTop[outClass][outClass] += resultBot[r][r];
                for(int c = 0; c < outClass; c++) { 
                    if(c != r) {
                            //bad
                            wrong = Math.abs(desireResult[r] - results[r]);
                            if(wrong > 0) {
                                for(int i = 0; i < outClass; i++) {
                                    wrong = (desireResult[r] - results[r]);
                                    if(wrong > 0 && c != i) {
                                        resultTop[r][c] = wrong;
                                        resultBot[r][c] = (resultTop[r][c] / (records * 1.0) )  * 100.0;
                                        break;
                                    }
                                }
                            }
                        //badP
                        resultBot[outClass][outClass] += resultBot[r][c];
                        if(wrong > 0) {
                            break;
                        }
                    }
                }
            }
            return resultTop[dimension-1][dimension-1];
    }
    
}
