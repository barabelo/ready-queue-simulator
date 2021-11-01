package br.study.model;

import java.util.List;

public interface CpuScheduler {
    List<CpuTask> schedule(List<Process> processList);
}
