package com.biznex.model.mediacategory;

import com.biznex.common.constant.MediaType;
import com.biznex.common.constant.Status;
import com.biznex.common.exception.ApiException;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.request.PageableRequestUtil;
import com.biznex.common.request.SearchSpecification;
import com.biznex.common.response.status.MediaCategoryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MediaCategoryService {

    private final MediaCategoryRepository mediaCategoryRepository;

    public Optional<MediaCategory> findById(Integer id) {
        if (id == null) return Optional.empty();
        return mediaCategoryRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<MediaCategory> getList(MediaType type, Status status) {
        return mediaCategoryRepository.findAllByTypeAndStatus(type, status);
    }

    @Transactional(readOnly = true)
    public Page<MediaCategory> getList(PageableRequest pageable) {
        return mediaCategoryRepository.findAll(new SearchSpecification<>(pageable.getSearch()), PageableRequestUtil.toPageable(pageable));
    }

    public MediaCategory getById(Integer id) {
        return findById(id).orElseThrow(() -> new ApiException(MediaCategoryStatus.NOT_FOUND));
    }

    public MediaCategory save(MediaCategory mediaCategory, MediaCategoryPayload payload) {
        mediaCategory.setName(payload.getName());
        mediaCategory.setType(payload.getType());
        mediaCategory.setStatus(payload.getStatus());
        return mediaCategoryRepository.saveAndFlush(mediaCategory);
    }

    public MediaCategory create(MediaCategoryPayload payload) {
        MediaCategory mediaCategory = new MediaCategory();
        return save(mediaCategory, payload);
    }

    public MediaCategory update(Integer id, MediaCategoryPayload payload) {
        MediaCategory mediaCategory = getById(id);
        return save(mediaCategory, payload);
    }

    public void delete(Integer id) {
        MediaCategory mediaCategory = getById(id);
        mediaCategoryRepository.delete(mediaCategory);
    }
}
