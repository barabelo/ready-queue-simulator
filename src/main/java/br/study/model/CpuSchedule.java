package br.study.model;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CpuSchedule {
    private List<CpuEvent> cpuEvents;
    private TimeUnit timeUnit;

    public CpuSchedule(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public List<CpuEvent> getCpuEvents() {
        return cpuEvents;
    }

    public void scheduleEvent(CpuEvent cpuEvent) throws ScheduleConflictException, AlreadyScheduledException {
        if (alreadyScheduled(cpuEvent)) throw new AlreadyScheduledException("Este evento de CPU já foi agendado.");
        if (scheduleConflict(cpuEvent)) throw new ScheduleConflictException("Já existe um evento de CPU agendado para" +
                " este período de tempo.");
        cpuEvents.add(cpuEvent);
    }

    private boolean scheduleConflict(CpuEvent newCpuEvent) {
        for (CpuEvent cpuEvent : cpuEvents) {
            long newCpuEventStart = timeUnit.convert(newCpuEvent.getStart(), newCpuEvent.getTimeUnit());
            long newCpuEventEnd = timeUnit.convert(newCpuEvent.getEnd(), newCpuEvent.getTimeUnit());
            long cpuEventStart = timeUnit.convert(cpuEvent.getStart(), cpuEvent.getTimeUnit());
            long cpuEventEnd = timeUnit.convert(cpuEvent.getEnd(), cpuEvent.getTimeUnit());
            if((newCpuEventStart >= cpuEventStart && newCpuEventStart < cpuEventEnd)
                    || (newCpuEventEnd > cpuEventStart && newCpuEventEnd <= cpuEventEnd)) return true;
        }
        return false;
    }

    private boolean alreadyScheduled(CpuEvent newCpuEvent) {
        for (CpuEvent cpuEvent : cpuEvents) {
            if (newCpuEvent.equals(cpuEvent)) return true;
        }
        return false;
    }
}
