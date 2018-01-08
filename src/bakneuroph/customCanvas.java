/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bakneuroph;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JScrollPane;
import org.neuroph.core.Connection;
import org.neuroph.core.Neuron;

/**
 *
 * @author patrik.f.balaz
 */
public class customCanvas extends Canvas {
    
    private int inputN, hiddenN, outN;
    private int width, height;
    
    private List<Neuron> inNeurons;
    private List<Neuron> outNeurons;

    public customCanvas(int inputN, int hiddenN, int outN, List<Neuron> inNeurons, List<Neuron> outNeurons) {
        this.inputN = inputN;
        this.hiddenN = hiddenN;
        this.outN = outN;
        this.inNeurons = inNeurons;
        this.outNeurons = outNeurons;
    }
    
    @Override
    public void paint(Graphics g){
        int radius = 30;
        int margin = 5;
        int distance = margin;
        this.width = this.getWidth();
        this.height = this.getHeight();
//        if((this.inputN * (radius + margin)) > this.height) {
//            System.out.println(this.inputN * (radius + margin));
//            System.out.println(this.height);
//        }
        int diff, distIn, distHid, distOut, diff1, diff2;
        distOut = margin;
        distIn = margin;
        distHid = margin;
        if(this.outN > this.inputN && this.outN > this.hiddenN) {
            //max out
            diff1 = (this.outN - this.inputN) / 2;
            diff2 = (this.outN - this.hiddenN) / 2;
            distOut = margin;
            for(int i = 0; i < diff1;i++){
                distIn += (radius + margin); 
            }
            for(int i = 0; i < diff2;i++) {
                distHid += (radius + margin);
            }    
        }
        else if(this.hiddenN > this.inputN && this.hiddenN > this.outN) {
            //max hidden
            diff1 = (this.hiddenN - this.inputN) / 2;
            diff2 = (this.hiddenN - this.outN) / 2;
            distHid = margin;
            for(int i = 0; i < diff1;i++){
                distIn += (radius + margin); 
            }
            for(int i = 0; i < diff2;i++) {
                distOut += (radius + margin);
            }    
        }
        else if(this.inputN > this.hiddenN && this.inputN > this.outN) {
            //max in
            diff1 = (this.inputN - this.hiddenN) / 2;
            diff2 = (this.inputN - this.outN) / 2;
            for(int i = 0; i < diff1;i++){
                distHid += (radius + margin); 
            }
            for(int i = 0; i < diff2;i++) {
                distOut += (radius + margin);
            }    
        }
        
        for(int i = 0; i < this.inputN; i++) {
            g.drawOval(30, distIn, radius, radius);
            distIn += (radius + margin); 
        }
        for(int i = 0; i < this.hiddenN; i++) {
            g.drawOval(300, distHid, radius, radius);
            distHid += (radius + margin);
        }
        for(int i = 0; i < this.outN; i++) {
            g.drawOval(500, distOut, radius, radius);
            distOut += (radius + margin);
        }
        
        distIn = distIn - ((radius + margin) * this.inputN);
        for(int i = 0; i < this.inputN; i++) {
            Neuron neu = inNeurons.get(i);
            List<Connection> con = neu.getOutConnections();
            distHid = distHid - ((radius + margin) * this.hiddenN);
            for(int j = 0; j < this.hiddenN; j++) {
                Connection c = con.get(j);
                if(c.getWeight().value >= 0.5) {
                    g.setColor(Color.yellow);
                }
                else if(c.getWeight().value < 0.5) {
                    g.setColor(Color.red);
                }
                g.drawLine(30 + (radius), (distIn + (radius/2)), 300, distHid + (radius/2));
                distHid += (radius + margin);
            }
            distIn += (radius + margin);
        }
        
        distHid = distHid - ((radius + margin) * this.hiddenN);
        for(int i = 0; i < this.hiddenN; i++) {
            distOut = distOut - ((radius + margin) * this.outN);
            for(int j = 0; j < this.outN; j++) {
                Neuron neu = this.outNeurons.get(j);
                List<Connection> con = neu.getInputConnections();
                Connection find = con.get(i);
                if(find.getWeight().value >= 0.5) {
                    g.setColor(Color.green);
                }
                else if(find.getWeight().value < 0.5) {
                    g.setColor(Color.blue);
                }
                g.drawLine(300 + (radius), (distHid + (radius/2)), 500, distOut + (radius/2));
                distOut += (radius + margin);
            }
            distHid += (radius + margin);
        }
        
        
        //reset to default
        g.setColor(Color.BLACK);
    }
    
}
