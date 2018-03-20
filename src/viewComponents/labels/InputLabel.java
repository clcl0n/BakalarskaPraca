package viewComponents.labels;

import javax.swing.JLabel;

public class InputLabel extends JLabel {
    
    public InputLabel(String labelName) {
        super(labelName);
        
        this.setFont(new java.awt.Font("SansSerif", 0, 12)); 
        //this.setForeground(new java.awt.Color(204, 204, 204));        
    }
    
}
