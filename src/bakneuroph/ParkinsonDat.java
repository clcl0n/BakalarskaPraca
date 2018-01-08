package bakneuroph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;

public class ParkinsonDat {
    
    private String[] dataContent;
    
    private ArrayList<double[]> desiredOutput = new ArrayList<double[]>();
    private ArrayList<double[]> input = new ArrayList<double[]>();

    public DataSet trainingSet = null;

    public int getOutputSize() {
        return desiredOutput.get(0).length;
    }
    
    public int getInputSize(){
        return input.get(0).length;
    }
    
    private void createTrainSet() {
        int size = input.size();
        trainingSet = new DataSet(input.get(0).length, desiredOutput.get(0).length);
        for(int i = 0; i < size; i++) {
            this.trainingSet.addRow(new DataSetRow(input.get(i), desiredOutput.get(i)));
        }
    }
    
    private void createData(){
        int rows = this.dataContent.length;
        int chars;
        char character;
        String stringRow = null;
        String newString = null;
        String elements[] = null;
        for(int i = 0; i < rows; i++) {
            stringRow = this.dataContent[i];
            elements = stringRow.split(",");
            consResultData(elements);
            consInputData(elements);
        }
    }
    
    private void consInputData(String elements[]) {
        int size = elements.length;
        double[] arrDouble = new double[size - 1];
        ArrayList<String[]> tmp = new ArrayList<String[]>();
        int j = 0;
        for(int i = 0; i < size; i++, j++) {
            if(i == 16){
                j--;
            }
            if(i != 16) {
                arrDouble[j] = Double.parseDouble(elements[i]); 
            }
        }
        input.add(arrDouble);
    }
    
    private void consResultData(String elements[]) {
        double add[] = new double[2];
        if(elements[16].equals("1")) {
            add[0] = 1.0;
            add[1] = 0.0;
        }
        else {
            add[0] = 0.0;
            add[1] = 1.0;
        }
        desiredOutput.add(add);
    }
    
    private String getStringRepresentation(ArrayList<Character> list) {    
        StringBuilder builder = new StringBuilder(list.size());
        for(Character ch: list) {
            builder.append(ch);
        }
        return builder.toString();
    }
    
    public ParkinsonDat(String filePath){
        this.dataContent = readFile(filePath);
        createData();
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
            Logger.getLogger(ParkinsonDat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ParkinsonDat.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(ParkinsonDat.class.getName()).log(Level.SEVERE, null, ex);
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
}
