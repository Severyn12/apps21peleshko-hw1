package ua.edu.ucu.tempseries;


import java.util.Arrays;
import java.util.InputMismatchException;


public class TemperatureSeriesAnalysis {
    private double[] temperatures = new double[1];
    private int elementNum = 0;

    public TemperatureSeriesAnalysis() {    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        temperatures = temperatureSeries;
        elementNum = temperatureSeries.length;
    }

    public double getSum() {
        double sum = 0;
        for (int i = 0; i < temperatures.length; i++) {
            sum += temperatures[i];
        }
        return sum;
    }

    public double average() {
        if (elementNum == 0) {
            throw new IllegalArgumentException();
        }
        double sum = getSum();

        return sum / temperatures.length;
    }

    public double deviation() {
        if (elementNum == 0) {
            throw new IllegalArgumentException();
        }

        double mean = average();
        double difference = 0;
        for (int i = 0; i < temperatures.length; i++) {
            difference += Math.pow(mean - temperatures[i], 2);
        }

        return Math.sqrt(difference / temperatures.length);
    }

    public double min() {
        if (elementNum == 0) {
            throw new IllegalArgumentException();
        }

        double minimum = Double.POSITIVE_INFINITY;
        for (int i = 0; i < temperatures.length; i++) {
            if (temperatures[i] < minimum  && i < elementNum) {
                minimum = temperatures[i];
            }
        }
        return minimum;
    }

    public double max() {
        if (elementNum == 0) {
            throw new IllegalArgumentException();
        }

        double maximum = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < temperatures.length; i++) {
            if (temperatures[i] > maximum && i < elementNum) {
                maximum = temperatures[i];
            }
        }
        return maximum;
    }

    public double findTempClosestToZero() {
        if (elementNum == 0) {
            throw new IllegalArgumentException();
        }

        double closest = Double.POSITIVE_INFINITY;
        for (int i = 0; i < temperatures.length; i++) {
            if (temperatures[i] == 0) {
                return temperatures[i];
            } else if (temperatures[i] > 0
                    && temperatures[i] <= Math.abs(closest)) {
                closest = temperatures[i];
            } else if (temperatures[i] < 0
                    && -temperatures[i] < Math.abs(closest)) {
                closest = temperatures[i];
            }
        }

        return closest;
    }

    public double findTempClosestToValue(double tempValue) {
        if (elementNum == 0) {
            throw new IllegalArgumentException();
        }

        double closestEle = 0;
        double closestDist = Double.POSITIVE_INFINITY;
        for (int i = 0; i < temperatures.length; i++) {

            if (Math.abs(tempValue - Math.abs(temperatures[i])) <= closestDist) {
                if (Math.abs(closestEle) == Math.abs(temperatures[i])) {
                    closestEle = Math.abs(temperatures[i]);
                }
                else {
                    closestEle = temperatures[i];
                }
                closestDist = Math.abs(tempValue - Math.abs(temperatures[i]));
            }
        }
        return closestEle;
    }

    public int idxFounder(double tempValue) {
        Arrays.sort(temperatures);
        int idx = 0;
        while (idx < temperatures.length && temperatures[idx] < tempValue) {
            idx++;
        }
        return idx;
    }

    public double[] findTempsLessThen(double tempValue) {
        if (elementNum == 0) {
            throw new IllegalArgumentException();
        }
        return Arrays.copyOfRange(temperatures, 0, idxFounder(tempValue));
    }

    public double[] findTempsGreaterThen(double tempValue) {
        if (elementNum == 0) {
            throw new IllegalArgumentException();
        }
        return Arrays.copyOfRange(temperatures, idxFounder(tempValue), temperatures.length);
    }


    public TempSummaryStatistics summaryStatistics() {
        TempSummaryStatistics statistics = new TempSummaryStatistics(average(), deviation(), min(), max());
        return statistics;
    }

    public int addTemps(double... temps) {
        double[] tempCopy = Arrays.copyOf(temperatures, temperatures.length);
        int idx = 0;
        for (int i = 0; i < temps.length; i++) {

            if (temps[i] < -273.0) {
                throw new InputMismatchException();
            }

            if (elementNum == tempCopy.length) {
                double[] nwTemps = new double[elementNum * 2];
                for (int j = 0; j < tempCopy.length; j++) {
                    nwTemps[j] = tempCopy[j];
                }
                tempCopy = nwTemps;
            }
            tempCopy[idx] = temps[i];

            idx++;
            elementNum++;
        }
        temperatures = tempCopy;
        return (int) getSum();
    }

    public double[] getTemperatures() {
        return temperatures;
    }
}
