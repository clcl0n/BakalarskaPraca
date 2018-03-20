package viewPanels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ConfusionMatrix extends JFrame {
    
    private MatrixPanel matrixPanel;
    
    public ConfusionMatrix(int records, int[] results, int[] desireResult) {
        super("Confusion Matrix");
        matrixPanel = new MatrixPanel(records, results, desireResult);
        this.add(matrixPanel);
        this.pack();
    }
    
    class MatrixPanel extends JPanel {
        
        private int outClass;
        private int records;
        private int[] results;
        private int[] desireResult;
        
        private String[][] matrixContentFrist;
        private String[][] matrixContentSecond;
        
        private int margin = 20;         
        private int rectLength = 100;
        
        private double[][] resultTop;
        private double[][] resultBot;
        
        private String numOfRecords(int numClass) {
            return String.valueOf(results[numClass]);
        }
        
        private void calculate() {
            int dimension = this.outClass + 1;
            resultTop = new double[dimension][dimension];
            resultBot = new double[dimension][dimension];
            int wrong, good;
            for(int r = 0; r < this.outClass; r++) {
                wrong = Math.abs(desireResult[r] - results[r]);
                good = desireResult[r] - wrong;
                if(good > 0) {
                    //good
                    resultTop[r][r] = good;//Math.abs(desireResult[r] - Math.abs(desireResult[r] - results[r]));
                    //goodP
                    resultBot[r][r] = (resultTop[r][r] / (records * 1.0) ) * 100.0;                        
                }
                //sumGood
                resultTop[this.outClass][this.outClass] += resultBot[r][r];
                for(int c = 0; c < this.outClass; c++) { 
                    if(c != r) {
                            //bad
                            wrong = Math.abs(desireResult[r] - results[r]);
                            if(wrong > 0) {
                                for(int i = 0; i < this.outClass; i++) {
                                    wrong = (desireResult[r] - results[r]);
                                    if(wrong > 0 && c != i) {
                                        resultTop[r][c] = wrong;
                                        resultBot[r][c] = (resultTop[r][c] / (records * 1.0) )  * 100.0;
                                        break;
                                    }
                                }
                            }
                        //badP
                        resultBot[this.outClass][this.outClass] += resultBot[r][c];
                        if(wrong > 0) {
                            break;
                        }
                    }
                }
            }
            
            double[] sumRowV = new double[this.outClass];
            for(int r = 0; r < this.outClass; r++) {
                for(int c = 0; c < this.outClass; c++) {
                    if(r != c) {
                        sumRowV[r] += resultTop[r][c];
                    }
                }
            }
            
            //set sum vertical
            for(int r = 0; r < this.outClass; r++) {
                resultTop[r][this.outClass] = (resultTop[r][r] / (sumRowV[r] + resultTop[r][r])) * 100.0;
                resultBot[r][this.outClass] = (sumRowV[r] / (sumRowV[r] + resultTop[r][r])) * 100.0;
            }
            
            double[] sumRowH = new double[this.outClass];
            for(int c = 0; c < this.outClass; c++) {
                for(int r = 0; r < this.outClass; r++) {
                    if(r != c) {
                        sumRowH[r] += resultTop[r][c];
                    }
                }
            }            
            
            //set sum horizontal
            for(int c = 0; c < this.outClass; c++) {
                resultTop[this.outClass][c] = (resultTop[c][c] / (sumRowH[c] + resultTop[c][c])) * 100.0;
                resultBot[this.outClass][c] = (sumRowH[c] / (sumRowH[c] + resultTop[c][c])) * 100.0;
            }
            int i = 0;
        }
        
        private void fillGoodBadCol(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.BLACK);
            int textWidth, x = margin, y = margin;
            String str;
            for(int r = 0; r < this.outClass; r++) {
                for(int c = 0; c < this.outClass; c++) {
                    //diagonal
                    if(r == c) {
                        str = String.format("%.0f", resultTop[r][r]);
                        textWidth = g.getFontMetrics().stringWidth(str);
                        g2.drawString(str, x + (rectLength / 2) - (textWidth / 2), y + (rectLength / 2));
                        str = String.format("%.1f", resultBot[r][r]) + "%";
                        textWidth = g.getFontMetrics().stringWidth(str);
                        g2.drawString(str, x + (rectLength / 2) - (textWidth / 2), y + (rectLength / 2) + 20);          
                    }
                    else {
                        str = String.format("%.0f", resultTop[r][c]);
                        textWidth = g.getFontMetrics().stringWidth(str);
                        g2.drawString(str, x + (rectLength / 2) - (textWidth / 2), y + (rectLength / 2));
                        str = String.format("%.1f", resultBot[r][c]) + "%";
                        textWidth = g.getFontMetrics().stringWidth(str);
                        g2.drawString(str, x + (rectLength / 2) - (textWidth / 2), y + (rectLength / 2) + 20);                          
                    }
                    x += rectLength;                    
                }
                x = margin;
                y += rectLength;
            }
        }
        
        private void fillSumCol(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            int textWidth, x = margin, y = margin;
            String str;
            
            //vertical
            x += this.outClass * rectLength;
            for(int r = 0; r < this.outClass; r++) {
                g2.setColor(Color.green);
                //good %
                str = String.format("%.1f", resultTop[r][this.outClass]) + "%";
                textWidth = g.getFontMetrics().stringWidth(str);
                g2.drawString(str, x + (rectLength / 2) - (textWidth / 2), y + (rectLength / 2));
                //bad %
                g2.setColor(Color.red);  
                str = String.format("%.1f", resultBot[r][this.outClass]) + "%";
                textWidth = g.getFontMetrics().stringWidth(str);
                g2.drawString(str, x + (rectLength / 2) - (textWidth / 2), y + (rectLength / 2) + 20);
                y += rectLength;                
            }       
            
            //horizontal
            x = margin;
            y = margin + rectLength * this.outClass;
            for(int c = 0; c < this.outClass; c++) {
                g2.setColor(Color.green);
                //good %
                str = String.format("%.1f", resultTop[this.outClass][c]) + "%";
                textWidth = g.getFontMetrics().stringWidth(str);
                g2.drawString(str, x + (rectLength / 2) - (textWidth / 2), y + (rectLength / 2));
                //bad %
                g2.setColor(Color.red);  
                str = String.format("%.1f", resultBot[this.outClass][c]) + "%";
                textWidth = g.getFontMetrics().stringWidth(str);
                g2.drawString(str, x + (rectLength / 2) - (textWidth / 2), y + (rectLength / 2) + 20);
                x += rectLength;
            }
            
            //Last
            x = margin + rectLength * this.outClass + 1;
            y = margin + rectLength * this.outClass + 1;
            g2.setColor(Color.green);
            //good %
            str = String.format("%.1f", resultTop[this.outClass][this.outClass]) + "%";
            textWidth = g.getFontMetrics().stringWidth(str);
            g2.drawString(str, x + (rectLength / 2) - (textWidth / 2), y + (rectLength / 2));
            //bad %
            g2.setColor(Color.red);  
            str = String.format("%.1f", resultBot[this.outClass][this.outClass]) + "%";
            textWidth = g.getFontMetrics().stringWidth(str);
            g2.drawString(str, x + (rectLength / 2) - (textWidth / 2), y + (rectLength / 2) + 20);            
        }
        
        private void printRes() {
            int size = this.results.length;
            for(int i = 0; i < size; i++) {
                System.out.println(desireResult[i]);
            }
        }
        
        @Override   
        protected void paintComponent(Graphics g) {
            calculate();
            
            String tmpStr = null;
            Graphics2D g2 = (Graphics2D) g;
            int fontSize = 20;
            g2.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
            int x = margin, y = margin;
            int width = rectLength, height = rectLength;
            for(int r = 0;r < (this.outClass + 1); r++) {
                for(int s = 0; s < (this.outClass + 1); s++) {
                    //good
                    if(r == s && r < this.outClass) {
                        g2.setColor(Color.green);
                        g2.fillRect(x, y, width, height);
                        g2.setColor(Color.black);

                        g2.setStroke(new BasicStroke(2));
                        g2.drawRect(x, y, width, height);                    
                    }
                    //sum good
                    else if(r == this.outClass && s == this.outClass) {
                        g2.setColor(Color.blue);
                        g2.fillRect(x, y, width, height);
                        g2.setColor(Color.black);

                        g2.setStroke(new BasicStroke(2));
                        g2.drawRect(x, y, width, height);                        
                    }
                    //row sum
                    else if(r == this.outClass || s == this.outClass) {
                        g2.setColor(Color.gray);
                        g2.fillRect(x, y, width, height);
                        g2.setColor(Color.black);

                        g2.setStroke(new BasicStroke(2));
                        g2.drawRect(x, y, width, height);
                    }
                    //bad
                    else {
                        g2.setColor(Color.red);
                        g2.fillRect(x, y, width, height);
                        g2.setColor(Color.black);

                        g2.setStroke(new BasicStroke(2));
                        g2.drawRect(x, y, width, height);                     
                    }
                    x += rectLength;
                }
                x = margin;
                y += rectLength;
            }
            fillGoodBadCol(g);
            fillSumCol(g);
            printRes();
        }
        
        public MatrixPanel(int records, int[] results, int desireResult[]) {
            this.outClass = results.length;
            this.records = records;
            this.results = results;
            this.desireResult = desireResult;
            this.setPreferredSize(new Dimension(800, 800));
        }
        
    }
    
}
