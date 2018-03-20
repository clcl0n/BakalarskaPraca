package viewComponents.buttons;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class BasicButton extends JButton {
    
    public BasicButton(String buttonName) {
        super(buttonName);
        this.setBackground(new Color(75, 81, 98));
        this.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N                
        this.setForeground(new java.awt.Color(255, 255, 255));
        this.setFocusPainted(false);
    }
    
}
