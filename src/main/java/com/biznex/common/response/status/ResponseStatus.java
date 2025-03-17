package com.biznex.common.response.status;

import com.biznex.common.constant.MessageType;
import org.springframework.http.HttpStatus;

public interface ResponseStatus {
    Integer getStatus();

    MessageType getMessageType();

    HttpStatus getHttpStatus();

    String name();
}
