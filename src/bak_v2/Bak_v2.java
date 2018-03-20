package bak_v2;

import viewPanels.FilePanel;
import viewPanels.NavBar;
import viewPanels.SettingsExtendPanel;
import viewPanels.SettingsPanel;
import viewPanels.TrainingPanel;

public class Bak_v2 {

    public static void main(String[] args) {
        Model neuModel = new Model();
        
        NavBar navBar = new NavBar();
        FilePanel filePanel = new FilePanel(neuModel);
        SettingsPanel settingsPanel = new SettingsPanel(neuModel);
        SettingsExtendPanel settingExtendPanel = new SettingsExtendPanel(neuModel);
        TrainingPanel trainingPanel = new TrainingPanel("Chyba sieťe", "Iterácie", "MSE", neuModel.getGraphData());
        
        AppView appView = new AppView(neuModel, navBar, filePanel, settingsPanel, settingExtendPanel, trainingPanel);
        
        MainControl mainControl = new MainControl(neuModel, appView, navBar, filePanel, settingsPanel, settingExtendPanel, trainingPanel);
        
    }
    
}
