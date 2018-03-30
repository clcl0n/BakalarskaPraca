package bak_v2;

import Workers.TrainWorker;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import viewPanels.SummaryPanel;
import viewPanels.FilePanel;
import viewPanels.NavBar;
import viewPanels.SettingsExtendPanel;
import viewPanels.SettingsPanel;
import viewPanels.TrainingPanel;

public class MainControl {

    private Model c_neuModel;
    private AppView c_appView;
    private NavBar c_navBar;
    private SettingsPanel c_settingsPanel;
    private SettingsExtendPanel c_settingExtendPanel;
    private TrainingPanel c_trainingPanel;
    private FilePanel c_filePanel;  
    private SummaryPanel c_endTrainingPanel;
    
    public MainControl(Model neuModel, AppView appView, NavBar navBar, FilePanel filePanel, SettingsPanel settingsPanel, SettingsExtendPanel settingExtendPanel, TrainingPanel trainingPanel, SummaryPanel endTrainingPanel) {
        this.c_neuModel = neuModel;
        this.c_appView = appView;
        this.c_navBar = navBar;
        this.c_settingsPanel = settingsPanel;
        this.c_settingExtendPanel = settingExtendPanel;
        this.c_trainingPanel = trainingPanel;
        this.c_filePanel = filePanel;
        this.c_endTrainingPanel = endTrainingPanel;
        
        c_settingsPanel.addToExtendSettingsActionListener(new NextToExtendSettingsListener());
        c_settingsPanel.addFilePathListener(new FilePathListener());
        c_settingExtendPanel.addTrainingActionListener(new TrainActionListener());
        c_settingExtendPanel.addRandomDivDataSetListener(new DivDataSetListener());
        c_settingExtendPanel.addPackagesDivDataSetListener(new DivDataSetListener());
        c_filePanel.addChooseFileTypeListener(new ChooseFileTypeListener());
        c_endTrainingPanel.addNeuModelActionListener(new NeuModelListener());
        c_endTrainingPanel.addConfusionMatrixListener(new ConfusionMatrixListener());
        c_trainingPanel.addNextEndListener(new NextTOEndTrainingPanel());
        c_endTrainingPanel.addSaveNetListener(new SaveNet());
        c_endTrainingPanel.addNewStartListener(new NewStartListener());
    }
    
    //Listener declarations
    
