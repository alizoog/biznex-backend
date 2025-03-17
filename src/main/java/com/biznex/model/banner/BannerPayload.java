package com.biznex.model.banner;

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
public class BannerPayload {

    @NotNull
    private Long photoId;
    @NotNull
    private Status status;
    @NotNull
    private Name message;
}
