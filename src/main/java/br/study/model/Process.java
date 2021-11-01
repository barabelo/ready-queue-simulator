package br.study.model;

import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;

public class Process {
    private final int pid;
    private final Instant arrivalTime;
    private final Duration burstTime;

    public Process(int pid, Instant arrivalTime, Duration burstTime) {
        if (pid < 0) throw new IllegalArgumentException("A process cannot have a negative pid.");
        if (arrivalTime.isBefore(Instant.EPOCH)) throw new IllegalArgumentException("A process cannot arrive at an " +
                "Instant before Instant.EPOCH.");
        if (burstTime.isZero()) throw new IllegalArgumentException("A process cannot have a null duration.");
        if (burstTime.isNegative()) throw new IllegalArgumentException("A process cannot have a negative duration.");
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

    public static Comparator<Process> procArrivalTimeComparator = Comparator.comparing(Process::getArrivalTime);

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
