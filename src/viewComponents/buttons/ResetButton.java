package viewComponents.buttons;

import java.awt.Color;
import javax.swing.JButton;

public class ResetButton extends JButton {
    public ResetButton(String name) {
        super(name);
        this.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        this.setForeground(new Color(124,129,140));
        this.setBorderPainted(false);
        this.setBackground(new Color(208,102,102));
        this.setForeground(Color.white);
    }
}
