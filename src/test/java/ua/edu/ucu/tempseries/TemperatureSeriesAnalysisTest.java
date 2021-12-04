package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;


import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();

        seriesAnalysis.deviation();
    }

    @Test
    public void functionTest() {
        double[] temperatureSeries = {3.0, -1.0, 2.0, -2.0, -5.0,  5.0, 9.0, 10.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 2.625;


        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);

        assertEquals(2.0, seriesAnalysis.findTempClosestToValue(1.5), 0.00001);

        assertEquals(-1.0, seriesAnalysis.findTempClosestToZero(), 0.00001);

        assertEquals(5.0, Math.round(seriesAnalysis.deviation()), 0.00001);

        assertEquals(10.0, seriesAnalysis.max(), 0.00001);

        assertEquals(-5.0, seriesAnalysis.min(), 0.00001);

        assertEquals(5, seriesAnalysis.findTempsLessThen(5.0).length );

        assertEquals(3, seriesAnalysis.findTempsGreaterThen(5.0).length );
    }

    @Test
    public void addTest() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.addTemps(new double [] {3.0});
        assertEquals(1, seriesAnalysis.getTemperatures().length );

    }

    @Test(expected = InputMismatchException.class)
    public void addErrorTest() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.addTemps(new double [] {-274.0});


    }
    

}
