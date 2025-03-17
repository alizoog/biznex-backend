package com.biznex.controller.admin.media;

import com.biznex.common.exception.ApiException;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.response.SuccessDataIterable;
import com.biznex.common.response.SuccessfulResponse;
import com.biznex.common.response.status.PhotoStatus;
import com.biznex.model.media.photo.PhotoPayload;
import com.biznex.model.media.photo.PhotoResponse;
import com.biznex.model.media.photo.PhotoService;
import com.biznex.utils.WebConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(WebConstants.ADMIN_URL + "/photos")
@RequiredArgsConstructor
public class PhotoAdminController {

    private final PhotoService photoService;

    @PreAuthorize(value = "hasAuthority('VIEW_PHOTOS')")
    @PostMapping("/pageable")
    public SuccessDataIterable<PhotoResponse> getList(@Valid @RequestBody PageableRequest pageable) {
        Page<PhotoResponse> response = photoService.getList(pageable).map(PhotoResponse::new);
        return new SuccessDataIterable<>(response);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_PHOTO')")
    @GetMapping("/{id}")
    public SuccessfulResponse<PhotoResponse> getById(@PathVariable Long id) {
        PhotoResponse response = new PhotoResponse(photoService.getById(id));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('CREATE_PHOTO')")
    @PostMapping
    public SuccessfulResponse<PhotoResponse> create(@Valid @RequestBody PhotoPayload payload) {
        PhotoResponse response = new PhotoResponse(photoService.create(payload));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('UPDATE_PHOTO')")
    @PutMapping("/{id}")
    public SuccessfulResponse<PhotoResponse> update(@PathVariable Long id, @Valid @RequestBody PhotoPayload payload) {
        PhotoResponse response = new PhotoResponse(photoService.update(id, payload));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_PHOTO')")
    @DeleteMapping("/{id}")
    public SuccessfulResponse<?> delete(@PathVariable Long id) {
        photoService.delete(id);
        throw new ApiException(PhotoStatus.DELETED);
    }
}
