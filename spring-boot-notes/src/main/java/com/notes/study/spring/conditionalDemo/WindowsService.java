package com.notes.study.spring.conditionalDemo;

import java.util.List;

public class WindowsService implements ListService {
    @Override
    public String showListCmd() {
        return "dir";
    }
}