    class ConfusionMatrixListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            c_trainingPanel.ConfusionMatrix(c_neuModel.numOfRecordsTrain(), c_neuModel.getClassOutputTrain(), c_neuModel.getClassDesireOutputTrain());
        }
    
    }
    
    class NeuModelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            c_trainingPanel.showModel(c_neuModel.getInNeu(), c_neuModel.getHidNeu(), c_neuModel.getOutNeu(), c_neuModel.getInLayer(), c_neuModel.getOutLayer());
        }
        
    }
    
    class ChooseFileTypeListener implements ActionListener {

        private void disableInputASetValue(int inNeu, int outNeu, String FilePath) {
            c_neuModel.setFileDataPath(FilePath);
            c_neuModel.setInNeu(inNeu);
            c_neuModel.setOutNeu(outNeu);   
            c_settingsPanel.setInNeu(inNeu);
            c_settingsPanel.setOutNeu(outNeu);
            c_settingsPanel.setHidNeu();
            c_settingsPanel.setMaxIt();
            c_settingsPanel.setMaxErr();
            c_settingsPanel.setRate();
            c_settingsPanel.setMomentum();
            c_settingsPanel.disableInNeu();
            c_settingsPanel.disableOutNeu();
            c_settingsPanel.disableGetFile();
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            boolean isParkinson, isArrhytmia, isBreastCancer, isDermatology, isCustom;
            isParkinson = c_filePanel.isSelectedParkinson();
            isArrhytmia = c_filePanel.isSelectedArrhythmia();
            isBreastCancer = c_filePanel.isSelectedBreastCancer();
            isDermatology = c_filePanel.isSelectedDermatology();
            isCustom = c_filePanel.isSelectedCustom();
            if(isParkinson || isArrhytmia || isBreastCancer || isDermatology || isCustom) {
                if(isParkinson) {
                    c_neuModel.setIsParkinson(true);
                    disableInputASetValue(16, 2, "MedicalData/parkinsons.data.txt");
                }
                else if(isArrhytmia) {
                    c_neuModel.setIsArrhythmia(true);
                    c_neuModel.setIsParkinson(false);
                    disableInputASetValue(277, 10, "MedicalData/arrhythmia.data.csv");
                }
                else if(isBreastCancer) {
                    c_neuModel.setIsBreastCancer(true);
                    c_neuModel.setIsParkinson(false);
                    disableInputASetValue(30, 2, "MedicalData/BreastCancer.data.txt");
                }
                else if(isDermatology) {
                    c_neuModel.setIsDermatology(true);
                    c_neuModel.setIsParkinson(false);
                    disableInputASetValue(34, 6, "MedicalData/dermatology.data.txt");
                }
                else if (isCustom){
                    c_neuModel.setIsCustom(true);
                    c_neuModel.setIsParkinson(false);
                }
                c_appView.showSettingsPanel();
                }
            else {
                JOptionPane.showMessageDialog(new Frame(), "Vyber jedno");
            }
        }
        
    }
    
    class FilePathListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
            dialog.setMode(FileDialog.LOAD);
            dialog.setVisible(true);
            String directory = dialog.getDirectory();
            String file = dialog.getFile(); 
            if(directory != null && file != null) {
                c_neuModel.setFileDataPath(directory + file);
            }
        }
        
    }    
    
    class DivDataSetListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            boolean isRandom, isPackages;
            isRandom = c_settingExtendPanel.isSelectedRandom();
            isPackages = c_settingExtendPanel.isSelectedPackages();
            if(isRandom) {
                c_settingExtendPanel.enableRandom();
            }
            else {
                c_settingExtendPanel.enablePackages();
            }
        }
        
    }
    
    class TrainActionListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            boolean isSigmoida, isTanh, isGaussian;
            boolean isRandom, isPackages;
            isSigmoida = c_settingExtendPanel.isSelectedSigmoid();
            isTanh = c_settingExtendPanel.isSelectedTanh();
            isGaussian = c_settingExtendPanel.isSelectedGaussian();
            isRandom = c_settingExtendPanel.isSelectedRandom();
            isPackages = c_settingExtendPanel.isSelectedPackages();
            
            if(isSigmoida) {
                c_neuModel.setFnSigmoid();
            }
            else if(isTanh) {
                c_neuModel.setFnTanh();
            }
            else if(isGaussian) {
                c_neuModel.setFnGaussian();
            }
            
            if(isRandom) {
                c_neuModel.setTestDiv(Integer.valueOf(c_settingExtendPanel.getTestDiv()));
                c_neuModel.setTrainDiv(Integer.valueOf(c_settingExtendPanel.getTrainDiv()));
            }
            else {
                
            }

            if(c_neuModel.getIsParkinson()) {
                c_neuModel.createParkinsonNeuNet();
            }
            else if(c_neuModel.getIsArrhythmia()) {
                c_neuModel.createArrhythmiaNeuNet();
            }
            else if(c_neuModel.getIsDermatology()) {
                c_neuModel.createDermatologyNeuNet();
            }
            else if(c_neuModel.getIsBreastCancer()) {
                c_neuModel.createBreastCancerNeuNet();
            }
            TrainWorker work = new TrainWorker(c_appView, c_neuModel);
            work.execute();
        }
        
    }
    
    class NextTOEndTrainingPanel implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(c_neuModel.isEnd()) {
                c_appView.showEndTrainingPanel();
                c_endTrainingPanel.setLastSuccess();
                c_endTrainingPanel.setLastTrainMSE();
                c_endTrainingPanel.setLastTestMSE();
            }
            else {
                JOptionPane.showMessageDialog(new Frame(), "not finished");
            }
        }
        
    }
    
    class SaveNet implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            c_neuModel.saveNet();
        }
        
    }
    
    class NewStartListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            c_appView.showFilePanel();
            c_neuModel.clear();
        }
        
    }
    
    class NextToExtendSettingsListener implements ActionListener {

        public boolean isFilled() {
            
            boolean is_inNeu, is_hidNeu, is_outNeu, is_rate, is_momentum, is_maxErr, is_maxIt;
            int inNeu = 0, hidNeu = 0, outNeu = 0, maxIt = 0;
            String str;
            double rate = 0.0, momentum = 0.0, maxErr = 0.0;
            
            str = c_settingsPanel.getInNeu();
            try {
                inNeu = Integer.parseInt(str);
                is_inNeu = true;
            }
            catch(NumberFormatException nfe) {
                is_inNeu = false;
            }
            
            str = c_settingsPanel.getHidNeu();
            try {
                hidNeu = Integer.parseInt(str);
                is_hidNeu = true;
            }
            catch(NumberFormatException nfe) {
                is_hidNeu = false;
            }         
            
            str = c_settingsPanel.getOutNeu();
            try {
                outNeu = Integer.parseInt(str);
                is_outNeu = true;
            }
            catch(NumberFormatException nfe) {
                is_outNeu = false;
            }            
           
            str = c_settingsPanel.getMaxIt();
            try {
                maxIt = Integer.parseInt(str);
                is_maxIt = true;
            }
            catch(NumberFormatException nfe) {
                is_maxIt = false;
            }               
            
            str = c_settingsPanel.getOutNeu();
            try {
                momentum = Double.parseDouble(str);
                is_momentum = true;
            }
            catch(NumberFormatException nfe) {
                is_momentum = false;
            }               
            
            str = c_settingsPanel.getRate();
            try {
                rate = Double.parseDouble(str);
                is_rate = true;
            }
            catch(NumberFormatException nfe) {
                is_rate = false;
            }             
            
            str = c_settingsPanel.getMaxErr();
            try {
                maxErr = Double.parseDouble(str);
                is_maxErr = true;
            }
            catch(NumberFormatException nfe) {
                is_maxErr = false;
            }             
            
            if(is_inNeu && is_hidNeu && is_outNeu && is_maxIt && is_momentum && is_rate && is_maxErr) {
                //Add values to model
                c_neuModel.setInNeu(inNeu);
                c_neuModel.setHidNeu(hidNeu);
                c_neuModel.setOutNeu(outNeu);
                c_neuModel.setMaxIt(maxIt);
                c_neuModel.setMomentum(momentum);
                c_neuModel.setRate(rate);
                c_neuModel.setMaxErr(maxErr);
                
                return true;
            }
            else {
                return false;
            }
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            if(!isFilled()) {
                JOptionPane.showMessageDialog(new Frame(), "Bad input");
            }
            else {
                c_settingExtendPanel.setTestDIv();
                c_settingExtendPanel.setTrainDiv();
                c_appView.showExtendSettingsPanel();                
            }
        }
        
    }
    
}
