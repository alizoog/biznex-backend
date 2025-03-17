package com.biznex.common.constant;

import lombok.Getter;

@Getter
public enum FileType {
    DRAFT("DRAFT FILE"),
    ACTIVE("ACTIVE FILE"),;

    private final String title;

    FileType(String title) {
        this.title = title;
    }
}
