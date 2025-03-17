package com.biznex.controller.client;

import com.biznex.common.constant.Status;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.response.SuccessDataIterable;
import com.biznex.common.response.SuccessfulResponse;
import com.biznex.model.career.CareerResponse;
import com.biznex.model.career.CareerService;
import com.biznex.utils.AppUtils;
import com.biznex.utils.WebConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(WebConstants.CLIENT_URL + "/career")
@RequiredArgsConstructor
public class CareerClientController {
    private final CareerService careerService;

    @PostMapping("/pageable")
    public SuccessDataIterable<CareerResponse> getList(@Valid @RequestBody PageableRequest pageable) {
        AppUtils.addSearchCriteriaStatus(pageable, Status.ACTIVE);
        Page<CareerResponse> response = careerService.getList(pageable).map(CareerResponse::minResponse);
        return new SuccessDataIterable<>(response);
    }

    @GetMapping("/{id}")
    public SuccessfulResponse<CareerResponse> getById(@PathVariable Long id) {
        CareerResponse response = CareerResponse.minResponse(careerService.getById(id));
        return new SuccessfulResponse<>(response);
    }

}
