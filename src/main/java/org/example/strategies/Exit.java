package org.example.strategies;

public class Exit implements MenuStrategy{
    @Override
    public void execute() {
        System.exit(0);
    }
}