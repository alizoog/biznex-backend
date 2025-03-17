package com.biznex.model.role;

import com.biznex.common.constant.Name;
import com.biznex.common.constant.Permission;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PermissionResponse {
    private String name;
    private Name title;

    public PermissionResponse(Permission permission) {
        this.name = permission.name();
        this.title = new Name(permission.getTitleUz(), permission.getTitleEn(), permission.getTitleRu());
    }

    public static List<PermissionResponse> getPermissions(List<Permission> permissions) {
        return permissions.stream().map(PermissionResponse::new).toList();
    }
}
