package br.study.model;

import org.junit.BeforeClass;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;

public class TestCpuTask {

    private static Process validProcess;

    @BeforeClass
    public static void initializeValidProcess() {
        validProcess = new Process(1, Instant.ofEpochMilli(0), Duration.ofMillis(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCpuTaskWhichStartsAtANegativeInstant() {
        new CpuTask(validProcess, Instant.ofEpochMilli(-1), Instant.ofEpochMilli(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCpuTaskWhichEndsAtANullInstant() {
        new CpuTask(validProcess, Instant.ofEpochMilli(1), Instant.ofEpochMilli(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCpuTaskWhichEndsAtTheSameTimeItBegins() {
        new CpuTask(validProcess, Instant.ofEpochMilli(1), Instant.ofEpochMilli(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCpuTaskWhichEndsBeforeItBegins() {
        new CpuTask(validProcess, Instant.ofEpochMilli(2), Instant.ofEpochMilli(1));
    }
}