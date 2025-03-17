package com.biznex.controller.admin;

import com.biznex.common.exception.ApiException;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.response.SuccessDataIterable;
import com.biznex.common.response.SuccessfulResponse;
import com.biznex.common.response.status.BannerStatus;
import com.biznex.model.banner.BannerPayload;
import com.biznex.model.banner.BannerResponse;
import com.biznex.model.banner.BannerService;
import com.biznex.utils.WebConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(WebConstants.ADMIN_URL + "/banners")
@RequiredArgsConstructor
public class BannerAdminController {

    private final BannerService bannerService;

    @PreAuthorize(value = "hasAuthority('VIEW_BANNERS')")
    @PostMapping("/pageable")
    public SuccessDataIterable<BannerResponse> getList(@Valid @RequestBody PageableRequest pageable) {
        Page<BannerResponse> response = bannerService.getList(pageable).map(BannerResponse::new);
        return new SuccessDataIterable<>(response);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_BANNER')")
    @GetMapping("/{id}")
    public SuccessfulResponse<BannerResponse> getById(@PathVariable Long id) {
        BannerResponse response = new BannerResponse(bannerService.getById(id));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('CREATE_BANNER')")
    @PostMapping
    public SuccessfulResponse<BannerResponse> create(@Valid @RequestBody BannerPayload payload) {
        BannerResponse response = new BannerResponse(bannerService.create(payload));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('UPDATE_BANNER')")
    @PutMapping("/{id}")
    public SuccessfulResponse<BannerResponse> update(@PathVariable Long id, @Valid @RequestBody BannerPayload payload) {
        BannerResponse response = new BannerResponse(bannerService.update(id, payload));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_BANNER')")
    @DeleteMapping("/{id}")
    public SuccessfulResponse<?> delete(@PathVariable Long id) {
        bannerService.delete(id);
        throw new ApiException(BannerStatus.DELETED);
    }
}
