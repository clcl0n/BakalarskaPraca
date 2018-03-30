package viewPanels;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import viewComponents.buttons.NavButton;

public class NavBar extends JPanel {
    
    private final GroupLayout navLayout;
    
    private final JLabel appLabel = new JLabel("Baklárska práca");

    private final NavButton data = new NavButton("Dáta");    
    private final NavButton settings = new NavButton("Nastavenia"); 
    private final NavButton training = new NavButton("Trénovanie");
    private final NavButton summary = new NavButton("Výsledky");

    public void unHighlightSummary() {
        summary.unHighlight();
    }
    
    public void highlightSummary() {
        summary.highlight();
    }
    
    public void unHighlightData() {
        data.unHighlight();
    }
    
    public void unHighlightSettings() {
        settings.unHighlight();
    }
    
    public void unHighlightTraining() {
        training.unHighlight();  
    }
    
    public void highlightData() {
        data.highlight();
    }
    
    public void highlightSettings() {
        settings.highlight();
    }
    
    public void highlightTraining() {
        training.highlight();
    }
    
    private void initLayout() {
        navLayout.setHorizontalGroup(
            navLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(appLabel)
                .addContainerGap(23, Short.MAX_VALUE))
            .addComponent(settings, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(data, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(training, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(summary, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        navLayout.setVerticalGroup(
            navLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(appLabel)
                .addGap(69, 69, 69)
                .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(settings, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(training, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(summary, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );    
    }
    
    public NavBar() {
        this.setBackground(new Color(75, 81, 98));
        
        navLayout = new GroupLayout(this);
        initLayout();
        this.setLayout(navLayout);
        
        //Set Label
        appLabel.setFont(new java.awt.Font("SansSerif", 0, 24)); 
        appLabel.setForeground(new java.awt.Color(255, 255, 255));
        this.add(appLabel);
        this.setSize(new Dimension(800, 400));
    }
    
}
