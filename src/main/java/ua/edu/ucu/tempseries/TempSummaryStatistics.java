package ua.edu.ucu.tempseries;


public final class TempSummaryStatistics {

    private final double avgTemp;
    private final double devTemp;
    private final double minTemp;
    private final double maxTemp;

    public TempSummaryStatistics(double mean, double stDev, double min, double max) {
        avgTemp = mean;
        devTemp = stDev;
        minTemp = min;
        maxTemp = max;
    }

    public double getAvgTemp() {
        return avgTemp;
    }

    public double getDevTemp() {
        return devTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }
}
