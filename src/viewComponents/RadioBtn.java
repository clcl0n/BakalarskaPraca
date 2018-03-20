package viewComponents;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

public class RadioBtn extends JRadioButton {
    
    public RadioBtn(String nameRadio) {
        super(nameRadio);
        this.setContentAreaFilled(false);
        this.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N        
        this.setFocusPainted(false);

        ImageIcon unselIcon = new ImageIcon("img/radiobutton-unchecked-md.png"); // load the image to a imageIcon
        Image image = unselIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(16, 16,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        unselIcon = new ImageIcon(newimg);  // transform it back
        
        ImageIcon selIcon = new ImageIcon("img/radiobutton-checked-sm-md.png"); // load the image to a imageIcon
        image = selIcon.getImage(); // transform it 
        newimg = image.getScaledInstance(16, 16,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        selIcon = new ImageIcon(newimg);  // transform it back        
        this.setIcon(unselIcon);
        this.setSelectedIcon(selIcon);
    }
    
}
