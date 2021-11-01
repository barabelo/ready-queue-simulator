package br.study.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class FifoScheduler implements CpuScheduler {
    @Override
    public List<CpuTask> schedule(List<Process> processes) {
        processes.sort(Process.procArrivalTimeComparator);
        List<CpuTask> cpuTasks = new ArrayList<>();

        for (int i = 0; i < processes.size(); i++) {
            Process process = processes.get(i);
            Instant start;
            if (i == 0) start = process.getArrivalTime();
            else {
                Instant lastCpuTaskEnd = cpuTasks.get(cpuTasks.size() - 1).getEnd();
                if (process.getArrivalTime().isAfter(lastCpuTaskEnd)) start = process.getArrivalTime();
                else start = lastCpuTaskEnd;
            }
            Instant end = start.plus(process.getBurstTime());
            cpuTasks.add(new CpuTask(process, start, end));
        }

        return cpuTasks;
    }
}
