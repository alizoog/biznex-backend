package com.biznex.controller.client;

import com.biznex.common.constant.Status;
import com.biznex.model.banner.BannerResponse;
import com.biznex.model.banner.BannerService;
import com.biznex.utils.WebConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(WebConstants.CLIENT_URL + "/banners")
@RequiredArgsConstructor
public class BannerClientController {

    private final BannerService bannerService;

    @GetMapping("/list")
    public List<BannerResponse> getList() {
        return bannerService.getList(Status.ACTIVE)
                .stream()
                .map(BannerResponse::minResponse)
                .toList();
    }
}
