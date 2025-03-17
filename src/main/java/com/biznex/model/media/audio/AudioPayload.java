package com.biznex.model.media.audio;

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
public class AudioPayload {

    @NotNull
    private Name title;
    private Long photoId;
    @NotNull
    private Long audioId;
    @NotNull
    private Integer categoryId;
    @NotNull
    private Status status;
}
