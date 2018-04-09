package viewPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JPanel;
import org.neuroph.core.Connection;
import org.neuroph.core.Neuron;

public class NeuronModel extends JPanel {
    
    private int inputN, hiddenN, outN;
    private int width, height;
    
    private List<Neuron> inNeurons;
    private List<Neuron> outNeurons;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int radius = 30;
        int margin = 10;
        int xDistance = 0;
        int yDistance = 0;
        int inWidth = margin + (inputN * radius) + (inputN * margin);
        int hidWidth = margin + (hiddenN * radius) + (hiddenN * margin);
        int outWidth = margin + (hiddenN * radius) + (outN * margin);
        int maxWidth;
        int fristTop, top;
        fristTop = 20;
        top = 200;
        if(inWidth > hidWidth) {
            if(inWidth > outWidth) {
                maxWidth = inWidth;
            }
            else {
                maxWidth = outWidth;
            }
        }
        else {
            if(hidWidth > outWidth) {
                maxWidth = hidWidth;
            }
            else {
                maxWidth = outWidth;
            }
        }
        
        int imgHeight = 2 * (margin + (3 * radius));        
        
        yDistance = margin + fristTop;
        xDistance = margin;
        for(int i = 0; i < inputN; i++) {
            g2.drawString("x" + i, xDistance + radius/2 - 7, yDistance + radius/2 + 3);
            g2.drawOval(xDistance, yDistance, radius, radius);
            xDistance += (margin + radius);
        }
        
        int yDistance2 = (margin + (3 * radius) + top);
        xDistance = margin;
        for(int i = 0; i < hiddenN; i++) {
            g2.drawOval(xDistance, yDistance2, radius, radius);
            xDistance += (margin + radius);
        } 
        
        int yDistance3 = yDistance2 * 2;
        xDistance = margin;
        for(int i = 0; i < outN; i++) {
            g2.drawString("y" + i, xDistance + radius/2 - 7, yDistance3 + radius/2 + 3);
            g2.drawOval(xDistance, yDistance3, radius, radius);
            xDistance += (margin + radius);
        }        
        
        int x1, x2, y1, y2;
        x1 = margin + (radius / 2);
        y1 = fristTop + radius + margin;
        x2 = x1;
        y2 = yDistance2;
        for(int i = 0; i < this.inputN; i++) {
            Neuron neu = inNeurons.get(i);
            List<Connection> con = neu.getOutConnections();
            for(int j = 0; j < this.hiddenN; j++) {
                Connection c = con.get(j);
                if(c.getWeight().value >= 0.5) {
                    g2.setColor(Color.yellow);
                }
                else if(c.getWeight().value < 0.5) {
                    g2.setColor(Color.red);
                }     
                g2.drawLine(x1, y1, x2, y2);
                x2 += margin + radius;
            }
            x2 = margin + (radius / 2);
            x1 += margin + radius;
        }
        
        x1 = margin + (radius / 2);
        y1 = yDistance2 + radius;
        x2 = x1;
        y2 = yDistance3;
        for(int i = 0; i < this.outN; i++) {
            Neuron neu = outNeurons.get(i);
            List<Connection> con = neu.getInputConnections();
            for(int j = 0; j < this.hiddenN; j++) {
                Connection c = con.get(j);
                if(c.getWeight().value >= 0.5) {
                    g2.setColor(Color.blue);
                }
                else if(c.getWeight().value < 0.5) {
                    g2.setColor(Color.green);
                }         
                g2.drawLine(x1, y1, x2, y2);
                x1 += margin + radius;
            }
            x1 = margin + (radius / 2);
            x2 += margin + radius;            
        }
        
//        
//        yDistance = margin + radius;
//        int yDistance2 = yDistance + (margin + (2 * radius));
//        yDistance += fristTop;
//        xDistance = margin + (radius / 2); 
//        int xDistance2;
//        for(int i = 0; i < this.inputN; i++) {
//            Neuron neu = inNeurons.get(i);
//            List<Connection> con = neu.getOutConnections();
//            xDistance2 = margin + (radius / 2);
//            for(int j = 0; j < this.hiddenN; j++) {
//                Connection c = con.get(j);
//                if(c.getWeight().value >= 0.5) {
//                    g.setColor(Color.yellow);
//                }
//                else if(c.getWeight().value < 0.5) {
//                    g.setColor(Color.red);
//                }
//                g.drawLine(xDistance, yDistance, xDistance2, yDistance2);
//                xDistance2 += (radius + margin);
//            }
//            xDistance += (radius + margin);
//        }        
//        
//        //distHid = distHid - ((radius + margin) * this.hiddenN);
//        yDistance = yDistance2 + top;
//        yDistance2 = yDistance + (margin + (3 * radius)) + top;
//        yDistance += radius;
//        xDistance = margin + (radius / 2);
//        for(int i = 0; i < this.hiddenN; i++) {
//            xDistance2 = margin + (radius / 2);
//            for(int j = 0; j < this.outN; j++) {
//                Neuron neu = this.outNeurons.get(j);
//                List<Connection> con = neu.getInputConnections();
//                Connection find = con.get(i);
//                if(find.getWeight().value >= 0.5) {
//                    g.setColor(Color.green);
//                }
//                else if(find.getWeight().value < 0.5) {
//                    g.setColor(Color.blue);
//                }
//                g.drawLine(xDistance, yDistance, xDistance2, yDistance2);
//                xDistance2 += margin + radius;
//            }
//            xDistance += (radius + margin);
//            g.setColor(Color.BLACK);
//        }        

        this.setPreferredSize(new Dimension(maxWidth, yDistance3 + fristTop));
    }
    
    public NeuronModel(int inputN, int hiddenN, int outN, List<Neuron> inNeurons, List<Neuron> outNeurons) {
        this.setBackground(Color.WHITE);
        this.inputN = inputN;
        this.hiddenN = hiddenN;
        this.outN = outN;
        this.inNeurons = inNeurons;
        this.outNeurons = outNeurons;
    }  
    
}
