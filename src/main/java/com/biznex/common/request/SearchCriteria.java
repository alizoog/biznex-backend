package com.biznex.common.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {
    @NotNull
    private String key;
    @Pattern(regexp = "^(^(!=)?|^(<=)?|^(>=)?|^(=)?|^(<)?|^(>)?|^(in)?|(%_)?|^(_%)?|^(%_%)?)$")
    private String operation;
    private Object value;
    @NotNull
    private TypeSearch type;
}
