package com.biznex.model.media.video;

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
public class VideoResponse extends TechnicalFieldsResponse {
    private Long id;
    private Name title;
    private String url;
    private FileResponse photo;
    private MediaCategoryResponse category;
    private Integer views;

    public VideoResponse(Video video) {
        this.id = video.getId();
        this.title = video.getTitle();
        this.url = video.getUrl();
        if (video.getPhoto() != null) {
            this.photo = FileResponse.minResponse(video.getPhoto());
        }
        this.category = MediaCategoryResponse.minResponse(video.getCategory());
        this.views = video.getViews();
        this.createdAt = video.getCreatedAt();
        this.updatedAt = video.getUpdatedAt();
        this.status = video.getStatus();
    }

    public static VideoResponse minResponse(Video video) {
        if (video == null) return null;
        VideoResponse videoResponse = new VideoResponse();
        videoResponse.setId(video.getId());
        videoResponse.setTitle(video.getTitle());
        videoResponse.setUrl(video.getUrl());
        if (video.getPhoto() != null) {
            videoResponse.setPhoto(FileResponse.minResponse(video.getPhoto()));
        }
        videoResponse.setCategory(MediaCategoryResponse.minResponse(video.getCategory()));
        videoResponse.setViews(video.getViews());
        return videoResponse;
    }
}
