package br.study.model.exception;

import br.study.model.CpuScheduler;
import br.study.model.CpuTask;
import br.study.model.Process;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class FifoScheduler implements CpuScheduler {
    @Override
    public List<CpuTask> schedule(List<Process> processes) throws ScheduleException {
        processes.sort(Process.procArrivalTimeComparator);
        List<CpuTask> cpuTasks = new ArrayList<>();

        for (int i = 0; i < processes.size(); i++) {
            Process process = processes.get(i);
            Instant start;
            Instant end;
            if (i == 0) start = process.getArrivalTime();
            else {
                Instant lastCpuTaskEnd = cpuTasks.get(cpuTasks.size() - 1).getEnd();
                if (process.getArrivalTime().isAfter(lastCpuTaskEnd)) start = process.getArrivalTime();
                else start = lastCpuTaskEnd;
            }
            end = start.plus(process.getBurstTime());
            try {
                cpuTasks.add(new CpuTask(process, start, end));
            } catch (CpuEventException e) {
                throw new ScheduleException("The provided process list cannot be scheduled by this algorithm.", e);
            }
        }

        return cpuTasks;
    }
}
