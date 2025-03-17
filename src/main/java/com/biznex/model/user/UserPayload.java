package com.biznex.model.user;

import com.biznex.common.constant.Event.OnCreate;
import com.biznex.common.constant.Event.OnUpdate;
import com.biznex.common.constant.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.plaf.synth.Region;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPayload {

    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    private String firstName;
    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    private String lastName;
    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    private String username;
    @NotBlank(groups = OnCreate.class)
    private String password;
    @NotNull
    private Long photoId;
    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    private Long roleId;
    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    private Status status;
    @NotNull
    private List<Region> regions;
}
