package bakneuroph;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import org.jfree.ui.RefineryUtilities;
import org.neuroph.core.Connection;
import org.neuroph.core.Neuron;
import org.neuroph.core.data.DataSet;

public class BakNeuroph {
    public static void main(String[] args) {
        //ParkinsonDat ešete nefunkčné
        ParkinsonDat dat = new ParkinsonDat("Parkinsons/parkinsons.data.txt");
       
        int in = dat.getInputSize();
        int out = dat.getOutputSize();
        //int in = 21;
        //int out = 7;
        int hidden = 10;
        
        MultiLayerPerceptronNet net = new MultiLayerPerceptronNet(in, hidden, out);
        Graph g = new Graph("Trénovanie", "Chyba sieťe", "Trénovanie", "Iterácie", "MSE");
        g.pack( );
        RefineryUtilities.centerFrameOnScreen( g );
        g.setVisible( true );
        net.setAtributesMomentumBackpropagation(2, 0.3, 0.3);
        
        //DataSet trainingSet = DataSet.createFromFile("AnimalSpecies2.txt", in, out, ",");
        DataSet trainingSet = dat.trainingSet;
        net.setTrainingSet(trainingSet);
        net.divideDataSet(80, 20);
       for(int i = 0;i < 5;i++){
            net.trainNet(1);
            g.addValues(i, net.getMSE());
       }
       net.getInputClassOutput();
       System.out.println(net.getMSE());
       
       
       List<Neuron> inNeurons = net.getInputNeurons(); 
       List<Neuron> outNeurons = net.getOutputNeurons();
       Neuron neu = inNeurons.get(0);
       List<Connection> con = neu.getOutConnections();
       Connection c = con.get(0);
        System.out.println(c.getWeight());
        
        
        
       JFrame frame = new JFrame("FrameDemo");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       //frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
       
       customCanvas canvas = new customCanvas(in, hidden, out, inNeurons, outNeurons);

        canvas.setSize(800, 800);
        //canvas.setBackground(Color.BLACK);
        canvas.setVisible(true);
        canvas.setFocusable(false);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }
}
