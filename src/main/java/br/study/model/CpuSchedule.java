package br.study.model;

import java.util.List;

public class CpuSchedule {
    private List<CpuEvent> cpuEvents;

    public List<CpuEvent> getCpuEvents() {
        return cpuEvents;
    }

    public void scheduleEvent(CpuEvent cpuEvent) throws ScheduleConflictException, AlreadyScheduledException {
        if (alreadyScheduled(cpuEvent)) throw new AlreadyScheduledException("Este evento de CPU já foi agendado.");
        if (scheduleConflict(cpuEvent)) throw new ScheduleConflictException("Já existe um evento de CPU agendado para" +
                " este período de tempo.");
        cpuEvents.add(cpuEvent);
    }

    private boolean scheduleConflict(CpuEvent newEvent) {
        for (CpuEvent event : cpuEvents) {
            if ((newEvent.getStart().isAfter(event.getStart()) && newEvent.getStart().isBefore(event.getEnd()))
                    || (newEvent.getEnd().isAfter(event.getStart()) && newEvent.getEnd().isBefore(event.getEnd()))) {
                return true;
            }
        }
        return false;
    }

    private boolean alreadyScheduled(CpuEvent newEvent) {
        for (CpuEvent event : cpuEvents) {
            if (newEvent.equals(event)) return true;
        }
        return false;
    }
}
