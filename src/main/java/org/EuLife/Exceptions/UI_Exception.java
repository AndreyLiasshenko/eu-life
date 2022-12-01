package org.EuLife.Exceptions;

public class UI_Exception extends Exception {
    private String details;

    public UI_Exception(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "UI_Exception: "+ details;
    }
}
