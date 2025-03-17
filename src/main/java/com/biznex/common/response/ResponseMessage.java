package com.biznex.common.response;

import com.biznex.common.constant.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage {
    private MessageType type;
    private String message;
}
