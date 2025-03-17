package com.biznex.controller.admin;

import com.biznex.common.exception.ApiException;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.response.SuccessDataIterable;
import com.biznex.common.response.SuccessfulResponse;
import com.biznex.common.response.status.NetworkStatus;
import com.biznex.model.network.NetworkPayload;
import com.biznex.model.network.NetworkResponse;
import com.biznex.model.network.NetworkService;
import com.biznex.utils.WebConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(WebConstants.ADMIN_URL + "/networks")
@RequiredArgsConstructor
public class NetworkAdminController {

    private final NetworkService networkService;

    @PreAuthorize(value = "hasAuthority('VIEW_NETWORKS')")
    @PostMapping("/pageable")
    public SuccessDataIterable<NetworkResponse> getList(@Valid @RequestBody PageableRequest pageable) {
        Page<NetworkResponse> response = networkService.getList(pageable).map(NetworkResponse::new);
        return new SuccessDataIterable<>(response);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_NETWORK')")
    @GetMapping("/{id}")
    public SuccessfulResponse<NetworkResponse> getById(@PathVariable Integer id) {
        NetworkResponse response = new NetworkResponse(networkService.getById(id));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('CREATE_NETWORK')")
    @PostMapping
    public SuccessfulResponse<NetworkResponse> create(@Valid @RequestBody NetworkPayload payload) {
        NetworkResponse response = new NetworkResponse(networkService.create(payload));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('UPDATE_NETWORK')")
    @PutMapping("/{id}")
    public SuccessfulResponse<NetworkResponse> update(@PathVariable Integer id, @Valid @RequestBody NetworkPayload payload) {
        NetworkResponse response = new NetworkResponse(networkService.update(id, payload));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_NETWORK')")
    @DeleteMapping("/{id}")
    public SuccessfulResponse<?> delete(@PathVariable Integer id) {
        networkService.delete(id);
        throw new ApiException(NetworkStatus.DELETED);
    }
}
