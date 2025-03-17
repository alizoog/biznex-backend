package com.biznex.common.exception;

import com.biznex.common.response.status.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiException extends RuntimeException {
    private ResponseStatus responseStatus;
}
