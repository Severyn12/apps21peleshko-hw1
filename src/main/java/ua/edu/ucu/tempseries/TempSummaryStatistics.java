package ua.edu.ucu.tempseries;


public final class TempSummaryStatistics {

    public final double avgTemp;
    public final double devTemp;
    private double minTemp;
    private double maxTemp;

    public TempSummaryStatistics(double mean, double stDev, double min, double max){
        avgTemp = mean;
        devTemp = stDev;
        minTemp = min;
        maxTemp = max;
    }

    
}
