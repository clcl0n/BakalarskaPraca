package viewPanels;

import bak_v2.Model;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import viewComponents.buttons.BasicButton;

public class EndTrainingPanel extends JPanel {
    
    private Model neuModel;
    
    private JLabel lastSuccess = new JLabel();
    private JLabel lastTrainMSE = new JLabel();
    private JLabel lastTestMSE = new JLabel();
    
    private BasicButton saveNet = new BasicButton("uloženie sieťe");
    
    public void setLastSuccess() {
        this.lastSuccess.setText(String.valueOf(neuModel.getLastSuccess()));
    }
    
    public void setLastTrainMSE() {
        this.lastTrainMSE.setText(String.valueOf(neuModel.getLastTrainMSE()));
    }
    
    public void setLastTestMSE() {
        this.lastTestMSE.setText(String.valueOf(neuModel.getLastTestMSE()));
    }
    
    public void addSaveNetListener(ActionListener al) {
        this.saveNet.addActionListener(al);
    }
    
    public EndTrainingPanel(Model neuModel) {
        this.neuModel = neuModel;
        this.add(lastSuccess);
        this.add(lastTrainMSE);
        this.add(lastTestMSE);
        this.add(saveNet);
    }
    
}
