package com.biznex.model.translation;

import com.biznex.common.constant.Name;
import com.biznex.common.constant.TranslationType;
import com.biznex.common.response.TechnicalFieldsResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TranslationResponse extends TechnicalFieldsResponse {
    private Long id;
    private Name name;
    private String tag;
    private List<TranslationType> types;

    public TranslationResponse(Translation translation) {
        this.id = translation.getId();
        this.name = translation.getName();
        this.tag = translation.getTag();
        this.types = translation.getTypes();
        this.status = translation.getStatus();
    }
}
