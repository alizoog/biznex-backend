package com.biznex.model.role;

import com.biznex.common.constant.Status;
import com.biznex.common.exception.ApiException;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.request.PageableRequestUtil;
import com.biznex.common.request.SearchSpecification;
import com.biznex.common.response.status.RoleStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Optional<Role> findById(Long id) {
        if (id == null) return Optional.empty();
        return roleRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Role> getList(Status status) {
        return roleRepository.findAllByStatus(status);
    }

    @Transactional(readOnly = true)
    public Page<Role> getList(PageableRequest pageable) {
        return roleRepository.findAll(new SearchSpecification<>(pageable.getSearch()), PageableRequestUtil.toPageable(pageable));
    }

    public Role getById(Long id) {
        return findById(id).orElseThrow(() -> new ApiException(RoleStatus.NOT_FOUND));
    }

    public Role save(Role role, RolePayload payload) {
        role.setName(payload.getName());
        role.setDescription(payload.getDescription());
        role.setPermissions(payload.getPermissions());
        role.setStatus(payload.getStatus());
        return roleRepository.saveAndFlush(role);
    }

    public Role create(RolePayload payload) {
        if (roleRepository.existsByName(payload.getName()))
            throw new ApiException(RoleStatus.NAME_ALREADY_EXISTS);
        Role role = new Role();
        return save(role, payload);
    }

    public Role update(Long id, RolePayload payload) {
        if (roleRepository.existsByIdNotAndName(id, payload.getName()))
            throw new ApiException(RoleStatus.NAME_ALREADY_EXISTS);
        Role role = getById(id);
        return save(role, payload);
    }

    @Transactional
    public void delete(Long id) {
        Role role = getById(id);
        role.setStatus(Status.DELETED);
        roleRepository.saveAndFlush(role);
    }
}
