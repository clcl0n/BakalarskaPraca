package viewPanels;

import bak_v2.Model;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import viewComponents.Input;
import viewComponents.RadioBtn;
import viewComponents.Separator;
import viewComponents.buttons.BasicButton;
import viewComponents.labels.GroupInputLabel;
import viewComponents.labels.InputLabel;
import viewComponents.labels.PanelLabel;

public class SettingsExtendPanel extends JPanel {
    
    private Model neuModel;
    private GroupLayout layout;
    
    private PanelLabel label = new PanelLabel("Nastavenia");
    private GroupInputLabel activation = new GroupInputLabel("Aktivacna funkcia");
    
    //Input Group label
    GroupInputLabel actFn = new GroupInputLabel("Aktivačná funkcia");
    
    //Group separatos
    Separator sepAct = new Separator();
    
    //Radio buttons
    RadioBtn sigmoid = new RadioBtn("Sigmoida");
    RadioBtn tanh = new RadioBtn("Tanh");
    RadioBtn gaussian = new RadioBtn("Gaussian");
    RadioBtn random = new RadioBtn("Náhodné rozdelenie");
    RadioBtn packages = new RadioBtn("Balíky");
    
    //Radio button group
    ButtonGroup activationFn = new ButtonGroup();    
    ButtonGroup dataSplit = new ButtonGroup();
    
    //Input fields
    private Input train = new Input();
    private Input test = new Input();
    private Input iPackages = new Input();
    
    //Buttons
    BasicButton trainBtn = new BasicButton("Trénovanie");
    
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
//        layout = new GroupLayout(this);
//        this.setLayout(layout);
//        this.setBackground(Color.WHITE);
//        layout.setAutoCreateGaps(true);
//        layout.setAutoCreateContainerGaps(true);
//
//        layout.setHorizontalGroup(layout.createSequentialGroup()
//                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addComponent(label)
//                    //.addComponent(sepAct, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(activation)                
//                )
//                .addComponent(sigmoid)
//                .addComponent(this.gaussian)
//                .addComponent(this.tanh)
//.addGap(this.getWidth())
//
//        );
//
//        layout.setVerticalGroup(layout.createSequentialGroup()
//                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                    .addComponent(label))
//                .addGap(20)
//                //.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                //.addComponent(sepAct, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE))
//                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(activation))
//                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(activation)
//                    .addGroup(layout.createSequentialGroup()
//                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                            .addComponent(sigmoid)
//                            .addComponent(this.gaussian)
//                            .addComponent(this.tanh))))
//
//
//                
//                
//                
//        );
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
        initLayout();
        setDefValue();
    }
    
}
