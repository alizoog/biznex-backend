package com.biznex.controller.admin;

import com.biznex.common.exception.ApiException;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.response.SuccessDataIterable;
import com.biznex.common.response.SuccessfulResponse;
import com.biznex.common.response.status.TranslationStatus;
import com.biznex.model.translation.TranslationPayload;
import com.biznex.model.translation.TranslationResponse;
import com.biznex.model.translation.TranslationService;
import com.biznex.utils.WebConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(WebConstants.ADMIN_URL + "/translations")
@RequiredArgsConstructor
public class TranslationAdminController {

    private final TranslationService translationService;

    @PreAuthorize(value = "hasAuthority('VIEW_TRANSLATIONS')")
    @PostMapping("/pageable")
    public SuccessDataIterable<TranslationResponse> getList(@Valid @RequestBody PageableRequest pageable) {
        Page<TranslationResponse> response = translationService.getList(pageable).map(TranslationResponse::new);
        return new SuccessDataIterable<>(response);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_TRANSLATION')")
    @GetMapping("/{id}")
    public SuccessfulResponse<TranslationResponse> getById(@PathVariable Long id) {
        TranslationResponse response = new TranslationResponse(translationService.getById(id));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('CREATE_TRANSLATION')")
    @PostMapping
    public SuccessfulResponse<TranslationResponse> create(@Valid @RequestBody TranslationPayload payload) {
        TranslationResponse response = new TranslationResponse(translationService.create(payload));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('UPDATE_TRANSLATION')")
    @PutMapping("/{id}")
    public SuccessfulResponse<TranslationResponse> update(@PathVariable Long id, @Valid @RequestBody TranslationPayload payload) {
        TranslationResponse response = new TranslationResponse(translationService.update(id, payload));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_TRANSLATION')")
    @DeleteMapping("/{id}")
    public SuccessfulResponse<?> delete(@PathVariable Long id) {
        translationService.delete(id);
        throw new ApiException(TranslationStatus.DELETED);
    }
}
