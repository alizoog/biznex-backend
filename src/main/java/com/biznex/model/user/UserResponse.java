package com.biznex.model.user;

import com.biznex.common.constant.Permission;
import com.biznex.common.response.TechnicalFieldsResponse;
import com.biznex.model.file.FileResponse;
import com.biznex.model.role.RoleResponse;
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
public class UserResponse extends TechnicalFieldsResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String username;
    private FileResponse photo;
    private RoleResponse role;
    private List<Permission> permissions;

    public UserResponse(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.fullName = getFullName(user);
        this.username = user.getUsername();
        if (user.getPhoto() != null) {
            this.photo = FileResponse.minResponse(user.getPhoto());
        }
        this.role = RoleResponse.minResponse(user.getRole());
        this.permissions = user.getRole().getPermissions();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.status = user.getStatus();
    }

    public static UserResponse minResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFullName(getFullName(user));
        return userResponse;
    }

    public static String getFullName(User user) {
        return String.format("%s %s", user.getFirstName(), user.getLastName());
    }
}
