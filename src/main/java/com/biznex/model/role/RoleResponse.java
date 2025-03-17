package com.biznex.model.role;

import com.biznex.common.response.TechnicalFieldsResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleResponse extends TechnicalFieldsResponse {
    private Long id;
    private String name;
    private String description;
    private List<PermissionResponse> permissions;

    public RoleResponse(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.description = role.getDescription();
        this.permissions = PermissionResponse.getPermissions(role.getPermissions());
        this.createdAt = role.getCreatedAt();
        this.updatedAt = role.getUpdatedAt();
        this.status = role.getStatus();
    }

    public static RoleResponse minResponse(Role role) {
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setId(role.getId());
        roleResponse.setName(role.getName());
        return roleResponse;
    }
}
