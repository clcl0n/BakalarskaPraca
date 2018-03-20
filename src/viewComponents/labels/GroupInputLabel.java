package viewComponents.labels;

import javax.swing.JLabel;

public class GroupInputLabel extends JLabel {
    
    public GroupInputLabel(String labelName) {
        super(labelName);
        
        this.setFont(new java.awt.Font("SansSerif", 0, 24)); 
        //this.setForeground(new java.awt.Color(204, 204, 204));  
    }    
}
