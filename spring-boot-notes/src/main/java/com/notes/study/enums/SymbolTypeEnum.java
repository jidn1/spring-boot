package com.notes.study.enums;

public enum SymbolTypeEnum {
    STOCK("stocks","股票"),
    CRYPTO("cryptos","加密货币"),
    BOND("bonds","债券"),
    ;
    private String type;

    private String desc;

    SymbolTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }


    public static SymbolTypeEnum getByValue(String val) {
        for (SymbolTypeEnum v : values()) {
            if (v.getType().equals( val)) {
                return v;
            }
        }
        return null;
    }

}
