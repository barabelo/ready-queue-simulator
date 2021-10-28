package br.study.model;

import java.util.concurrent.TimeUnit;

public class Process {
    private final int pid;
    private final int arrivalTime;
    private final int burstTime;
    private final TimeUnit timeUnit;

    public Process(int pid, int arrivalTime, int burstTime, TimeUnit timeUnit) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.timeUnit = timeUnit;
    }

    public int getPid() {
        return pid;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Process process = (Process) o;

        return pid == process.pid;
    }

    @Override
    public int hashCode() {
        return pid;
    }
}
