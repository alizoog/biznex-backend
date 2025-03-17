package com.biznex.model.banner;

import com.biznex.common.constant.FileType;
import com.biznex.common.constant.Status;
import com.biznex.common.exception.ApiException;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.request.PageableRequestUtil;
import com.biznex.common.request.SearchSpecification;
import com.biznex.common.response.status.BannerStatus;
import com.biznex.model.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;
    private final FileService fileService;

    public Optional<Banner> findById(Long id) {
        if (id == null) return Optional.empty();
        return bannerRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Banner> getList(Status status) {
        return bannerRepository.findAllByStatus(status);
    }

    @Transactional(readOnly = true)
    public Page<Banner> getList(PageableRequest pageable) {
        return bannerRepository.findAll(new SearchSpecification<>(pageable.getSearch()), PageableRequestUtil.toPageable(pageable));
    }

    public Banner getById(Long id) {
        return findById(id).orElseThrow(() -> new ApiException(BannerStatus.NOT_FOUND));
    }

    public Banner save(Banner banner, BannerPayload payload) {
        banner.setPhoto(fileService.changeType(payload.getPhotoId(), FileType.ACTIVE));
        banner.setStatus(payload.getStatus());
        return bannerRepository.saveAndFlush(banner);
    }

    public Banner create(BannerPayload payload) {
        Banner banner = new Banner();
        return save(banner, payload);
    }

    public Banner update(Long id, BannerPayload payload) {
        Banner banner = getById(id);
        return save(banner, payload);
    }

    public void delete(Long id) {
        Banner banner = getById(id);
        bannerRepository.delete(banner);
    }
}
