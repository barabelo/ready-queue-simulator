package br.study.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class TestProcess {

    List<Process> listOfProcessesWithDecreasingArrivalTimes = new ArrayList<>();
    int lastIndexOfTheList;

    @Test(expected = IllegalArgumentException.class)
    public void testCreateProcessWithNegativePid() {
        new Process(-1, Instant.ofEpochMilli(0), Duration.ofMillis(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateProcessWithNegativeArrivalTime() {
        new Process(1, Instant.ofEpochMilli(-1), Duration.ofMillis(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateProcessWithNullDuration() {
        new Process(1, Instant.ofEpochMilli(1), Duration.ZERO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateProcessWithNegativeDuration() {
        new Process(1, Instant.ofEpochMilli(1), Duration.ofMillis(-1));
    }

    @Before
    public void initializeListOfProcessesWithDecreasingArrivalTimes() {
        Process processWithTheBiggestArrivalTime = new Process(3, Instant.ofEpochMilli(5), Duration.ofMillis(9));
        Process processWithTheSmallestArrivalTime = new Process(1, Instant.ofEpochMilli(1), Duration.ofMillis(9));
        listOfProcessesWithDecreasingArrivalTimes.add(processWithTheBiggestArrivalTime);
        listOfProcessesWithDecreasingArrivalTimes.add(processWithTheSmallestArrivalTime);
        lastIndexOfTheList = listOfProcessesWithDecreasingArrivalTimes.size() - 1;
    }

    @Test
    public void testProcArrivalTimeComparator() {
        listOfProcessesWithDecreasingArrivalTimes.sort(Process.procArrivalTimeComparator);
        Instant arrivalTimeOfTheFirstProcessOnTheSortedProcessList =
                listOfProcessesWithDecreasingArrivalTimes.get(0).getArrivalTime();
        Instant arrivalTimeOfTheSecondProcessOnTheSortedProcessList =
                listOfProcessesWithDecreasingArrivalTimes.get(lastIndexOfTheList).getArrivalTime();
        Assert.assertTrue(arrivalTimeOfTheFirstProcessOnTheSortedProcessList
                .isBefore(arrivalTimeOfTheSecondProcessOnTheSortedProcessList));
    }
}
