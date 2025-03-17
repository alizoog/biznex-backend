package com.biznex.model.blog;

import com.biznex.common.constant.BlogType;
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
public class BlogPayload {

    @NotNull
    private Name name;
    @NotNull
    private Name title;
    @NotNull
    private Name description;
    private Long photoId;
    @NotNull
    private BlogType type;
    @NotNull
    private Status status;
}
