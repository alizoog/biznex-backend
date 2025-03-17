package com.biznex.controller.admin.media;

import com.biznex.common.exception.ApiException;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.response.SuccessDataIterable;
import com.biznex.common.response.SuccessfulResponse;
import com.biznex.common.response.status.VideoStatus;
import com.biznex.model.media.video.VideoPayload;
import com.biznex.model.media.video.VideoResponse;
import com.biznex.model.media.video.VideoService;
import com.biznex.utils.WebConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(WebConstants.ADMIN_URL + "/videos")
@RequiredArgsConstructor
public class VideoAdminController {

    private final VideoService videoService;

    @PreAuthorize(value = "hasAuthority('VIEW_VIDEOS')")
    @PostMapping("/pageable")
    public SuccessDataIterable<VideoResponse> getList(@Valid @RequestBody PageableRequest pageable) {
        Page<VideoResponse> response = videoService.getList(pageable).map(VideoResponse::new);
        return new SuccessDataIterable<>(response);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_VIDEO')")
    @GetMapping("/{id}")
    public SuccessfulResponse<VideoResponse> getById(@PathVariable Long id) {
        VideoResponse response = new VideoResponse(videoService.getById(id));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('CREATE_VIDEO')")
    @PostMapping
    public SuccessfulResponse<VideoResponse> create(@Valid @RequestBody VideoPayload payload) {
        VideoResponse response = new VideoResponse(videoService.create(payload));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('UPDATE_VIDEO')")
    @PutMapping("/{id}")
    public SuccessfulResponse<VideoResponse> update(@PathVariable Long id, @Valid @RequestBody VideoPayload payload) {
        VideoResponse response = new VideoResponse(videoService.update(id, payload));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_VIDEO')")
    @DeleteMapping("/{id}")
    public SuccessfulResponse<?> delete(@PathVariable Long id) {
        videoService.delete(id);
        throw new ApiException(VideoStatus.DELETED);
    }
}
