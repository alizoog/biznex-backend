package com.biznex.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JobTypeEnum {
    FULL_TIME_REMOTE("To‘liq stavka (Masofaviy)", "Полная ставка (Удаленно)", "Full-time Remote"),
    FULL_TIME_ONSITE("To‘liq stavka (Ofisda)", "Полная ставка (Офис)", "Full-time Onsite"),
    FULL_TIME_HYBRID("To‘liq stavka (Gibrid)", "Полная ставка (Гибрид)", "Full-time Hybrid"),
    PART_TIME_REMOTE("Yarim stavka (Masofaviy)", "Частичная ставка (Удаленно)", "Part-time Remote"),
    PART_TIME_ONSITE("Yarim stavka (Ofisda)", "Частичная ставка (Офис)", "Part-time Onsite"),
    PART_TIME_HYBRID("Yarim stavka (Gibrid)", "Частичная ставка (Гибрид)", "Part-time Hybrid");

    private final String titleUz;
    private final String titleRu;
    private final String titleEn;
}
