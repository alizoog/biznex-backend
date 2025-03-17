package com.biznex.controller.client.media;

import com.biznex.common.constant.Status;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.request.SearchCriteria;
import com.biznex.common.request.TypeSearch;
import com.biznex.common.response.SuccessDataIterable;
import com.biznex.common.response.SuccessfulResponse;
import com.biznex.model.media.video.VideoResponse;
import com.biznex.model.media.video.VideoService;
import com.biznex.utils.WebConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(WebConstants.CLIENT_URL + "/videos")
@RequiredArgsConstructor
public class VideoClientController {

    private final VideoService videoService;

    @PostMapping("/pageable")
    public SuccessDataIterable<VideoResponse> getList(@Valid @RequestBody PageableRequest pageable) {
        pageable.getSearch().add(new SearchCriteria("status", "=", Status.ACTIVE, TypeSearch.STRING));
        Page<VideoResponse> response = videoService.getList(pageable).map(VideoResponse::minResponse);
        return new SuccessDataIterable<>(response);
    }

    @GetMapping("/{id}")
    public SuccessfulResponse<VideoResponse> getById(@PathVariable Long id) {
        VideoResponse response = VideoResponse.minResponse(videoService.changeViews(id));
        return new SuccessfulResponse<>(response);
    }

    @GetMapping("/last-active")
    public SuccessfulResponse<VideoResponse> getLastActive() {
        VideoResponse response = VideoResponse.minResponse(videoService.getLastByStatus(Status.ACTIVE));
        return new SuccessfulResponse<>(response);
    }
}
