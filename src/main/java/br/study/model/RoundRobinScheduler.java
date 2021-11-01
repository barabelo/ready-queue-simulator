package br.study.model;

import java.util.List;

public class RoundRobinScheduler implements CpuScheduler {

    private int quantum;

    public RoundRobinScheduler(int quantum) {
        if (quantum == 0) throw new IllegalArgumentException("The quantum value cannot be zero.");
        if (quantum < 0) throw new IllegalArgumentException("The quantum value cannot be negative.");
        this.quantum = quantum;
    }

    public void setQuantum(int quantum) {
        if (quantum == 0) throw new IllegalArgumentException("The quantum value cannot be zero.");
        if (quantum < 0) throw new IllegalArgumentException("The quantum value cannot be negative.");
        this.quantum = quantum;
    }

    @Override
    public List<CpuTask> schedule(List<Process> processList) {

        return null;
    }
}
