package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;


import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverageWithOneElementArray() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;
        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.average();
    }

    @Test
    public void functionTest() {
        double[] temperatureSeries = {3.0, -1.0, 2.0, -2.0, -5.0,  5.0, 9.0, 10.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 2.625;


        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);

        assertEquals(-1.0, seriesAnalysis.findTempClosestToZero(), 0.00001);

        assertEquals(2.0, seriesAnalysis.findTempClosestToValue(1.5), 0.00001);

        assertEquals(5.0, Math.round(seriesAnalysis.deviation()), 0.00001);

        assertEquals(10.0, seriesAnalysis.max(), 0.00001);

        assertEquals(-5.0, seriesAnalysis.min(), 0.00001);

        assertEquals(5, seriesAnalysis.findTempsLessThen(5.0).length );

        assertEquals(3, seriesAnalysis.findTempsGreaterThen(5.0).length );
    }

    @Test
    public void addTest() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        assertEquals(3.0, seriesAnalysis.addTemps(new double [] {3.0}),0.00001);
        assertEquals(1, seriesAnalysis.getTemperatures().length );
    }

    @Test(expected = InputMismatchException.class)
    public void addErrorTest() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.addTemps(new double [] {-274.0});
    }

    @Test
    public void summaryStatisticsTest() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.addTemps(new double [] {3.0, 34.0, 8.0});
        TempSummaryStatistics statistics = seriesAnalysis.summaryStatistics();

        assertTrue(statistics instanceof TempSummaryStatistics);
        assertEquals(4, seriesAnalysis.getTemperatures().length );
        assertEquals(11.25, statistics.getAvgTemp(), 0.00001);
        assertEquals(13.0, Math.round(statistics.getDevTemp()), 0.00001);
        assertEquals(34.0, statistics.getMaxTemp(), 0.00001);
        assertEquals(3.0, statistics.getMinTemp(), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void minErrorTest() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.min();
    }

    @Test(expected = IllegalArgumentException.class)
    public void maxErrorTest() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.max();
    }

    @Test(expected = IllegalArgumentException.class)
    public void findTempsLessThenErrorTest() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.findTempsLessThen(5.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findTempsGreaterThenErrorTest() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.findTempsGreaterThen(7.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deviationErrorTest() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.deviation();
    }
}
