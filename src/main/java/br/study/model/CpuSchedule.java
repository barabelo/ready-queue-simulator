package br.study.model;

import br.study.model.exception.ScheduleException;

import java.util.List;

public class CpuSchedule {
    private List<CpuTask> cpuTasks;

    public List<CpuTask> getCpuEvents() {
        return cpuTasks;
    }

    public void scheduleEvent(CpuTask cpuTask) throws ScheduleException {
        if (alreadyScheduled(cpuTask)) throw new ScheduleException("This CPU event has already been " +
                "scheduled.");
        if (hasConflict(cpuTask)) throw new ScheduleException("There is already a CPU scheduled at this time" +
                ".");
        cpuTasks.add(cpuTask);
    }

    public boolean hasConflict(CpuTask newEvent) {
        for (CpuTask event : cpuTasks) {
            if ((newEvent.getStart().isAfter(event.getStart()) && newEvent.getStart().isBefore(event.getEnd()))
                    || (newEvent.getEnd().isAfter(event.getStart()) && newEvent.getEnd().isBefore(event.getEnd())))
                return true;
        }
        return false;
    }

    public boolean alreadyScheduled(CpuTask newEvent) {
        for (CpuTask event : cpuTasks) {
            if (newEvent.equals(event)) return true;
        }
        return false;
    }

}
