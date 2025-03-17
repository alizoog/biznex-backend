package com.biznex.model.career;

import com.biznex.common.constant.Status;
import com.biznex.common.exception.ApiException;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.request.PageableRequestUtil;
import com.biznex.common.request.SearchSpecification;
import com.biznex.common.response.status.CareerStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CareerService {

    private final CareerRepository careerRepository;

    public Optional<Career> findById(Long id) {
        if (id == null) return Optional.empty();
        return careerRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<Career> getList(PageableRequest pageable) {
        return careerRepository.findAll(
                new SearchSpecification<>(pageable.getSearch()),
                PageableRequestUtil.toPageable(pageable)
        );
    }

    public Career getById(Long id) {
        return findById(id).orElseThrow(() -> new ApiException(CareerStatus.NOT_FOUND));
    }

    public Career save(Career career, CareerPayload payload) {
        career.setName(payload.getName());
        career.setDescription(payload.getDescription());
        career.setJobType(payload.getJobType());
        career.setSalaryRange(payload.getSalaryRange());
        career.setStatus(payload.getStatus());
        return careerRepository.saveAndFlush(career);
    }

    public Career create(CareerPayload payload) {
        Career career = new Career();
        return save(career, payload);
    }

    public Career update(Long id, CareerPayload payload) {
        Career career = getById(id);
        return save(career, payload);
    }

    public void delete(Long id) {
        Career career = getById(id);
        career.setStatus(Status.DELETED);
        careerRepository.saveAndFlush(career);
    }
}
