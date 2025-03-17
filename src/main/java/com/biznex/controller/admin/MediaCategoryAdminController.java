package com.biznex.controller.admin;

import com.biznex.utils.WebConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.biznex.common.exception.ApiException;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.response.SuccessDataIterable;
import com.biznex.common.response.SuccessfulResponse;
import com.biznex.common.response.status.MediaCategoryStatus;
import com.biznex.model.mediacategory.MediaCategoryPayload;
import com.biznex.model.mediacategory.MediaCategoryResponse;
import com.biznex.model.mediacategory.MediaCategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(WebConstants.ADMIN_URL + "/media-categories")
@RequiredArgsConstructor
public class MediaCategoryAdminController {

    private final MediaCategoryService mediaCategoryService;

    @PreAuthorize(value = "hasAuthority('VIEW_MEDIA_CATEGORIES')")
    @PostMapping("/pageable")
    public SuccessDataIterable<MediaCategoryResponse> getList(@Valid @RequestBody PageableRequest pageable) {
        Page<MediaCategoryResponse> response = mediaCategoryService.getList(pageable).map(MediaCategoryResponse::new);
        return new SuccessDataIterable<>(response);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_MEDIA_CATEGORY')")
    @GetMapping("/{id}")
    public SuccessfulResponse<MediaCategoryResponse> getById(@PathVariable Integer id) {
        MediaCategoryResponse response = new MediaCategoryResponse(mediaCategoryService.getById(id));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('CREATE_MEDIA_CATEGORY')")
    @PostMapping
    public SuccessfulResponse<MediaCategoryResponse> create(@Valid @RequestBody MediaCategoryPayload payload) {
        MediaCategoryResponse response = new MediaCategoryResponse(mediaCategoryService.create(payload));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('UPDATE_MEDIA_CATEGORY')")
    @PutMapping("/{id}")
    public SuccessfulResponse<MediaCategoryResponse> update(@PathVariable Integer id, @Valid @RequestBody MediaCategoryPayload payload) {
        MediaCategoryResponse response = new MediaCategoryResponse(mediaCategoryService.update(id, payload));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_MEDIA_CATEGORY')")
    @DeleteMapping("/{id}")
    public SuccessfulResponse<?> delete(@PathVariable Integer id) {
        mediaCategoryService.delete(id);
        throw new ApiException(MediaCategoryStatus.DELETED);
    }
}
