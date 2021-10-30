package br.study.model;

import br.study.model.exception.ScheduleException;

import java.util.List;

public class CpuSchedule {
    private List<CpuEvent> cpuEvents;

    public List<CpuEvent> getCpuEvents() {
        return cpuEvents;
    }

    public void scheduleEvent(CpuEvent cpuEvent) throws ScheduleException {
        if (alreadyScheduled(cpuEvent)) throw new ScheduleException("This CPU event has already been " +
                "scheduled.");
        if (hasConflict(cpuEvent)) throw new ScheduleException("There is already a CPU scheduled at this time" +
                ".");
        cpuEvents.add(cpuEvent);
    }

    public boolean hasConflict(CpuEvent newEvent) {
        for (CpuEvent event : cpuEvents) {
            if ((newEvent.getStart().isAfter(event.getStart()) && newEvent.getStart().isBefore(event.getEnd()))
                    || (newEvent.getEnd().isAfter(event.getStart()) && newEvent.getEnd().isBefore(event.getEnd())))
                return true;
        }
        return false;
    }

    public boolean alreadyScheduled(CpuEvent newEvent) {
        for (CpuEvent event : cpuEvents) {
            if (newEvent.equals(event)) return true;
        }
        return false;
    }
}
