package com.notes.study.mysqlbinlog;

@FunctionalInterface
public interface BinLogListener {

    void onEvent(LogItem item);
}
