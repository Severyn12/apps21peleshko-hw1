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

    public double getSum(){
        double sum = 0;
        for (int i = 0; i < temperatures.length; i++){
            sum += temperatures[i];
        }
        return sum;
    }

    public double average() {
        if (temperatures.length == 0){
            throw new IllegalArgumentException();
        }
        double sum = getSum();

        return sum/ temperatures.length;
    }

    public double deviation() {

        if (temperatures.length == 0){
            throw new IllegalArgumentException();
        }

        double mean = average();
        double difference = 0;
        for (int i = 0; i < temperatures.length; i++){
            difference += Math.pow(mean - temperatures[i],2);
        }

        return Math.sqrt(difference/temperatures.length);
    }

    public double min() {

        if (temperatures.length == 0){
            throw new IllegalArgumentException();
        }

        double minimum = Double.POSITIVE_INFINITY;
        for (int i = 0; i < temperatures.length; i++){
            if (temperatures[i] < minimum) {
                minimum = temperatures[i];
            }
        }
        return minimum;
    }

    public double max() {

        if (temperatures.length == 0){
            throw new IllegalArgumentException();
        }

        double maximum = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < temperatures.length; i++){
            if (temperatures[i] > maximum) {
                maximum = temperatures[i];
            }
        }
        return maximum;
    }

    public double findTempClosestToZero() {
        double closest = 0;
        for (int i = 0; i < temperatures.length; i++){
            if (temperatures[i] == 0){
                return temperatures[i];
            }
            if (closest == 0) {
                closest = temperatures[i];
            } else if (temperatures[i] > 0 && temperatures[i] <= Math.abs(closest)) {
                closest = temperatures[i];
            } else if (temperatures[i] < 0 && - temperatures[i] < Math.abs(closest)) {
                closest = temperatures[i];
            }
        }

        return closest;
    }

    public double findTempClosestToValue(double tempValue) {
        double closest_ele = 0;
        double closest_dist = Double.POSITIVE_INFINITY;
        for (int i = 0; i < temperatures.length; i++){

            if ( Math.abs(tempValue - Math.abs(temperatures[i])) <= closest_dist){
                if (Math.abs(closest_ele) == Math.abs(temperatures[i])){
                    closest_ele = Math.abs(temperatures[i]);
                }
                else{
                    closest_ele = temperatures[i];
                }
                closest_dist = Math.abs(tempValue - Math.abs(temperatures[i]));
            }
        }
        return closest_ele;
    }

    public int idxFounder(double tempValue){
        Arrays.sort(temperatures);
        int idx = 0;
        while (idx < temperatures.length && temperatures[idx] < tempValue){
            idx++;
        }
        return idx;
    }

    public double[] findTempsLessThen(double tempValue) {


        return Arrays.copyOfRange(temperatures, 0,idxFounder(tempValue));
    }

    public double[] findTempsGreaterThen(double tempValue) {

        return Arrays.copyOfRange(temperatures, idxFounder(tempValue),temperatures.length);
    }


    public TempSummaryStatistics summaryStatistics() {
        TempSummaryStatistics statistics = new TempSummaryStatistics(average(), deviation(), min(), max());
        return statistics;
    }

    public int addTemps(double... temps) {

        double [] temp_copy = Arrays.copyOf(temperatures, temperatures.length);

        for (int i = 0; i < temps.length; i++) {

            if (temps[i] < -273.0){
                throw new InputMismatchException();
            }

            if (elementNum == temperatures.length) {
                double [] nwTemps = new double [elementNum*2];
                for (int j = 0; j < temperatures.length; j++){
                    nwTemps[j] = temp_copy[j];
                }
                temp_copy = nwTemps;
             }
            temp_copy[elementNum] = temps[i];
            elementNum++;
        }
        temperatures = temp_copy;
        return (int)getSum();
    }

    public double[] getTemperatures() {
        return temperatures;
    }
}
