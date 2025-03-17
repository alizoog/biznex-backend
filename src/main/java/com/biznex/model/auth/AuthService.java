package com.biznex.model.auth;

import com.biznex.common.exception.ApiException;
import com.biznex.common.response.status.UserStatus;
import com.biznex.model.user.User;
import com.biznex.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    public TokenResponse login(LoginPayload payload) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    payload.getUsername(),
                                    payload.getPassword()
                            )
                    );
            User user = (User) authenticate.getPrincipal();
            return new TokenResponse(
                    jwtProvider.generateAccessToken(payload.getUsername(), user.getRole()),
                    jwtProvider.generateRefreshToken(payload.getUsername(), user.getRole()));

        } catch (BadCredentialsException e) {
            throw new ApiException(UserStatus.USERNAME_OR_PASSWORD_INCORRECT);
        }
    }
}
