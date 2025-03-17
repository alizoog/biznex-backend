package com.biznex.controller.admin;

import com.biznex.common.constant.Permission;
import com.biznex.common.constant.Status;
import com.biznex.model.role.PermissionResponse;
import com.biznex.model.role.RoleResponse;
import com.biznex.model.role.RoleService;
import com.biznex.utils.WebConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(WebConstants.ADMIN_URL + "/references")
@RequiredArgsConstructor
public class ReferenceAdminController {

    private final RoleService roleService;

    @GetMapping("/roles")
    public List<RoleResponse> getRoles() {
        return roleService.getList(Status.ACTIVE)
                .stream()
                .map(RoleResponse::minResponse)
                .toList();
    }

    @GetMapping("/permissions")
    public List<PermissionResponse> getPermissions() {
        return Arrays.stream(Permission.values())
                .map(PermissionResponse::new)
                .toList();
    }
}
