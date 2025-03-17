package com.biznex.common.response.status;

import com.biznex.common.constant.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FileStatus implements ResponseStatus {
    NOT_FOUND(1, MessageType.WARNING, HttpStatus.BAD_REQUEST),
    DELETED(2, MessageType.INFO, HttpStatus.NO_CONTENT),
    TYPE_NOT_SUPPORTED(3, MessageType.WARNING, HttpStatus.BAD_REQUEST);

    private final Integer status;
    private final MessageType messageType;
    private final HttpStatus httpStatus;
}
