package com.biznex.model.media.audio;

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
public class AudioResponse extends TechnicalFieldsResponse {
    private Long id;
    private Name title;
    private FileResponse photo;
    private FileResponse audio;
    private MediaCategoryResponse category;
    private Integer views;

    public AudioResponse(Audio audio) {
        this.id = audio.getId();
        this.title = audio.getTitle();
        if (audio.getPhoto() != null) {
            this.photo = FileResponse.minResponse(audio.getPhoto());
        }
        this.audio = FileResponse.minResponse(audio.getAudio());
        this.category = MediaCategoryResponse.minResponse(audio.getCategory());
        this.views = audio.getViews();
        this.createdAt = audio.getCreatedAt();
        this.updatedAt = audio.getUpdatedAt();
        this.status = audio.getStatus();
    }

    public static AudioResponse minResponse(Audio audio) {
        AudioResponse audioResponse = new AudioResponse();
        audioResponse.setId(audio.getId());
        audioResponse.setTitle(audio.getTitle());
        if (audio.getPhoto() != null) {
            audioResponse.setPhoto(FileResponse.minResponse(audio.getPhoto()));
        }
        audioResponse.setAudio(FileResponse.minResponse(audio.getAudio()));
        audioResponse.setCategory(MediaCategoryResponse.minResponse(audio.getCategory()));
        audioResponse.setViews(audio.getViews());
        return audioResponse;
    }
}
