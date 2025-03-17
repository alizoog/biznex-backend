package com.biznex.controller.admin.media;

import com.biznex.common.exception.ApiException;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.response.SuccessDataIterable;
import com.biznex.common.response.SuccessfulResponse;
import com.biznex.common.response.status.AudioStatus;
import com.biznex.model.media.audio.AudioPayload;
import com.biznex.model.media.audio.AudioResponse;
import com.biznex.model.media.audio.AudioService;
import com.biznex.utils.WebConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(WebConstants.ADMIN_URL + "/audios")
@RequiredArgsConstructor
public class AudioAdminController {

    private final AudioService audioService;

    @PreAuthorize(value = "hasAuthority('VIEW_AUDIOS')")
    @PostMapping("/pageable")
    public SuccessDataIterable<AudioResponse> getList(@Valid @RequestBody PageableRequest pageable) {
        Page<AudioResponse> response = audioService.getList(pageable).map(AudioResponse::new);
        return new SuccessDataIterable<>(response);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_AUDIO')")
    @GetMapping("/{id}")
    public SuccessfulResponse<AudioResponse> getById(@PathVariable Long id) {
        AudioResponse response = new AudioResponse(audioService.getById(id));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('CREATE_AUDIO')")
    @PostMapping
    public SuccessfulResponse<AudioResponse> create(@Valid @RequestBody AudioPayload payload) {
        AudioResponse response = new AudioResponse(audioService.create(payload));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('UPDATE_AUDIO')")
    @PutMapping("/{id}")
    public SuccessfulResponse<AudioResponse> update(@PathVariable Long id, @Valid @RequestBody AudioPayload payload) {
        AudioResponse response = new AudioResponse(audioService.update(id, payload));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_AUDIO')")
    @DeleteMapping("/{id}")
    public SuccessfulResponse<?> delete(@PathVariable Long id) {
        audioService.delete(id);
        throw new ApiException(AudioStatus.DELETED);
    }
}
