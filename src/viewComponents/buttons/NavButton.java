package viewComponents.buttons;

import java.awt.Color;
import javax.swing.JButton;

public class NavButton extends JButton {
    
    public void highlight() {
        this.setBackground(new Color(82,148,226));
        this.setForeground(new java.awt.Color(255, 255, 255));
        this.setContentAreaFilled(true);
    }
    
    public void unHighlight() {
        this.setContentAreaFilled(false);
        this.setForeground(new Color(124,129,140));
    }
    
    public NavButton(String buttonName) {
        super(buttonName);
        this.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        this.setForeground(new Color(124,129,140));
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false); 
    }
    
}
