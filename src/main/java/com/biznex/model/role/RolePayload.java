package com.biznex.model.role;

import com.biznex.common.constant.Permission;
import com.biznex.common.constant.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolePayload {

    @NotBlank
    private String name;
    @NotBlank
    @Size(max = 250)
    private String description;
    private List<Permission> permissions;
    @NotNull
    private Status status;
}
