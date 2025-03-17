package com.biznex.controller.admin;

import com.biznex.common.exception.ApiException;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.response.SuccessDataIterable;
import com.biznex.common.response.SuccessfulResponse;
import com.biznex.common.response.status.RoleStatus;
import com.biznex.model.role.RolePayload;
import com.biznex.model.role.RoleResponse;
import com.biznex.model.role.RoleService;
import com.biznex.utils.WebConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(WebConstants.ADMIN_URL + "/roles")
@RequiredArgsConstructor
public class RoleAdminController {

    private final RoleService roleService;

    @PreAuthorize(value = "hasAuthority('VIEW_ROLES')")
    @PostMapping("/pageable")
    public SuccessDataIterable<RoleResponse> getList(@Valid @RequestBody PageableRequest pageable) {
        Page<RoleResponse> response = roleService.getList(pageable).map(RoleResponse::new);
        return new SuccessDataIterable<>(response);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_ROLE')")
    @GetMapping("/{id}")
    public SuccessfulResponse<RoleResponse> getById(@PathVariable Long id) {
        RoleResponse response = new RoleResponse(roleService.getById(id));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('CREATE_ROLE')")
    @PostMapping
    public SuccessfulResponse<RoleResponse> create(@Valid @RequestBody RolePayload payload) {
        RoleResponse response = new RoleResponse(roleService.create(payload));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('UPDATE_ROLE')")
    @PutMapping("/{id}")
    public SuccessfulResponse<RoleResponse> update(@PathVariable Long id, @Valid @RequestBody RolePayload payload) {
        RoleResponse response = new RoleResponse(roleService.update(id, payload));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_ROLE')")
    @DeleteMapping("/{id}")
    public SuccessfulResponse<?> delete(@PathVariable Long id) {
        roleService.delete(id);
        throw new ApiException(RoleStatus.DELETED);
    }
}
