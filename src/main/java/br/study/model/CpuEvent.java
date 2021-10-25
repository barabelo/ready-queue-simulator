package br.study.model;

public class CpuEvent {
    private Process process;
    private int startTime;
    private int finishTime;

    public CpuEvent(Process process, int startTime, int finishTime) {
        this.process = process;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public Process getProcess() {
        return process;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }
}
