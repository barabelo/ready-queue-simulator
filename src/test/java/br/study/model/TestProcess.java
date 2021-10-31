package br.study.model;

import br.study.model.exception.InvalidProcessException;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;

public class TestProcess {

    @Test(expected = InvalidProcessException.class)
    public void testCreateProcessWithNegativeArrivalTime() throws InvalidProcessException {
        Process processWithNegativeArrivalTime = new Process(1, Instant.ofEpochMilli(-1), Duration.ofMillis(1));
    }

    @Test(expected = InvalidProcessException.class)
    public void testCreateProcessWithNegativeDuration() throws InvalidProcessException {
        Process processWithNegativeDuration = new Process(1, Instant.ofEpochMilli(1), Duration.ofMillis(-1));
    }

    @Test(expected = InvalidProcessException.class)
    public void testCreateProcessWithNullDuration() throws InvalidProcessException {
        Process processWithNegativeDuration = new Process(1, Instant.ofEpochMilli(1), Duration.ZERO);
    }
}
