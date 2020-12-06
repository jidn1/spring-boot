package com.notes.study.spring.conditionalDemo;

public class MacService implements  ListService {
    @Override
    public String showListCmd() {
        return "ls";
    }
}
