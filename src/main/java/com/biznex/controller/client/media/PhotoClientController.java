package com.biznex.controller.client.media;

import com.biznex.common.constant.Status;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.request.SearchCriteria;
import com.biznex.common.request.TypeSearch;
import com.biznex.common.response.SuccessDataIterable;
import com.biznex.common.response.SuccessfulResponse;
import com.biznex.model.media.photo.PhotoResponse;
import com.biznex.model.media.photo.PhotoService;
import com.biznex.utils.WebConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(WebConstants.CLIENT_URL + "/photos")
@RequiredArgsConstructor
public class PhotoClientController {

    private final PhotoService photoService;

    @PostMapping("/pageable")
    public SuccessDataIterable<PhotoResponse> getList(@Valid @RequestBody PageableRequest pageable) {
        pageable.getSearch().add(new SearchCriteria("status", "=", Status.ACTIVE, TypeSearch.STRING));
        Page<PhotoResponse> response = photoService.getList(pageable).map(PhotoResponse::minResponse);
        return new SuccessDataIterable<>(response);
    }

    @GetMapping("/{id}")
    public SuccessfulResponse<PhotoResponse> getById(@PathVariable Long id) {
        PhotoResponse response = PhotoResponse.minResponse(photoService.changeViews(id));
        return new SuccessfulResponse<>(response);
    }
}
