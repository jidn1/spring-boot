package com.notes.study.enums;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Other implements Serializable {

    @NotBlank
    private String aaa;

    @NotBlank
    private String bbb;
}
