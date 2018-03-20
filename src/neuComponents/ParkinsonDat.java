package neuComponents;

import java.util.ArrayList;

public class ParkinsonDat extends CustomData {

    public ParkinsonDat(String filePath) {
        super(filePath, ",");
    }

    @Override
    void consResultData(String[] elements) {
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

    @Override
    void consInputData(String[] elements) {
        int size = elements.length - 6;
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
    
}
