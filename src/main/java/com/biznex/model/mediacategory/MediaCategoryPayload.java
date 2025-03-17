package com.biznex.model.mediacategory;

import com.biznex.common.constant.MediaType;
import com.biznex.common.constant.Name;
import com.biznex.common.constant.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MediaCategoryPayload {

    @NotNull
    private Name name;
    @NotNull
    private MediaType type;
    @NotNull
    private Status status;
}
