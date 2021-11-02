package br.study.model;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoundRobinScheduler implements CpuScheduler {

    private final Duration quantum;

    public RoundRobinScheduler(Duration quantum) {
        if (quantum.isZero()) throw new IllegalArgumentException("The quantum value cannot be zero.");
        if (quantum.isNegative()) throw new IllegalArgumentException("The quantum value cannot be negative.");
        this.quantum = quantum;
    }

    private Instant calcScheduleStart(List<Process> processList) {
        if (processList.isEmpty()) throw new IllegalArgumentException("The process list must not be empty.");
        Process firstProcess = processList.get(0);
        return firstProcess.getArrivalTime();
    }

    private List<Duration> getBurstTimes(List<Process> processList) {
        if (processList.isEmpty()) throw new IllegalArgumentException("The process list must not be empty.");
        return processList.stream().map(Process::getBurstTime).collect(Collectors.toList());
    }

    @Override
    public List<CpuTask> schedule(List<Process> processList) {
        if (processList.isEmpty()) throw new IllegalArgumentException("The process list must not be empty.");

        List<CpuTask> cpuTasks = new ArrayList<>();
        processList.sort(Process.procArrivalTimeComparator);
        List<Duration> burstTimesLeft = getBurstTimes(processList);
        Instant timeCounter = calcScheduleStart(processList);
        int processSwitch = 0;
        int finishedProcesses = 0;

        while (finishedProcesses < processList.size()) {
            Process taskProcess = processList.get(processSwitch);
            Instant taskProcessArrTime = taskProcess.getArrivalTime();
            if (taskProcessArrTime.isAfter(timeCounter)) timeCounter = taskProcessArrTime;
            Instant taskStart = timeCounter;
            Instant taskEnd;
            Duration burstTimeLeft = burstTimesLeft.get(processSwitch);

            if (finishedProcesses == processList.size() - 1) {
                taskEnd = taskStart.plus(burstTimeLeft);
                cpuTasks.add(new CpuTask(taskProcess, taskStart, taskEnd));
                finishedProcesses++;
            } else if (burstTimeLeft.compareTo(quantum) > 0) {
                timeCounter = timeCounter.plus(quantum);
                taskEnd = taskStart.plus(quantum);
                cpuTasks.add(new CpuTask(taskProcess, taskStart, taskEnd));
                burstTimesLeft.set(processSwitch, burstTimeLeft.minus(quantum));
            } else if (!burstTimeLeft.isZero()) {
                timeCounter = timeCounter.plus(burstTimeLeft);
                taskEnd = taskStart.plus(burstTimeLeft);
                cpuTasks.add(new CpuTask(taskProcess, taskStart, taskEnd));
                burstTimesLeft.set(processSwitch, Duration.ZERO);
                finishedProcesses++;
            }
            if (processSwitch < processList.size() - 1) processSwitch++;
            else processSwitch = 0;
        }
        return cpuTasks;
    }

}
