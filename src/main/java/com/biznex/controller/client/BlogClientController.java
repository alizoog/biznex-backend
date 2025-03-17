package com.biznex.controller.client;

import com.biznex.common.constant.Status;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.request.SearchCriteria;
import com.biznex.common.request.TypeSearch;
import com.biznex.common.response.SuccessDataIterable;
import com.biznex.common.response.SuccessfulResponse;
import com.biznex.model.blog.BlogResponse;
import com.biznex.model.blog.BlogService;
import com.biznex.utils.WebConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(WebConstants.CLIENT_URL + "/blogs")
@RequiredArgsConstructor
public class BlogClientController {

    private final BlogService blogService;

    @PostMapping("/pageable")
    public SuccessDataIterable<BlogResponse> getList(@Valid @RequestBody PageableRequest pageable) {
        pageable.getSearch().add(new SearchCriteria("status", "=", Status.ACTIVE, TypeSearch.STRING));
        Page<BlogResponse> response = blogService.getList(pageable).map(BlogResponse::minResponse);
        return new SuccessDataIterable<>(response);
    }

    @GetMapping("/{id}")
    public SuccessfulResponse<BlogResponse> getById(@PathVariable Long id) {
        BlogResponse response = BlogResponse.minResponse(blogService.changeViews(id));
        return new SuccessfulResponse<>(response);
    }
}
