package viewPanels;

import bak_v2.Model;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.neuroph.core.Neuron;
import viewComponents.buttons.BasicButton;

public class TrainingPanel extends JPanel {
    
    private Model model;
    private NeuronModel neuModel;
    private ConfusionMatrix confusionMatrix;
    
    private BasicButton modelBtn = new BasicButton("Model");
    private BasicButton confusionBtn = new BasicButton("Confusion Matrix");
    
    private XYSeriesCollection seriesCollection;
    private XYSeriesCollection seriesCollectionSucces;

    private JLabel iteration = new JLabel();
    private JLabel errTrain = new JLabel();
    
    public void errTrainLabel(String text) {
        this.errTrain.setText(text);
    }
    
    public void iterationLabel(String text) {
        this.iteration.setText(text);
    }
    
    public void ConfusionMatrix(int records, int results[], int[] desireResult) {
        confusionMatrix = new ConfusionMatrix(records, results, desireResult);
        confusionMatrix.setVisible(true);
    }
    
    public void showModel(int inputN, int hiddenN, int outN, List<Neuron> inNeurons, List<Neuron> outNeurons) {
        JFrame frame = new JFrame("FrameDemo");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        neuModel = new NeuronModel(inputN, hiddenN, outN, inNeurons, outNeurons);
        neuModel.setPreferredSize(new Dimension(400, 500));
        JScrollPane scroll = new JScrollPane(neuModel);
        frame.setLayout(new BorderLayout());
        frame.add(scroll, BorderLayout.CENTER);
        frame.setSize(800, 800);
        frame.setVisible(true);
    }
    
    public void addConfusionMatrixListener(ActionListener acl) {
        confusionBtn.addActionListener(acl);
    }
    
    public void addNeuModelActionListener(ActionListener acl) {
        modelBtn.addActionListener(acl);
    }
    
    private JFreeChart createChartSucces(String graphTitle, String titleX, String titleY){
        JFreeChart result = ChartFactory.createXYLineChart(
            graphTitle,
            titleX,
            titleY,
            this.seriesCollectionSucces,
            PlotOrientation.VERTICAL,
            true, true, false);
        return result;
    }
    
    private JFreeChart createChart(String graphTitle, String titleX, String titleY){
        JFreeChart result = ChartFactory.createXYLineChart(
            graphTitle,
            titleX,
            titleY,
            this.seriesCollection,
            PlotOrientation.VERTICAL,
            true, true, false);
        return result;
    }
    
    public TrainingPanel(Model model, String graphTitle, String titleX, String titleY, XYSeriesCollection seriesCollection, XYSeriesCollection seriesCollectionSucces) {
        this.model = model;
        this.seriesCollection = seriesCollection;
        this.seriesCollectionSucces = seriesCollectionSucces;
        this.setBackground(Color.WHITE);        
        JFreeChart chart = createChart(graphTitle, titleX, titleY);
        JFreeChart succesChart = createChartSucces("Úspešnosť", titleX, "%");
        ChartPanel chartPanel = new ChartPanel( chart );
        ChartPanel chartPanelSucces = new ChartPanel( succesChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        this.add(chartPanel);
        this.add(chartPanelSucces);
        this.add(modelBtn);
        this.add(confusionBtn);
        this.add(iteration);
        this.add(errTrain);
    }
}
