package com.biznex.model.media.video;

import com.biznex.common.constant.FileType;
import com.biznex.common.constant.Status;
import com.biznex.common.exception.ApiException;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.request.PageableRequestUtil;
import com.biznex.common.request.SearchSpecification;
import com.biznex.common.response.status.VideoStatus;
import com.biznex.model.file.FileService;
import com.biznex.model.mediacategory.MediaCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final MediaCategoryService mediaCategoryService;
    private final FileService fileService;

    public Optional<Video> findById(Long id) {
        if (id == null) return Optional.empty();
        return videoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<Video> getList(PageableRequest pageable) {
        return videoRepository.findAll(new SearchSpecification<>(pageable.getSearch()), PageableRequestUtil.toPageable(pageable));
    }

    public Video getById(Long id) {
        return findById(id).orElseThrow(() -> new ApiException(VideoStatus.NOT_FOUND));
    }

    public Video getLastByStatus(Status status) {
        return videoRepository.findTopByStatusOrderByCreatedAtDesc(status)
                .map(video -> {
                    video.setViews(video.getViews() + 1);
                    return videoRepository.saveAndFlush(video);
                })
                .orElse(null);
    }

    public Video changeViews(Long id) {
        Video video = getById(id);
        video.setViews(video.getViews() + 1);
        return videoRepository.saveAndFlush(video);
    }

    public Video save(Video video, VideoPayload payload) {
        video.setTitle(payload.getTitle());
        video.setUrl(payload.getUrl());
        if (payload.getPhotoId() != null) {
            video.setPhoto(fileService.changeType(payload.getPhotoId(), FileType.ACTIVE));
        }
        video.setCategory(mediaCategoryService.getById(payload.getCategoryId()));
        video.setStatus(payload.getStatus());
        return videoRepository.saveAndFlush(video);
    }

    public Video create(VideoPayload payload) {
        Video video = new Video();
        return save(video, payload);
    }

    public Video update(Long id, VideoPayload payload) {
        Video video = getById(id);
        return save(video, payload);
    }

    public void delete(Long id) {
        Video video = getById(id);
        videoRepository.delete(video);
    }
}
