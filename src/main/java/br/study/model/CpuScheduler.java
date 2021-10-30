package br.study.model;

import java.util.List;

public interface CpuScheduler {
    List<CpuEvent> schedule(List<Process> processList);
}
