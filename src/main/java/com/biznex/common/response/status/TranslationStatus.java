package com.biznex.common.response.status;

import com.biznex.common.constant.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TranslationStatus implements ResponseStatus {
    NOT_FOUND(1, MessageType.ERROR, HttpStatus.NOT_FOUND),
    DELETED(2, MessageType.INFO, HttpStatus.NO_CONTENT),
    TAG_ALREADY_EXISTS(3, MessageType.ERROR, HttpStatus.CONFLICT), ACCESS_REQUIRED(4, MessageType.ERROR, HttpStatus.BAD_REQUEST);

    private final Integer status;
    private final MessageType messageType;
    private final HttpStatus httpStatus;
}
