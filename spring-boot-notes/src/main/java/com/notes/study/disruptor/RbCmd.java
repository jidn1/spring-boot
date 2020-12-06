package com.notes.study.disruptor;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class RbCmd {


    public int code;

    public String msg;


}
