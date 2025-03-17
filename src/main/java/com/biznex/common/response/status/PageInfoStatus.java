package com.biznex.common.response.status;

import com.biznex.common.constant.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PageInfoStatus implements ResponseStatus {
    NOT_FOUND(1, MessageType.ERROR, HttpStatus.NOT_FOUND);

    private final Integer status;
    private final MessageType messageType;
    private final HttpStatus httpStatus;
}
