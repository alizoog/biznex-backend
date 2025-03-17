package com.biznex.common.response;

import com.biznex.common.constant.Status;
import com.biznex.model.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TechnicalFieldsResponse {
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime createdAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime updatedAt;

    public User createdBy;

    public User updatedBy;

    private String message;

    public Status status;
}
