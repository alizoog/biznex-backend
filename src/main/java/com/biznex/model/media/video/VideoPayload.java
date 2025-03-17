package com.biznex.model.media.video;

import com.biznex.common.constant.Name;
import com.biznex.common.constant.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoPayload {

    @NotNull
    private Name title;
    @NotBlank
    private String url;
    private Long photoId;
    @NotNull
    private Integer categoryId;
    @NotNull
    private Status status;
}
