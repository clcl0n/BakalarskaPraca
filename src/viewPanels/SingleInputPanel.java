package viewPanels;

import bak_v2.Model;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import viewComponents.buttons.BasicButton;
import viewComponents.buttons.FileGetButton;

public class SingleInputPanel extends JPanel {
    
    private JLabel mse = new JLabel("0.0");
    private JLabel type = new JLabel("0");
    
    private Model neuModel;
    private FileGetButton getFile = new FileGetButton();
    private BasicButton calculate = new BasicButton("Výpočet");
    
    public SingleInputPanel(Model neuModel) {
        this.neuModel = neuModel;
        this.add(getFile);
        this.add(calculate);
        this.add(mse);
        this.add(type);
    }
    
    public void addData(double mse, int type) {
        this.mse.setText(String.valueOf(mse));
        this.type.setText(String.valueOf(type));
        this.update(this.getGraphics());
    }
    
    public void addCalculateResultListener(ActionListener al) {
        this.calculate.addActionListener(al);
    }
    
    public void addGetSingleDataPathListener(ActionListener al) {
        this.getFile.addActionListener(al);
    }
    
}
