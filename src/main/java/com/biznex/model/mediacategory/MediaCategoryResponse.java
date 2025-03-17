package com.biznex.model.mediacategory;

import com.biznex.common.constant.MediaType;
import com.biznex.common.constant.Name;
import com.biznex.common.constant.Status;
import com.biznex.common.response.TechnicalFieldsResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MediaCategoryResponse extends TechnicalFieldsResponse {
    private Integer id;
    private Name name;
    private MediaType type;
    private Status status;

    public MediaCategoryResponse(MediaCategory mediaCategory) {
        this.id = mediaCategory.getId();
        this.name = mediaCategory.getName();
        this.type = mediaCategory.getType();
        this.status = mediaCategory.getStatus();
        this.createdAt = mediaCategory.getCreatedAt();
        this.updatedAt = mediaCategory.getUpdatedAt();
    }

    public static MediaCategoryResponse minResponse(MediaCategory mediaCategory) {
        MediaCategoryResponse mediaCategoryResponse = new MediaCategoryResponse();
        mediaCategoryResponse.setId(mediaCategory.getId());
        mediaCategoryResponse.setName(mediaCategory.getName());
        return mediaCategoryResponse;
    }
}
