package br.study.model;

import java.util.concurrent.TimeUnit;

public class CpuEvent {
    private Process process;
    private int start;
    private int end;
    private TimeUnit timeUnit;

    public CpuEvent(Process process, int start, int end, TimeUnit timeUnit) throws InvalidTimePeriodException {
        if (end < start) throw new InvalidTimePeriodException("A CPU event cannot end before it begins.");
        if (end == start) throw new InvalidTimePeriodException("A CPU event cannot end at the same time it begins.");

        this.process = process;
        this.start = start;
        this.end = end;
        this.timeUnit = timeUnit;
    }

    public Process getProcess() {
        return process;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}
