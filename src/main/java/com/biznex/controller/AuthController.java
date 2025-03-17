package com.biznex.controller;

import com.biznex.common.response.SuccessfulResponse;
import com.biznex.model.auth.AuthService;
import com.biznex.model.auth.LoginPayload;
import com.biznex.model.auth.TokenResponse;
import com.biznex.model.user.UserResponse;
import com.biznex.security.JwtProvider;
import com.biznex.utils.CurrentUser;
import com.biznex.utils.WebConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(WebConstants.BASE_URL_WITH_VERSION + "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtProvider jwtProvider;

    @PostMapping("/login")
    public SuccessfulResponse<TokenResponse> login(@Valid @RequestBody LoginPayload payload) {
        TokenResponse response = authService.login(payload);
        return new SuccessfulResponse<>(response);
    }

    @PostMapping("/refresh-token")
    public SuccessfulResponse<TokenResponse> refreshToken(@RequestParam String refreshToken) {
        String username = jwtProvider.extractUsername(refreshToken);
        String newAccessToken = jwtProvider.generateAccessToken(username, CurrentUser.getCurrentUser().getRole());
        return new SuccessfulResponse<>(new TokenResponse(newAccessToken, refreshToken));
    }

    @GetMapping("/me")
    public SuccessfulResponse<UserResponse> getMe() {
        UserResponse response = new UserResponse(CurrentUser.getCurrentUser());
        return new SuccessfulResponse<>(response);
    }
}
