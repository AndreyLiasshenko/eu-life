package org.EuLife.Exceptions;

public class DAO_Exception extends Exception {
    private String details;

    public DAO_Exception(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "DAO_Exception: " + details;
    }
}
