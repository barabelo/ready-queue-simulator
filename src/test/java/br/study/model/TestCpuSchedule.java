package br.study.model;

import br.study.model.exception.InvalidProcessException;
import org.junit.Before;

import java.time.Duration;
import java.time.Instant;

public class TestCpuSchedule {

    @Before
    public void initialize() throws InvalidProcessException {
        Process p1 = new Process(1, Instant.ofEpochMilli(0), Duration.ofMillis(1));
        Process p2 = new Process(2, Instant.ofEpochMilli(0), Duration.ofMillis(2));
        Process p3 = new Process(3, Instant.ofEpochMilli(0), Duration.ofMillis(3));
        Process p4 = new Process(4, Instant.ofEpochMilli(0), Duration.ofMillis(4));
    }
}
