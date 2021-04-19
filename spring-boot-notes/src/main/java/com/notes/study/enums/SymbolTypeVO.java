package com.notes.study.enums;

import com.notes.study.annontions.EnumValidAnnotation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SymbolTypeVO implements Serializable {

    @NotBlank
    private String name;

    @NotNull
    @DecimalMin(value = "0",message = "count fromt is not")
    private BigDecimal count;
    /**
     * symbolType
     */
    @EnumValidAnnotation(message = "枚举类型值输入错误",	target = SymbolTypeEnum.class )
    private String symbolType = "bond";

    @Valid
    @Size(min = 1)
    private List<Other>  list;

}
