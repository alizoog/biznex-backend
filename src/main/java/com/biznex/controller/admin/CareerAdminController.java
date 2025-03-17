package com.biznex.controller.admin;

import com.biznex.common.exception.ApiException;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.response.SuccessDataIterable;
import com.biznex.common.response.SuccessfulResponse;
import com.biznex.common.response.status.CareerStatus;
import com.biznex.model.career.CareerPayload;
import com.biznex.model.career.CareerResponse;
import com.biznex.model.career.CareerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/career")
public class CareerAdminController {
    private final CareerService careerService;

    @PreAuthorize(value = "hasAuthority('VIEW_CAREERS')")
    @PostMapping("/pageable")
    public SuccessDataIterable<CareerResponse> getList(@Valid @RequestBody PageableRequest pageable) {
        Page<CareerResponse> response = careerService.getList(pageable).map(CareerResponse::new);
        return new SuccessDataIterable<>(response);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_CAREER')")
    @GetMapping("/{id}")
    public SuccessfulResponse<CareerResponse> getById(@PathVariable Long id) {
        CareerResponse response = new CareerResponse(careerService.getById(id));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('CREATE_CAREER')")
    @PostMapping
    public SuccessfulResponse<CareerResponse> create(@RequestBody CareerPayload payload) {
        CareerResponse response = new CareerResponse(careerService.create(payload));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('UPDATE_CAREER')")
    @PutMapping("/{id}")
    public SuccessfulResponse<CareerResponse> update(@PathVariable Long id, @RequestBody CareerPayload payload) {
        CareerResponse response = new CareerResponse(careerService.update(id, payload));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_CAREER')")
    @DeleteMapping("/{id}")
    public SuccessfulResponse<?> delete(@PathVariable Long id) {
        careerService.delete(id);
        throw new ApiException(CareerStatus.DELETED);
    }
}
