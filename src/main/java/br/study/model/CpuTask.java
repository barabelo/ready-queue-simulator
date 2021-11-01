package br.study.model;

import java.time.Instant;

public class CpuTask {
    private final Process process;
    private final Instant start;
    private final Instant end;

    public CpuTask(Process process, Instant start, Instant end) {
        if (start.isBefore(Instant.EPOCH)) throw new IllegalArgumentException("A cpu task cannot start at an Instant " +
                "before Instant.EPOCH.");
        if (end.equals(Instant.EPOCH)) throw new IllegalArgumentException("A cpu task cannot end at an Instant equal " +
                "to Instant.EPOCH");
        if (end.equals(start)) throw new IllegalArgumentException("A CPU task cannot end at the same time it begins.");
        if (end.isBefore(start)) throw new IllegalArgumentException("A CPU task cannot end before it begins.");
        this.process = process;
        this.start = start;
        this.end = end;
    }

    public Process getProcess() {
        return process;
    }

    public Instant getStart() {
        return start;
    }

    public Instant getEnd() {
        return end;
    }


}
