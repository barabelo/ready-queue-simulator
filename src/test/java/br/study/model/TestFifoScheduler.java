package br.study.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class TestFifoScheduler {

    private final List<Process> contiguousProcessList = new ArrayList<>();
    private final List<CpuTask> expectedScheduleOfProcessesOnContiguousProcessList = new ArrayList<>();

    @Before
    public void initializeContiguousProcessListAndItsExpectedSchedule() {
        Process process1 = new Process(1, Instant.ofEpochMilli(0), Duration.ofMillis(24));
        Process process2 = new Process(2, Instant.ofEpochMilli(1), Duration.ofMillis(3));
        Process process3 = new Process(3, Instant.ofEpochMilli(2), Duration.ofMillis(3));
        contiguousProcessList.add(process1);
        contiguousProcessList.add(process2);
        contiguousProcessList.add(process3);

        CpuTask cpuTask1 = new CpuTask(process1, Instant.ofEpochMilli(0), Instant.ofEpochMilli(24));
        CpuTask cpuTask2 = new CpuTask(process2, Instant.ofEpochMilli(24), Instant.ofEpochMilli(27));
        CpuTask cpuTask3 = new CpuTask(process3, Instant.ofEpochMilli(27), Instant.ofEpochMilli(30));
        expectedScheduleOfProcessesOnContiguousProcessList.add(cpuTask1);
        expectedScheduleOfProcessesOnContiguousProcessList.add(cpuTask2);
        expectedScheduleOfProcessesOnContiguousProcessList.add(cpuTask3);
    }

    @Test
    public void testScheduleContiguousProcessList() {
        List<CpuTask> processesScheduledByTheAlgorithm = new FifoScheduler().schedule(contiguousProcessList);
        for (int i = 0; i < processesScheduledByTheAlgorithm.size(); i++) {
            Assert.assertEquals(processesScheduledByTheAlgorithm.get(i),
                    expectedScheduleOfProcessesOnContiguousProcessList.get(i));
        }
    }
}