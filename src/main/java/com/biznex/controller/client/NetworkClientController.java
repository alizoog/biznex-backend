package com.biznex.controller.client;

import com.biznex.common.constant.Status;
import com.biznex.model.network.NetworkResponse;
import com.biznex.model.network.NetworkService;
import com.biznex.utils.WebConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(WebConstants.CLIENT_URL + "/networks")
@RequiredArgsConstructor
public class NetworkClientController {

    private final NetworkService networkService;

    @GetMapping("/list")
    public List<NetworkResponse> getList() {
        return networkService.getList(Status.ACTIVE)
                .stream()
                .map(NetworkResponse::minResponse)
                .toList();
    }
}
