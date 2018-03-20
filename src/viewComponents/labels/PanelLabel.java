package viewComponents.labels;

import javax.swing.JLabel;

public class PanelLabel extends JLabel {
    
    public PanelLabel(String panelName) {
        super(panelName);
        
        this.setFont(new java.awt.Font("SansSerif", 0, 30)); 
        //this.setForeground(new java.awt.Color(204, 204, 204));  
    }
    
}
