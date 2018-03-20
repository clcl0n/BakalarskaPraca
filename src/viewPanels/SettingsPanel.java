package viewPanels;

import bak_v2.Model;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import viewComponents.buttons.BasicButton;
import viewComponents.labels.GroupInputLabel;
import viewComponents.Input;
import viewComponents.labels.InputLabel;
import viewComponents.Separator;
import viewComponents.buttons.FileGetButton;
import viewComponents.labels.PanelLabel;

public class SettingsPanel extends JPanel {
    
    private Model neuModel;
    
    private final GroupLayout settingsLayout;
    
    //Panel label
    PanelLabel label = new PanelLabel("Nastavenia");
    
    //Input Group label
    GroupInputLabel file = new GroupInputLabel("Vyber súbor");
    GroupInputLabel neuNet = new GroupInputLabel("Neurónová sieť");
    GroupInputLabel train = new GroupInputLabel("Trénovanie");
    
    //Input labes
    InputLabel inNeu = new InputLabel("Vstupné neuróny");
    InputLabel hidNeu = new InputLabel("Skryté neuróny");
    InputLabel outNeu = new InputLabel("Výstupné neuróny");
    
    InputLabel numIt = new InputLabel("Počet iterácii");
    InputLabel rate = new InputLabel("Rate");
    InputLabel momentum = new InputLabel("Momentum");    
    InputLabel maxErr = new InputLabel("max Err");
    
    //Group separatos
    Separator sepFile = new Separator();
    Separator sepNeu = new Separator();    
    Separator sepTrain = new Separator();
    
    //Input fields
    private Input inInNeu = new Input();
    private Input inHidNeu = new Input();
    private Input inOutNeu = new Input();
    private Input inNumIt = new Input();
    private Input inRate = new Input();
    private Input inMomentum = new Input();
    private Input inMaxErr = new Input();
    
    //Buttons
    BasicButton next = new BasicButton("Dalej");
    FileGetButton getFile = new FileGetButton();
    
    public void addFilePathListener(ActionListener acl) {
        getFile.addActionListener(acl);
    }
    
    public void addToExtendSettingsActionListener(ActionListener acl) {
        next.addActionListener(acl);
    }
    
    private void initLayout() {
        settingsLayout.setHorizontalGroup(
            settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(settingsLayout.createSequentialGroup()
                        .addComponent(numIt)
                        .addGap(88, 88, 88)
                        .addComponent(rate)
                        //.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(99, 99, 99)
                        .addComponent(momentum)
                        .addGap(75, 75, 75)
                        .addComponent(maxErr)
                        .addGap(114, 114, 114))
                    .addGroup(settingsLayout.createSequentialGroup()
                        .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(settingsLayout.createSequentialGroup()
                                .addComponent(inNeu)
                                .addGap(62, 62, 62)
                                .addComponent(hidNeu)
                                .addGap(54, 54, 54)
                                .addComponent(outNeu))
                            .addComponent(label)
                            .addComponent(getFile)
                            .addComponent(sepFile, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(file)
                            .addComponent(neuNet)
                            .addGroup(settingsLayout.createSequentialGroup()
                                .addComponent(inInNeu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(98, 98, 98)
                                .addComponent(inHidNeu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73)
                                .addComponent(inOutNeu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(train)
                            .addComponent(sepTrain, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(settingsLayout.createSequentialGroup()
                                .addComponent(inNumIt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(95, 95, 95)
                                .addComponent(inRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71)
                                .addComponent(inMomentum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76)
                                .addComponent(inMaxErr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(43, Short.MAX_VALUE))))
            .addGroup(settingsLayout.createSequentialGroup()
                .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(settingsLayout.createSequentialGroup()
                        .addGap(274, 274, 274)
                        .addComponent(next))
                    .addGroup(settingsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(sepNeu, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        settingsLayout.setVerticalGroup(
            settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label)
                .addGap(18, 18, 18)
                .addComponent(sepFile, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(file)
                .addGap(26, 26, 26)
                .addComponent(getFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sepNeu, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(neuNet)
                .addGap(18, 18, 18)
                .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inNeu)
                    .addComponent(hidNeu)
                    .addComponent(outNeu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inInNeu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inHidNeu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inOutNeu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sepTrain, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(train)
                .addGap(18, 18, 18)
                .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numIt)
                    .addComponent(rate)
                    .addComponent(momentum)
                    .addComponent(maxErr))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inNumIt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inMomentum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inMaxErr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(next)
                .addContainerGap())
        );
    }
    
    public SettingsPanel(Model neuModel) {
        this.neuModel = neuModel;
        this.setBackground(new Color(255, 255, 255));
        this.setSize(new Dimension(800, 400));
        this.settingsLayout = new GroupLayout(this);
        this.initLayout();
        this.setLayout(settingsLayout);
    }
    
    public void setInNeu(int num) {
        inInNeu.setText(String.valueOf(num));
    }
    
    public void setOutNeu(int num) {
        inOutNeu.setText(String.valueOf(num));
    }
    
    public void setHidNeu() {
        inHidNeu.setText(String.valueOf(neuModel.getHidNeu()));
    }
    
    public void setRate() {
        inRate.setText(String.valueOf(neuModel.getRate()));
    }
    
    public void setMaxIt() {
        inNumIt.setText(String.valueOf(neuModel.getMaxIt()));
    }
    
    public void setMomentum() {
        inMomentum.setText(String.valueOf(neuModel.getMomentum()));
    }
    
    public void setMaxErr() {
        inMaxErr.setText(String.valueOf(neuModel.getMaxErr()));
    }
    
    public void disableGetFile() {
        getFile.setEnabled(false);
    }
    
    public void disableInNeu() {
        inInNeu.setEditable(false);
    }
    
    public void disableOutNeu() {
        inOutNeu.setEditable(false);
    }
    
    public String getInNeu() {
        return this.inInNeu.getText();
    }
    
    public String getHidNeu() {
        return this.inHidNeu.getText();
    }    
   
    public String getOutNeu() {
        return this.inOutNeu.getText();
    }

    public String getMaxIt() {
        return this.inNumIt.getText();
    }

    public String getMomentum() {
        return this.inMomentum.getText();
    }

    public String getRate() {
        return this.inRate.getText();
    }

    public String getMaxErr() {
        return this.inMaxErr.getText();
    }    
    
}
