package com.biznex.model.media.photo;

import com.biznex.common.constant.Name;
import com.biznex.common.response.TechnicalFieldsResponse;
import com.biznex.model.file.FileResponse;
import com.biznex.model.mediacategory.MediaCategoryResponse;
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
public class PhotoResponse extends TechnicalFieldsResponse {
    private Long id;
    private Name title;
    private FileResponse photo;
    private MediaCategoryResponse category;
    private Integer views;

    public PhotoResponse(Photo photo) {
        this.id = photo.getId();
        this.title = photo.getTitle();
        this.photo = FileResponse.minResponse(photo.getPhoto());
        this.category = MediaCategoryResponse.minResponse(photo.getCategory());
        this.views = photo.getViews();
        this.createdAt = photo.getCreatedAt();
        this.updatedAt = photo.getUpdatedAt();
        this.status = photo.getStatus();
    }

    public static PhotoResponse minResponse(Photo photo) {
        PhotoResponse photoResponse = new PhotoResponse();
        photoResponse.setId(photo.getId());
        photoResponse.setTitle(photo.getTitle());
        photoResponse.setPhoto(FileResponse.minResponse(photo.getPhoto()));
        photoResponse.setCategory(MediaCategoryResponse.minResponse(photo.getCategory()));
        photoResponse.setViews(photo.getViews());
        return photoResponse;
    }
}
