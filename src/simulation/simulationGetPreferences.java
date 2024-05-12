package simulation;

import process.PreferencesCollector;
import process.*;
import simulation.*;

public class simulationGetPreferences {

    public static void main(String[] args) {
        PreferencesCollector collector = new PreferencesCollector();
        collector.collectPreferences();
        collector.displayPreferences();
        
    }
}
