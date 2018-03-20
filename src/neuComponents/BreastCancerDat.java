package neuComponents;

import java.util.ArrayList;

public class BreastCancerDat extends CustomData{

    public BreastCancerDat(String filePath) {
        super(filePath, ",");
    }

    @Override
    void consResultData(String[] elements) {
        int size = 2;
        double add[] = new double[size];
        int type = Integer.parseInt(elements[1]);
        for(int i = 0; i < size; i++) {
            if(type == 1) {
                add[0] = 1.0;
            }
            else {
                add[1] = 1.0;
            }
        }
        desiredOutput.add(add);
    }

    @Override
    void consInputData(String[] elements) {
        int size = elements.length;
        double[] arrDouble = new double[size - 2];
        ArrayList<String[]> tmp = new ArrayList<String[]>();
        int j = 0;
        for(int i = 2; i < size; i++, j++) {
                arrDouble[j] = Double.parseDouble(elements[i]); 
        }
        input.add(arrDouble);
    }
    
}
