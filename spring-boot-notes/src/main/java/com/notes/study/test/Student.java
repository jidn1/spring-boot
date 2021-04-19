package com.notes.study.test;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Student {

    @JSONField(name="base-currency")
    private List<String> name = new ArrayList<>();

    @JSONField(name="base-address")
    private List<String> address= new ArrayList<>();

    private List<String> age= new ArrayList<>();
}
