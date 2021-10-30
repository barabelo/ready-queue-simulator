package br.study.model;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class CpuEvent {
    private Process process;
    private Instant start;
    private Instant end;

    public CpuEvent(Process process, Instant start, Instant end) {
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
