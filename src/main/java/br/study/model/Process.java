package br.study.model;

import java.time.Duration;
import java.time.Instant;

public class Process {
    private final int pid;
    private final Instant arrivalTime;
    private final Duration burstTime;

    public Process(int pid, Instant arrivalTime, Duration burstTime) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }

    public int getPid() {
        return pid;
    }

    public Instant getArrivalTime() {
        return arrivalTime;
    }

    public Duration getBurstTime() {
        return burstTime;
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
