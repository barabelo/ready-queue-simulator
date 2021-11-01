package br.study.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class FifoScheduler implements CpuScheduler {
    @Override
    public List<CpuTask> schedule(List<Process> processList) {
        processList.sort(Process.procArrivalTimeComparator);
        List<CpuTask> cpuTasks = new ArrayList<>();

        for (int i = 0; i < processList.size(); i++) {
            Process process = processList.get(i);
            Instant start;
            if (i == 0) start = process.getArrivalTime();
            else {
                Instant lastCpuTaskEnd = cpuTasks.get(cpuTasks.size() - 1).getEnd();
                if (process.getArrivalTime().isAfter(lastCpuTaskEnd)) throw new IllegalArgumentException("The " +
                        "algorithm cannot schedule the provided process list because it is not contiguous.");
                else start = lastCpuTaskEnd;
            }
            Instant end = start.plus(process.getBurstTime());
            cpuTasks.add(new CpuTask(process, start, end));
        }

        return cpuTasks;
    }
}
