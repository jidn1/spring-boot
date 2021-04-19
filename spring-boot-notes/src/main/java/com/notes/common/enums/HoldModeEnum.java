package com.notes.common.enums;

public enum HoldModeEnum {

    SINGLE_SIDE_HOLD(1, "single"),  //单向持仓
    DOUBLE_SIDE_HOLD(2, "twoWay"), //双向持仓
    ;


    private Integer code;

    private String value;

    HoldModeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static HoldModeEnum toEnum(Integer code) {
        for (HoldModeEnum ver : HoldModeEnum.values()) {
            if (ver.getCode().equals(code)) {
                return ver;
            }
        }
        return null;
    }

    public static HoldModeEnum of(String value) {
        for (HoldModeEnum ver : HoldModeEnum.values()) {
            if (ver.getValue().equals(value)) {
                return ver;
            }
        }
        return null;
    }

    public boolean isThisType(HoldModeEnum holdModeEnum) {
        return this == holdModeEnum;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}