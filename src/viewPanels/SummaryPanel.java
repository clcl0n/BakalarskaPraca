package viewPanels;

import bak_v2.Model;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import viewComponents.Separator;
import viewComponents.buttons.BasicButton;
import viewComponents.labels.GroupInputLabel;
import viewComponents.labels.PanelLabel;

public class SummaryPanel extends JPanel {
    
    //Model & layout
    private final Model neuModel;
    private final GroupLayout summaryLayout = new GroupLayout(this);

    //Panel name
    private final PanelLabel label = new PanelLabel("Výsledky");    

    //Input groups labels
    private final GroupInputLabel success = new GroupInputLabel("Úspešnosť:");
    private final GroupInputLabel trainMse = new GroupInputLabel("Chyba trénovacích dát:");
    private final GroupInputLabel testMse = new GroupInputLabel("Chyba testovacích dát:");
    
    //Data labels
    private JLabel lastSuccess = new JLabel();
    private JLabel lastTrainMSE = new JLabel();
    private JLabel lastTestMSE = new JLabel();
    
    //Separator
    private final Separator sep = new Separator();    
    
    //All buttons
    private BasicButton saveNet = new BasicButton("uloženie sieťe");
    private BasicButton modelBtn = new BasicButton("Model");
    private BasicButton confusionBtn = new BasicButton("Confusion Matrix");
    private BasicButton next = new BasicButton("Dalej");        
    
    /**
     * Add ActionListener to next button
     * 
     * @param al ActionListener
     */
    public void addNextToSingleInputListener(ActionListener al) {
        next.addActionListener(al);
    }
    
    /**
     * Add ActionListener to Confusion Matrix button
     * 
     * @param al ActionListener
     */
    public void addConfusionMatrixListener(ActionListener al) {
        confusionBtn.addActionListener(al);
    }
    
    /**
     * Add ActionListener to Neuron model button
     * 
     * @param al ActionListener
     */    
    public void addNeuModelActionListener(ActionListener al) {
        modelBtn.addActionListener(al);
    }    
    
    /**
     * Set text to Success label from Model
     */
    public void setLastSuccess() {
        this.lastSuccess.setText(String.format("%.2f",neuModel.getLastSuccess()) + " %");
    }
    
    /**
     * Set text to TrainMSE label form Model
     */
    public void setLastTrainMSE() {
        this.lastTrainMSE.setText(String.format("%.2f",neuModel.getLastTrainMSE()));
    }
    
    /**
     * Set text to TestMSE label from Model
     */
    public void setLastTestMSE() {
        this.lastTestMSE.setText(String.format("%.2f",neuModel.getLastTestMSE()));
    }
    
    public void addSaveNetListener(ActionListener al) {
        this.saveNet.addActionListener(al);
    }
    
    private void initLayout() {
        this.setBackground(Color.WHITE);
        this.setLayout(summaryLayout);
        summaryLayout.setHorizontalGroup(
            summaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(summaryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(summaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(summaryLayout.createSequentialGroup()
                        .addComponent(saveNet)
                        .addGap(18, 18, 18)
                        .addComponent(modelBtn)
                        .addGap(18, 18, 18)
                        .addComponent(confusionBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(next))
                    .addComponent(label)
                    .addComponent(sep, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(success)
                    .addComponent(lastSuccess)
                    .addComponent(trainMse)
                    .addComponent(lastTrainMSE)
                    .addComponent(testMse)
                    .addComponent(lastTestMSE))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        summaryLayout.setVerticalGroup(
            summaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(summaryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sep, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(success)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lastSuccess)
                .addGap(18, 18, 18)
                .addComponent(trainMse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lastTrainMSE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(testMse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lastTestMSE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                .addGroup(summaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveNet)
                    .addComponent(modelBtn)
                    .addComponent(confusionBtn)
                    .addComponent(next))
                .addContainerGap())
        );
    }
    
    public SummaryPanel(Model neuModel) {
        this.neuModel = neuModel;
        initLayout();
    }
    
}
