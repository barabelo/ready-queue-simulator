package br.study.model;

import br.study.model.exception.ScheduleException;

import java.util.List;

public interface CpuScheduler {
    List<CpuTask> schedule(List<Process> processList) throws ScheduleException;
}
