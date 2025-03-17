package com.biznex.model.network;

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
public class NetworkPayload {

    @NotBlank
    private String url;
    @NotNull
    private Long photoId;
    @NotNull
    private Status status;
}
