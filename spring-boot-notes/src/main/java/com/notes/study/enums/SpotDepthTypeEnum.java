package com.notes.study.enums;

import lombok.Getter;

/**
 * @Author: allen
 * @Date: 2021-05-19 17:45
 * @DES: 深度精度合并类型
 */
@Getter
public enum SpotDepthTypeEnum {

    /**
     * step0
     */
    STEP0("step0", "1"),

    /**
     * step1
     */
    STEP1("step1", "10"),

    /**
     * step2
     */
    STEP2("step2", "100"),

    /**
     * step3
     */
    STEP3("step3", "1000"),

    /**
     * step4
     */
    STEP4("step4", "10000"),

    /**
     * step5
     */
    STEP5("step5", "100000"),
    ;

    private final String code;
    private final String value;

    SpotDepthTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static SpotDepthTypeEnum toEnum(String code) {
        for (SpotDepthTypeEnum item : SpotDepthTypeEnum.values()) {
            if (item.code.equalsIgnoreCase(code)) {
                return item;
            }
        }
        return null;
    }
}
