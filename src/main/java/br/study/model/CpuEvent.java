package br.study.model;

import br.study.model.exception.CpuEventException;

import java.time.Instant;

public class CpuEvent {
    private final Process process;
    private final Instant start;
    private final Instant end;

    public CpuEvent(Process process, Instant start, Instant end) throws CpuEventException {
        if (end.isBefore(start)) throw new CpuEventException("A cpu event cannot end before it starts.");
        if (end.equals(start)) throw new CpuEventException("A cpu event cannot end at the same time it begins.");
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
