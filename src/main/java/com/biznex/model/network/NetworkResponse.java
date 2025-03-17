package com.biznex.model.network;

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
public class NetworkResponse extends TechnicalFieldsResponse {
    private Integer id;
    private String url;
    private FileResponse photo;

    public NetworkResponse(Network network) {
        this.id = network.getId();
        this.url = network.getUrl();
        this.photo = FileResponse.minResponse(network.getPhoto());
        this.status = network.getStatus();
        this.createdAt = network.getCreatedAt();
        this.updatedAt = network.getUpdatedAt();
    }

    public static NetworkResponse minResponse(Network network) {
        NetworkResponse networkResponse = new NetworkResponse();
        networkResponse.setId(network.getId());
        networkResponse.setUrl(network.getUrl());
        networkResponse.setPhoto(FileResponse.minResponse(network.getPhoto()));
        return networkResponse;
    }
}
