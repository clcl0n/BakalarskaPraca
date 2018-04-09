package viewPanels;

import bak_v2.Model;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import viewComponents.RadioBtn;
import viewComponents.Separator;
import viewComponents.buttons.BasicButton;
import viewComponents.labels.GroupInputLabel;
import viewComponents.labels.PanelLabel;

public class FilePanel extends JPanel {
   
    private Model neuModel;
    
    private final GroupLayout fileLayout;
    
    //Panel Label
    PanelLabel label = new PanelLabel("Dáta");
    
    //Input Group label
    GroupInputLabel file = new GroupInputLabel("Vybranie suboru dát");
    GroupInputLabel fromFile = new GroupInputLabel("Importovanie natrénovanej siete");
    
    //Group separatos
    Separator sepFile = new Separator();    
    //Separator sepFromFile = new Separator();
    
    //Radio buttons
    RadioBtn parkinson = new RadioBtn("Parkinson");
    RadioBtn arrhythmia = new RadioBtn("Arrhythmia");
    RadioBtn breastCancer = new RadioBtn("Breast Cancer");
    RadioBtn dermatology = new RadioBtn("Dermatology");
    RadioBtn custom = new RadioBtn("Vlastne");

    //Radio button group
    ButtonGroup buttonGroup = new ButtonGroup();
    
    //Buttons
    BasicButton next = new BasicButton("Dalej");
    BasicButton netFromFile = new BasicButton("Načítanie");
    
    public FilePanel(Model neuModel) {
        this.neuModel = neuModel;
        this.setBackground(Color.WHITE);
        this.setSize(new Dimension(800, 400));
        this.fileLayout = new GroupLayout(this);
        this.initLayout();
        setDefValue();
        this.setLayout(fileLayout);
    }
    
    private void setDefValue() {
        parkinson.setSelected(true);
        neuModel.setIsParkinson(true);
    }
    
    private void initLayout() {
        fileLayout.setHorizontalGroup(
            fileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fileLayout.createSequentialGroup()
                .addGroup(fileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fileLayout.createSequentialGroup()
                        .addGap(274, 274, 274)
                        .addComponent(next))
                    .addGroup(fileLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(fileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label)
                            .addComponent(file)
                            .addGroup(fileLayout.createSequentialGroup()
                                .addComponent(parkinson)
                                .addGap(34, 34, 34)
                                .addComponent(arrhythmia)
                                .addGap(27, 27, 27)
                                .addComponent(breastCancer)
                                .addGap(30, 30, 30)
                                .addComponent(dermatology))
                            //.addComponent(sepFromFile, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fromFile)
                            .addComponent(netFromFile)
                            .addComponent(sepFile)
                            .addComponent(custom))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        fileLayout.setVerticalGroup(
            fileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fileLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(label)
                .addGap(18, 18, 18)
                .addComponent(sepFile, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(file)
                .addGap(35, 35, 35)
                .addGroup(fileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arrhythmia)
                    .addComponent(parkinson)
                    .addComponent(breastCancer)
                    .addComponent(dermatology)
                
                )
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(custom)
                .addGap(15, 15, 15)
                //.addComponent(sepFromFile)
                .addComponent(fromFile)
                .addGap(15, 15, 15)
                .addComponent(netFromFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(next)
                .addContainerGap())
        );        
        
        buttonGroup.add(parkinson);
        buttonGroup.add(arrhythmia);
        buttonGroup.add(breastCancer);
        buttonGroup.add(dermatology);
        buttonGroup.add(custom);
    
    }
    
    public void addFromFileListener(ActionListener al) {
        netFromFile.addActionListener(al);
    }
    
    public void addChooseFileTypeListener(ActionListener acl) {
        next.addActionListener(acl);
    }
    
    public boolean isSelectedParkinson() {
        return parkinson.isSelected();
    }
    
    public boolean isSelectedArrhythmia() {
        return arrhythmia.isSelected();
    }   
    
    public boolean isSelectedBreastCancer() {
        return breastCancer.isSelected();
    }

    public boolean isSelectedDermatology() {
        return dermatology.isSelected();
    }

    public boolean isSelectedCustom() {
        return custom.isSelected();
    }    
    
}
