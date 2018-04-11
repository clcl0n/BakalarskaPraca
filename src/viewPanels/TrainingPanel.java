package viewPanels;

import bak_v2.Model;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
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
    
    //Model & Layout
    private final Model model;
    private final GroupLayout trainLayout = new GroupLayout(this);

    //Frames
    private NeuronModel neuModel;
    private ConfusionMatrix confusionMatrix;
    
    //Data labels
    private final JLabel iteration = new JLabel();
    private final JLabel errTrain = new JLabel();
    
    //Buttons
    private final BasicButton next = new BasicButton("Dalej");
    
    //SeriesCollections for graphs
    private final XYSeriesCollection seriesCollection;
    private final XYSeriesCollection seriesCollectionSucces;
    
    /**
     * Set text to Err Train Label
     * 
     * @param text text to set
     */
    public void errTrainLabel(String text) {
        this.errTrain.setText(text);
    }
    
    /**
     * Set text to Iteration Label
     * 
     * @param text text to set
     */
    public void iterationLabel(String text) {
        this.iteration.setText(text);
    }
    
    /**
     * Create Confusion Matrix Frame
     * 
     * @param records number of records
     * @param results array of results
     * @param desireResult array of desire result
     */
    public void ConfusionMatrix(int records, int results[], int[] desireResult) {
        confusionMatrix = new ConfusionMatrix(records, results, desireResult);
        confusionMatrix.setVisible(true);
    }
    
    /**
     * Create Neuron Model Frame
     * 
     * @param inputN number of input Neurons
     * @param hiddenN number of hidden Neurons
     * @param outN number of output Neurons
     * @param inNeurons List of input Neurons
     * @param outNeurons  List of output Neurons
     */
    public void showModel(int inputN, int hiddenN, int outN, List<Neuron> inNeurons, List<Neuron> outNeurons) {
        JFrame frame = new JFrame("FrameDemo");
        neuModel = new NeuronModel(inputN, hiddenN, outN, inNeurons, outNeurons);
        neuModel.setPreferredSize(new Dimension(400, 500));
        JScrollPane scroll = new JScrollPane(neuModel);
        frame.setLayout(new BorderLayout());
        frame.add(scroll, BorderLayout.CENTER);
        frame.setSize(800, 800);
        frame.setVisible(true);
    }
    
    /**
     * Add ActionListener to Next button
     * 
     * @param al ActionListener
     */
    public void addNextEndListener(ActionListener al) {
        next.addActionListener(al);
    }
    
    /**
     * Create Chart to show Success of training neural net.
     * 
     * @param graphTitle Graph name
     * @param titleX X axis name
     * @param titleY Y axis name
     * @return new Chart
     */
    private JFreeChart createChartSuccess(String graphTitle, String titleX, String titleY){
        JFreeChart result = ChartFactory.createXYLineChart(
            graphTitle,
            titleX,
            titleY,
            this.seriesCollectionSucces,
            PlotOrientation.VERTICAL,
            true, true, false);
        return result;
    }
    
    /**
     * Create Chart to show MSE of training neural net.
     * 
     * @param graphTitle Graph name
     * @param titleX X axis name
     * @param titleY Y axis name
     * @return new Chart
     */    
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
    
    /**
     * Initialize layout.
     * 
     * @param graphTitle Graph name
     * @param titleX X axis name
     * @param titleY Y axis name
     */
    private void initLayout(String graphTitle, String titleX, String titleY) {
        JFreeChart chart = createChart(graphTitle, titleX, titleY);
        JFreeChart succesChart = createChartSuccess("Úspešnosť", titleX, "%");
        ChartPanel chartPanel = new ChartPanel( chart );
        ChartPanel chartPanelSucces = new ChartPanel( succesChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 200 ) );
        chartPanelSucces.setPreferredSize( new java.awt.Dimension( 560 , 200 ) );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(chartPanel);
        this.add(chartPanelSucces);
        this.add(next); 
    }
    
    public TrainingPanel(Model model, String graphTitle, String titleX, String titleY, XYSeriesCollection seriesCollection, XYSeriesCollection seriesCollectionSucces) {
        this.model = model;
        this.setBackground(Color.WHITE);    
        this.seriesCollection = seriesCollection;
        this.seriesCollectionSucces = seriesCollectionSucces;
        initLayout(graphTitle, titleX, titleY);
    }
}
