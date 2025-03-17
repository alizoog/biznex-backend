package com.biznex.controller.client.media;

import com.biznex.common.constant.Status;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.request.SearchCriteria;
import com.biznex.common.request.TypeSearch;
import com.biznex.common.response.SuccessDataIterable;
import com.biznex.common.response.SuccessfulResponse;
import com.biznex.model.media.audio.AudioResponse;
import com.biznex.model.media.audio.AudioService;
import com.biznex.utils.WebConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(WebConstants.CLIENT_URL + "/audios")
@RequiredArgsConstructor
public class AudioClientController {

    private final AudioService audioService;

    @PostMapping("/pageable")
    public SuccessDataIterable<AudioResponse> getList(@Valid @RequestBody PageableRequest pageable) {
        pageable.getSearch().add(new SearchCriteria("status", "=", Status.ACTIVE, TypeSearch.STRING));
        Page<AudioResponse> response = audioService.getList(pageable).map(AudioResponse::minResponse);
        return new SuccessDataIterable<>(response);
    }

    @GetMapping("/{id}")
    public SuccessfulResponse<AudioResponse> getById(@PathVariable Long id) {
        AudioResponse response = AudioResponse.minResponse(audioService.changeViews(id));
        return new SuccessfulResponse<>(response);
    }
}
