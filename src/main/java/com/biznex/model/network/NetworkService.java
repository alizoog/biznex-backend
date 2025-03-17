package com.biznex.model.network;

import com.biznex.common.constant.FileType;
import com.biznex.common.constant.Status;
import com.biznex.common.exception.ApiException;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.request.PageableRequestUtil;
import com.biznex.common.request.SearchSpecification;
import com.biznex.common.response.status.NetworkStatus;
import com.biznex.model.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NetworkService {

    private final NetworkRepository networkRepository;
    private final FileService fileService;

    public Optional<Network> findById(Integer id) {
        if (id == null) return Optional.empty();
        return networkRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Network> getList(Status status) {
        return networkRepository.findAllByStatus(status);
    }

    @Transactional(readOnly = true)
    public Page<Network> getList(PageableRequest pageable) {
        return networkRepository.findAll(new SearchSpecification<>(pageable.getSearch()), PageableRequestUtil.toPageable(pageable));
    }

    public Network getById(Integer id) {
        return findById(id).orElseThrow(() -> new ApiException(NetworkStatus.NOT_FOUND));
    }

    public Network save(Network network, NetworkPayload payload) {
        network.setUrl(payload.getUrl());
        network.setPhoto(fileService.changeType(payload.getPhotoId(), FileType.ACTIVE));
        network.setStatus(payload.getStatus());
        return networkRepository.saveAndFlush(network);
    }

    public Network create(NetworkPayload payload) {
        Network network = new Network();
        return save(network, payload);
    }

    public Network update(Integer id, NetworkPayload payload) {
        Network network = getById(id);
        return save(network, payload);
    }

    public void delete(Integer id) {
        Network network = getById(id);
        networkRepository.delete(network);
    }
}
