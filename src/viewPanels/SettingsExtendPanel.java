package viewPanels;

import bak_v2.Model;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import viewComponents.Input;
import viewComponents.RadioBtn;
import viewComponents.Separator;
import viewComponents.buttons.BasicButton;
import viewComponents.labels.GroupInputLabel;
import viewComponents.labels.PanelLabel;

public class SettingsExtendPanel extends JPanel {
    
    private final Model neuModel;
    private final GroupLayout extendSettingsLayout;
        
    private final PanelLabel label = new PanelLabel("Nastavenia");
    private final GroupInputLabel activation = new GroupInputLabel("Aktivacna funkcia");
    
    //Input Group label
    private final GroupInputLabel actFn = new GroupInputLabel("Aktivačná funkcia");
    private final GroupInputLabel dataDiv = new GroupInputLabel("Rozdelenie dát");

    //Group separatos
    private final Separator sepAct = new Separator();
    
    //Radio buttons
    private final RadioBtn sigmoid = new RadioBtn("Sigmoida");
    private final RadioBtn tanh = new RadioBtn("Tanh");
    private final RadioBtn gaussian = new RadioBtn("Gaussian");
    private final RadioBtn random = new RadioBtn("Náhodné rozdelenie");
    private final RadioBtn packages = new RadioBtn("Balíky");
    
    //Radio button group
    private final ButtonGroup activationFn = new ButtonGroup();    
    private final ButtonGroup dataSplit = new ButtonGroup();
    
    //Input fields
    private final Input train = new Input();
    private final Input test = new Input();
    private final Input iPackages = new Input();
    
    //Buttons
    private final BasicButton trainBtn = new BasicButton("Trénovanie");
    
    public void addRandomDivDataSetListener(ActionListener al) {
        random.addActionListener(al);
    }
    
    public void addPackagesDivDataSetListener(ActionListener al) {
        packages.addActionListener(al);
    }
    
    private void disableRandom() {
        train.setEnabled(false);
        test.setEnabled(false);
    }
    
    public void enablePackages() {
        iPackages.setEnabled(true);
        disableRandom();
    }
    
    private void disablePackages() {
        iPackages.setEnabled(false);
    }
    
    public void enableRandom() {
        train.setEnabled(true);
        test.setEnabled(true);
        disablePackages();
    }
    
    public String getPackages() {
        return this.packages.getText();
    }
    
    private void setDefValue() {
        enableRandom();
        sigmoid.setSelected(true);
        random.setSelected(true);
        neuModel.setFnSigmoid();
    }
    
    public boolean isSelectedPackages() {
        return packages.isSelected();
    }
    
    public boolean isSelectedRandom() {
        return random.isSelected();
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
    
    public void setTrainDiv() {
        this.train.setText(String.valueOf(neuModel.getTrainDiv()));
    } 
    
    public void setTestDIv() {
        this.test.setText(String.valueOf(neuModel.getTestDiv()));
    }
    
    public String getTestDiv() {
        return this.test.getText();
    }
    
    public String getTrainDiv() {
        return this.train.getText();
    }
    
    private void initLayout() {
        this.add(sigmoid);
        this.add(tanh);
        this.add(gaussian);
        this.add(trainBtn);
        this.add(train);
        this.add(test);
        this.add(random);
        this.add(packages);
        this.add(iPackages);
        
        activationFn.add(sigmoid);
        activationFn.add(tanh);
        activationFn.add(gaussian);
        
        dataSplit.add(random);
        dataSplit.add(packages);
    }
    
    public void addTrainingActionListener(ActionListener acl) {
        trainBtn.addActionListener(acl);
    }
    
    public SettingsExtendPanel(Model neuModel) {
        this.neuModel = neuModel;
        this.extendSettingsLayout = new GroupLayout(this);;
        initLayout();
        setDefValue();
    }
    
}
