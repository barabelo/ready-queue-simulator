package br.study.model;

import br.study.model.exception.InvalidDurationException;

import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;

public class Process {
    private final int pid;
    private final Instant arrivalTime;
    private final Duration burstTime;

    public Process(int pid, Instant arrivalTime, Duration burstTime) throws InvalidDurationException {
        if (burstTime.isNegative()) throw new InvalidDurationException("A process burst rate cannot be negative.");
        if (burstTime.isZero()) throw new InvalidDurationException("A process burst rate cannot be null.");
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

    public static Comparator<Process> procArrivalTimeComparator = new Comparator<Process>() {
        @Override
        public int compare(Process o1, Process o2) {
            return o1.getArrivalTime().compareTo(o2.getArrivalTime());
        }
    };

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
