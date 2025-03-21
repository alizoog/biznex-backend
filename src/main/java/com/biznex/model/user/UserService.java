package com.biznex.model.user;

import com.biznex.common.constant.FileType;
import com.biznex.common.constant.Status;
import com.biznex.common.exception.ApiException;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.request.PageableRequestUtil;
import com.biznex.common.request.SearchSpecification;
import com.biznex.common.response.status.UserStatus;
import com.biznex.model.file.FileService;
import com.biznex.model.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final FileService fileService;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findById(Long id) {
        if (id == null) return Optional.empty();
        return userRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<User> getList(PageableRequest pageable) {
        return userRepository.findAll(
                new SearchSpecification<>(pageable.getSearch()),
                PageableRequestUtil.toPageable(pageable)
        );
    }

    public User getById(Long id) {
        return findById(id).orElseThrow(() -> new ApiException(UserStatus.NOT_FOUND));
    }

    public User save(User user, UserPayload payload) {
        user.setFirstName(payload.getFirstName());
        user.setLastName(payload.getLastName());
        user.setUsername(payload.getUsername());
        if (StringUtils.hasText(payload.getPassword())) {
            user.setPassword(passwordEncoder.encode(payload.getPassword()));
        }
        if (payload.getPhotoId() != null) {
            user.setPhoto(fileService.changeType(payload.getPhotoId(), FileType.ACTIVE));
        }
        user.setRole(roleService.getById(payload.getRoleId()));
        user.setStatus(payload.getStatus());
        return userRepository.saveAndFlush(user);
    }

    public User create(UserPayload payload) {
        if (userRepository.existsByUsername(payload.getUsername()))
            throw new ApiException(UserStatus.USERNAME_ALREADY_EXIST);
        User user = new User();
        return save(user, payload);
    }

    public User update(Long id, UserPayload payload) {
        if (userRepository.existsByIdNotAndUsername(id, payload.getUsername()))
            throw new ApiException(UserStatus.USERNAME_ALREADY_EXIST);
        User user = getById(id);
        return save(user, payload);
    }

    public void delete(Long id) {
        User user = getById(id);
        user.setStatus(Status.DELETED);
        userRepository.saveAndFlush(user);
    }
}
