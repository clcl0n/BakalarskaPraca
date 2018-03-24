package Workers;

import bak_v2.AppView;
import bak_v2.Model;
import javax.swing.SwingWorker;

public class TrainWorker extends SwingWorker<Integer, String> {

    private AppView w_appView;
    private Model w_neuModel;
    
    private void startLearning() {
        int maxIt = w_neuModel.getMaxIt();
        double[] mse = new double[2];
        double maxErr;
        maxErr = w_neuModel.getMaxErr(); 
        for(int i = 0; i < maxIt; i++) {
            mse = w_neuModel.netTrain();
            this.w_neuModel.addValues(i, mse[0]);
            this.w_neuModel.addTestValues(i, mse[1]);
            this.w_neuModel.addSuccessValues(i, this.w_neuModel.calculateSuccessTrain());
            if(mse[0] < maxErr) {
                break;
            }
            if((i % 100) == 0) {
               //this.w_neuModel.mix();            
            }
        }
        w_neuModel.setEnd(true);
        w_neuModel.setLastSuccess(this.w_neuModel.calculateSuccessTrain());
        w_neuModel.setLastTrainMSE(mse[0]);
        w_neuModel.setLastTestMSE(mse[1]);
    }
    
    public TrainWorker(AppView appView, Model neuModel) {
        this.w_appView = appView;
        this.w_neuModel = neuModel;
    }
    
    @Override
    protected Integer doInBackground() throws Exception {
        w_appView.showTrainPanel();
        
        startLearning();

        return null;
    }
 
}
