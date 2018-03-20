package neuComponents;

import java.util.ArrayList;

public class DermatologyDat extends CustomData{

    public DermatologyDat(String filePath) {
        super(filePath, ",");
    }

    @Override
    void consResultData(String[] elements) {
        int size = 6;
        double add[] = new double[size];
        int type = Integer.parseInt(elements[(elements.length - 1)]);
        for(int i = 0; i < size; i++) {
            if((type - 1) == i ) {
                add[i] = 1.0;
            }
            else {
                add[i] = 0.0;
            }
        }
        desiredOutput.add(add);
    }

    @Override
    void consInputData(String[] elements) {
        int size = elements.length - 1;
        double[] arrDouble = new double[size];
        ArrayList<String[]> tmp = new ArrayList<String[]>();
        int j = 0;
        for(int i = 0; i < size; i++, j++) {
                arrDouble[j] = Double.parseDouble(elements[i]); 
        }
        input.add(arrDouble);
    }
    
}
