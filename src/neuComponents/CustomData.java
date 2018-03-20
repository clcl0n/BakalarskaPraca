package neuComponents;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;

abstract public class CustomData {
    
    private String[] dataContent;
    
    protected ArrayList<double[]> desiredOutput = new ArrayList<double[]>();
    protected ArrayList<double[]> input = new ArrayList<double[]>();

    private DataSet trainingSet = null;  
    
    public CustomData(String filePath, String ch) {
        this.dataContent = readFile(filePath);
        createData(ch);
        createTrainSet();
    }

    private String[] readFile(String filePath) {
        FileReader fr = null;
        ArrayList<String> textData = new ArrayList<String>();
        try {
            fr = new FileReader(filePath);
            BufferedReader textReader = new BufferedReader(fr);
            String line = null;
            while((line = textReader.readLine()) != null) {
                textData.add(line);
            }
        } 
        catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } 
        finally {
            try {
                fr.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        
        int size = textData.size();
        if(size == 0) {
            return null;
        }
        else {
            String result[] = new String[size];
            for(int i = 0; i < size; i++) {
                result[i] = textData.get(i);
            }
            return result;
        }
    }    
    
    private void createData(String ch){
        int rows = this.dataContent.length;
        int chars;
        char character;
        String stringRow = null;
        String newString = null;
        String elements[] = null;
        for(int i = 0; i < rows; i++) {
            stringRow = this.dataContent[i];
            elements = stringRow.split(ch);
            consResultData(elements);
            consInputData(elements);
        }
    }    
    
    abstract void consResultData(String elements[]);
    
    abstract void consInputData(String elements[]);
    
    private void createTrainSet() {
        int size = input.size();
        trainingSet = new DataSet(input.get(0).length, desiredOutput.get(0).length);
        for(int i = 0; i < size; i++) {
            this.trainingSet.addRow(new DataSetRow(input.get(i), desiredOutput.get(i)));
        }
    }    
    
    public int getOutputSize() {
        return desiredOutput.get(0).length;
    }
    
    public int getInputSize(){
        return input.get(0).length;
    }    
    
    public DataSet getTrainingSet() {
        return this.trainingSet;
    }
    
}
