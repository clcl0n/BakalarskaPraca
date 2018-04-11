package viewPanels;

import bak_v2.Model;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import viewComponents.Separator;
import viewComponents.buttons.BasicButton;
import viewComponents.buttons.FileGetButton;
import viewComponents.labels.GroupInputLabel;
import viewComponents.labels.PanelLabel;

public class SingleInputPanel extends JPanel {
    
    //Model & Layout
    private final Model neuModel;
    private final GroupLayout singleInputLayout = new GroupLayout(this);    

    //Panel name
    private final PanelLabel label = new PanelLabel("Test ????");
    
    //Input Group Labels
    private final GroupInputLabel err = new GroupInputLabel("Chyba určenia:");
    private final GroupInputLabel clas = new GroupInputLabel("Trieda:");    
    private final GroupInputLabel file = new GroupInputLabel("Vyber súbor");        
    
    //Separators
    private final Separator sep = new Separator();    
    
    //Data Labels
    private final JLabel mse = new JLabel("0.0");
    private final JLabel type = new JLabel("0");
 
    //Buttons
    private final FileGetButton getFile = new FileGetButton();
    private final BasicButton calculate = new BasicButton("Výpočet");
    
    /**
     * Initialize layout
     */
    private void initLayout(){
        this.setBackground(Color.WHITE);
        singleInputLayout.setHorizontalGroup(
            singleInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(singleInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(singleInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label)
                    .addComponent(sep, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(file)
                    .addComponent(getFile)
                    .addComponent(err)
                    .addComponent(mse)
                    .addComponent(clas)
                    .addComponent(type)
                    .addComponent(calculate))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        singleInputLayout.setVerticalGroup(
            singleInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(singleInputLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sep, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(file)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(getFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(err)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(clas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(type)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(calculate)
                .addContainerGap(143, Short.MAX_VALUE))
        );        
    }
    
    /**
     * Set text to MSE Label & Type Label
     * 
     * @param mse text to set
     * @param type text to set
     */
    public void addData(double mse, int type) {
        this.mse.setText(String.format("%.2f",mse));
        this.type.setText(String.valueOf(type));
        this.update(this.getGraphics());
    }
    
    /**
     * ActionListener to calculate button
     * 
     * @param al ActionListener
     */
    public void addCalculateResultListener(ActionListener al) {
        this.calculate.addActionListener(al);
    }
    
    /**
     * ActionListener to SingleDataPathListener
     * 
     * @param al ActionListener 
     */
    public void addGetSingleDataPathListener(ActionListener al) {
        this.getFile.addActionListener(al);
    }
 
    public SingleInputPanel(Model neuModel) {
        this.neuModel = neuModel;
        this.setLayout(singleInputLayout);
        initLayout();
    }
}
