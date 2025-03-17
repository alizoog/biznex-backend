package com.biznex.controller.client;

import com.biznex.common.constant.MediaType;
import com.biznex.common.constant.Status;
import com.biznex.model.mediacategory.MediaCategoryResponse;
import com.biznex.model.mediacategory.MediaCategoryService;
import com.biznex.utils.WebConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(WebConstants.CLIENT_URL + "/media-categories")
@RequiredArgsConstructor
public class MediaCategoryClientController {

    private final MediaCategoryService mediaCategoryService;

    @GetMapping("/list/{type}")
    public List<MediaCategoryResponse> getList(@PathVariable MediaType type) {
        return mediaCategoryService.getList(type, Status.ACTIVE)
                .stream()
                .map(MediaCategoryResponse::minResponse)
                .toList();
    }
}
