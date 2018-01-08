package bakneuroph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.ui.ApplicationFrame;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Line chart used to display values.
 */
public class Graph extends ApplicationFrame {
    private XYSeries dataSeries;
    private XYSeriesCollection seriesCollection;
    
    /**
     * Constructor for Graph class.
     * 
     * @param frameTitle Title of frame
     * @param graphTitle Title of Graph
     * @param nameData Name of DataSeries
     * @param titleX Name of X axis
     * @param titleY Name of Y axis
     */
    public Graph(String frameTitle, String graphTitle, String nameData, String titleX, String titleY){
        super(frameTitle);
        dataSeries = new XYSeries(nameData);
        seriesCollection = new XYSeriesCollection();
        seriesCollection.addSeries(dataSeries);
        
        JFreeChart chart = createChart(graphTitle, titleX, titleY);
        
        ChartPanel chartPanel = new ChartPanel( chart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }
    
    /**
     * Create new Chart object.
     * 
     * @param graphTitle Name of Graph    
     * @param titleX Name of X axis
     * @param titleY Name of Y axis
     * @return newly created Chart
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
     * Add values on x and y axis.
     * 
     * @param x value on X axis
     * @param y value on Y axis
     */
    public void addValues(int x, double y){
        dataSeries.add(x, y);
    }
    
}
