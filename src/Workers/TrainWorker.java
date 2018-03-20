package Workers;

import bak_v2.AppView;
import bak_v2.Model;
import javax.swing.SwingWorker;

public class TrainWorker extends SwingWorker<Integer, String> {

    private AppView w_appView;
    private Model w_neuModel;
    
    private void startLearning() {
        int maxIt = w_neuModel.getMaxIt();
        double[] mse;
        double maxErr;
        maxErr = w_neuModel.getMaxErr();
        for(int i = 0; i < maxIt; i++) {
            mse = w_neuModel.netTrain();
            this.w_neuModel.addValues(i, mse[0]);
            this.w_neuModel.addTestValues(i, mse[1]);
            if(mse[0] < maxErr) {
                break;
            }
            if((i % 100) == 0) {
                this.w_neuModel.mix();            
            }
        }
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
