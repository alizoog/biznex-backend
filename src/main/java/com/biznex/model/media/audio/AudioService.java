package com.biznex.model.media.audio;

import com.biznex.common.constant.FileType;
import com.biznex.common.exception.ApiException;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.request.PageableRequestUtil;
import com.biznex.common.request.SearchSpecification;
import com.biznex.common.response.status.AudioStatus;
import com.biznex.model.file.FileService;
import com.biznex.model.mediacategory.MediaCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AudioService {

    private final AudioRepository audioRepository;
    private final MediaCategoryService mediaCategoryService;
    private final FileService fileService;

    public Optional<Audio> findById(Long id) {
        if (id == null) return Optional.empty();
        return audioRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<Audio> getList(PageableRequest pageable) {
        return audioRepository.findAll(new SearchSpecification<>(pageable.getSearch()), PageableRequestUtil.toPageable(pageable));
    }

    public Audio getById(Long id) {
        return findById(id).orElseThrow(() -> new ApiException(AudioStatus.NOT_FOUND));
    }

    public Audio changeViews(Long id) {
        Audio audio = getById(id);
        audio.setViews(audio.getViews() + 1);
        return audioRepository.saveAndFlush(audio);
    }

    public Audio save(Audio audio, AudioPayload payload) {
        audio.setTitle(payload.getTitle());
        if (payload.getPhotoId() != null) {
            audio.setPhoto(fileService.changeType(payload.getPhotoId(), FileType.ACTIVE));
        }
        audio.setAudio(fileService.changeType(payload.getAudioId(), FileType.ACTIVE));
        audio.setCategory(mediaCategoryService.getById(payload.getCategoryId()));
        audio.setStatus(payload.getStatus());
        return audioRepository.saveAndFlush(audio);
    }

    public Audio create(AudioPayload payload) {
        Audio audio = new Audio();
        return save(audio, payload);
    }

    public Audio update(Long id, AudioPayload payload) {
        Audio audio = getById(id);
        return save(audio, payload);
    }

    public void delete(Long id) {
        Audio audio = getById(id);
        audioRepository.delete(audio);
    }
}
