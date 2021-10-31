package br.study.model;

import br.study.model.exception.InvalidProcessException;
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

    @Test(expected = InvalidProcessException.class)
    public void testCreateProcessWithNegativeArrivalTime() throws InvalidProcessException {
        Process processWithNegativeArrivalTime = new Process(1, Instant.ofEpochMilli(-1), Duration.ofMillis(1));
    }

    @Test(expected = InvalidProcessException.class)
    public void testCreateProcessWithNegativeDuration() throws InvalidProcessException {
        Process processWithNegativeDuration = new Process(1, Instant.ofEpochMilli(1), Duration.ofMillis(-1));
    }

    @Test(expected = InvalidProcessException.class)
    public void testCreateProcessWithNullDuration() throws InvalidProcessException {
        Process processWithNegativeDuration = new Process(1, Instant.ofEpochMilli(1), Duration.ZERO);
    }

    @Before
    public void initializeListOfProcessesWithDecreasingArrivalTimes() throws InvalidProcessException {
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
