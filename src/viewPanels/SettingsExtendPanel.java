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
import viewComponents.labels.PanelLabel;

public class SettingsExtendPanel extends JPanel {
    
    //Model & Layout
    private final Model neuModel;
    private final GroupLayout extendSettingsLayout = new GroupLayout(this);
        
    //Panel name
    private final PanelLabel label = new PanelLabel("Nastavenia");
    
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
    
    /**
     * Initialize Layout
     */
    private void initLayout() {
        this.setBackground(Color.WHITE);
        this.setLayout(extendSettingsLayout);
        extendSettingsLayout.setHorizontalGroup(
            extendSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(extendSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(extendSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label)
                    .addComponent(sepAct, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(actFn)
                    .addComponent(dataDiv)
                    .addGroup(extendSettingsLayout.createSequentialGroup()
                        .addComponent(train, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(extendSettingsLayout.createSequentialGroup()
                        .addComponent(sigmoid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tanh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(extendSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(trainBtn)
                            .addComponent(gaussian))))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        extendSettingsLayout.setVerticalGroup(
            extendSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(extendSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sepAct, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(actFn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(extendSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sigmoid)
                    .addComponent(tanh)
                    .addComponent(gaussian))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dataDiv)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(extendSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(train, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                .addComponent(trainBtn)
                .addContainerGap())
        );        
        
        //RadioButton groups
        activationFn.add(sigmoid);
        activationFn.add(tanh);
        activationFn.add(gaussian);
        
        dataSplit.add(random);
        dataSplit.add(packages);
    }    
    
    /**
     * Add ActionListener to RandomDivData radioButton
     * 
     * @param al ActionListener
     */
    public void addRandomDivDataSetListener(ActionListener al) {
        random.addActionListener(al);
    }
    
    /**
     * Add ActionListener to PackagesDivData radioButton
     * 
     * @param al ActionListener
     */    
    public void addPackagesDivDataSetListener(ActionListener al) {
        packages.addActionListener(al);
    }
    
    /**
     * Add ActionListener to Training button
     * 
     * @param al ActionListener 
     */
    public void addTrainingActionListener(ActionListener al) {
        trainBtn.addActionListener(al);
    }    
    
    //Enable & Disable
    
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
    
    //Is functions
    
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
    
    //Geters
    
    public String getPackages() {
        return this.packages.getText();
    }
    
    public String getTestDiv() {
        return this.test.getText();
    }
    
    public String getTrainDiv() {
        return this.train.getText();
    }    
    
    //Setters
    
    private void setDefValue() {
        enableRandom();
        sigmoid.setSelected(true);
        random.setSelected(true);
        neuModel.setFnSigmoid();
    }
    
    public void setTrainDiv() {
        this.train.setText(String.valueOf(neuModel.getTrainDiv()));
    } 
    
    public void setTestDIv() {
        this.test.setText(String.valueOf(neuModel.getTestDiv()));
    }
    
    public SettingsExtendPanel(Model neuModel) {
        this.neuModel = neuModel;
        initLayout();
        setDefValue();
    }
    
}
