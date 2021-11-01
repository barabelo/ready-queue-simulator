package br.study.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class RoundRobinScheduler implements CpuScheduler {

    private Duration quantum;

    public RoundRobinScheduler(Duration quantum) {
        if (quantum.isZero()) throw new IllegalArgumentException("The quantum value cannot be zero.");
        if (quantum.isNegative()) throw new IllegalArgumentException("The quantum value cannot be negative.");
        this.quantum = quantum;
    }

    @Override
    public List<CpuTask> schedule(List<Process> processList) {
        processList.sort(Process.procArrivalTimeComparator);
        List<CpuTask> cpuTasks = new ArrayList<>();
        return null;
    }
}
