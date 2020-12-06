package com.notes.study.spring.conditionalDemo;

public class LinuxService implements ListService{
    @Override
    public String showListCmd() {

        return "ll";
    }
}
