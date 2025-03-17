package com.biznex.model.banner;

import com.biznex.common.constant.Name;
import com.biznex.common.response.TechnicalFieldsResponse;
import com.biznex.model.file.FileResponse;
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
public class BannerResponse extends TechnicalFieldsResponse {
    private Long id;
    private FileResponse photo;
    private Name title;

    public BannerResponse(Banner banner) {
        this.id = banner.getId();
        this.photo = FileResponse.minResponse(banner.getPhoto());
        this.status = banner.getStatus();
        this.title = banner.getTitle();
        this.setCreatedAt(banner.getCreatedAt());
        this.setUpdatedAt(banner.getUpdatedAt());
    }

    public static BannerResponse minResponse(Banner banner) {
        BannerResponse bannerResponse = new BannerResponse();
        bannerResponse.setId(banner.getId());
        bannerResponse.setPhoto(FileResponse.minResponse(banner.getPhoto()));
        bannerResponse.setTitle(banner.getTitle());
        return bannerResponse;
    }
}
