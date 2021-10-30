package br.study.model;

public class AlreadyScheduledException extends Exception {
    public AlreadyScheduledException(String message) {
        super(message);
    }
}
