package com.biznex.controller.admin;

import com.biznex.common.constant.Event.OnCreate;
import com.biznex.common.constant.Event.OnUpdate;
import com.biznex.common.exception.ApiException;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.response.SuccessDataIterable;
import com.biznex.common.response.SuccessfulResponse;
import com.biznex.common.response.status.UserStatus;
import com.biznex.model.user.UserPayload;
import com.biznex.model.user.UserResponse;
import com.biznex.model.user.UserService;
import com.biznex.utils.WebConstants;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(WebConstants.ADMIN_URL + "/users")
public class UserAdminController {

    private final UserService userService;

    public UserAdminController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize(value = "hasAuthority('VIEW_USERS')")
    @PostMapping("/pageable")
    public SuccessDataIterable<UserResponse> getList(@Valid @RequestBody PageableRequest pageable) {
        Page<UserResponse> response = userService.getList(pageable).map(UserResponse::new);
        return new SuccessDataIterable<>(response);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_USER')")
    @GetMapping("/{id}")
    public SuccessfulResponse<UserResponse> getById(@PathVariable Long id) {
        UserResponse response = new UserResponse(userService.getById(id));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('CREATE_USER')")
    @PostMapping
    public SuccessfulResponse<UserResponse> create(@Validated(value = OnCreate.class) @RequestBody UserPayload payload) {
        UserResponse response = new UserResponse(userService.create(payload));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('UPDATE_USER')")
    @PutMapping("/{id}")
    public SuccessfulResponse<UserResponse> update(@PathVariable Long id, @Validated(value = OnUpdate.class) @RequestBody UserPayload payload) {
        UserResponse response = new UserResponse(userService.update(id, payload));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_USER')")
    @DeleteMapping("/{id}")
    public SuccessfulResponse<?> delete(@PathVariable Long id) {
        userService.delete(id);
        throw new ApiException(UserStatus.DELETED);
    }
}
