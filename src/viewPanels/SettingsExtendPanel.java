package viewPanels;

import bak_v2.Model;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import viewComponents.RadioBtn;
import viewComponents.Separator;
import viewComponents.buttons.BasicButton;
import viewComponents.labels.GroupInputLabel;
import viewComponents.labels.InputLabel;

public class SettingsExtendPanel extends JPanel {
    
    private Model neuModel;
    
    //Input Group label
    GroupInputLabel actFn = new GroupInputLabel("Aktivačná funkcia");
    
    //Group separatos
    Separator sepAct = new Separator();
    
    //Radio buttons
    RadioBtn sigmoid = new RadioBtn("Sigmoida");
    RadioBtn tanh = new RadioBtn("Tanh");
    RadioBtn gaussian = new RadioBtn("Gaussian");
    
    //Radio button group
    ButtonGroup buttonGroup = new ButtonGroup();    
    
    //Buttons
    BasicButton trainBtn = new BasicButton("Trénovanie");
    
    private void setDefValue() {
        sigmoid.setSelected(true);
        neuModel.setFnSigmoid();
    }
    
    public boolean isSelectedSigmoid() {
        return sigmoid.isSelected();
    }
    
    public boolean isSelectedTanh() {
        return tanh.isSelected();
    }    
    
    public boolean isSelectedGaussian() {
        return gaussian.isSelected();
    }    
    
    private void initLayout() {
        this.add(sigmoid);
        this.add(tanh);
        this.add(gaussian);
        this.add(trainBtn);
        
        
        buttonGroup.add(sigmoid);
        buttonGroup.add(tanh);
        buttonGroup.add(gaussian);
    }
    
    public void addTrainingActionListener(ActionListener acl) {
        trainBtn.addActionListener(acl);
    }
    
    public SettingsExtendPanel(Model neuModel) {
        this.neuModel = neuModel;
        initLayout();
        setDefValue();
    }
    
}
