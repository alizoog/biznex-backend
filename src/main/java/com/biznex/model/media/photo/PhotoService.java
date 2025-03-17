package com.biznex.model.media.photo;

import com.biznex.common.constant.FileType;
import com.biznex.common.exception.ApiException;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.request.PageableRequestUtil;
import com.biznex.common.request.SearchSpecification;
import com.biznex.common.response.status.PhotoStatus;
import com.biznex.model.file.FileService;
import com.biznex.model.mediacategory.MediaCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final MediaCategoryService mediaCategoryService;
    private final FileService fileService;

    public Optional<Photo> findById(Long id) {
        if (id == null) return Optional.empty();
        return photoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<Photo> getList(PageableRequest pageable) {
        return photoRepository.findAll(new SearchSpecification<>(pageable.getSearch()), PageableRequestUtil.toPageable(pageable));
    }

    public Photo getById(Long id) {
        return findById(id).orElseThrow(() -> new ApiException(PhotoStatus.NOT_FOUND));
    }

    public Photo changeViews(Long id) {
        Photo photo = getById(id);
        photo.setViews(photo.getViews() + 1);
        return photoRepository.saveAndFlush(photo);
    }

    public Photo save(Photo photo, PhotoPayload payload) {
        photo.setTitle(payload.getTitle());
        photo.setPhoto(fileService.changeType(payload.getPhotoId(), FileType.ACTIVE));
        photo.setCategory(mediaCategoryService.getById(payload.getCategoryId()));
        photo.setStatus(payload.getStatus());
        return photoRepository.saveAndFlush(photo);
    }

    public Photo create(PhotoPayload payload) {
        Photo photo = new Photo();
        return save(photo, payload);
    }

    public Photo update(Long id, PhotoPayload payload) {
        Photo photo = getById(id);
        return save(photo, payload);
    }

    public void delete(Long id) {
        Photo photo = getById(id);
        photoRepository.delete(photo);
    }
}
