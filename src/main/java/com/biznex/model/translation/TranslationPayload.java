package com.biznex.model.translation;

import com.biznex.common.constant.Name;
import com.biznex.common.constant.Status;
import com.biznex.common.constant.TranslationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TranslationPayload {

    @NotNull
    private Name name;
    @NotBlank
    private String tag;
    @NotNull
    private List<TranslationType> types;
    @NotNull
    private Status status;
}
