package com.biznex.controller.admin;

import com.biznex.common.exception.ApiException;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.response.SuccessDataIterable;
import com.biznex.common.response.SuccessfulResponse;
import com.biznex.common.response.status.BlogStatus;
import com.biznex.model.blog.BlogPayload;
import com.biznex.model.blog.BlogResponse;
import com.biznex.model.blog.BlogService;
import com.biznex.utils.WebConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(WebConstants.ADMIN_URL + "/blogs")
@RequiredArgsConstructor
public class BlogAdminController {

    private final BlogService blogService;

    @PreAuthorize(value = "hasAuthority('VIEW_BLOGS')")
    @PostMapping("/pageable")
    public SuccessDataIterable<BlogResponse> getList(@Valid @RequestBody PageableRequest pageable) {
        Page<BlogResponse> response = blogService.getList(pageable).map(BlogResponse::new);
        return new SuccessDataIterable<>(response);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_BLOG')")
    @GetMapping("/{id}")
    public SuccessfulResponse<BlogResponse> getById(@PathVariable Long id) {
        BlogResponse response = new BlogResponse(blogService.getById(id));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('CREATE_BLOG')")
    @PostMapping
    public SuccessfulResponse<BlogResponse> create(@Valid @RequestBody BlogPayload payload) {
        BlogResponse response = new BlogResponse(blogService.create(payload));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('UPDATE_BLOG')")
    @PutMapping("/{id}")
    public SuccessfulResponse<BlogResponse> update(@PathVariable Long id, @Valid @RequestBody BlogPayload payload) {
        BlogResponse response = new BlogResponse(blogService.update(id, payload));
        return new SuccessfulResponse<>(response);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_BLOG')")
    @DeleteMapping("/{id}")
    public SuccessfulResponse<?> delete(@PathVariable Long id) {
        blogService.delete(id);
        throw new ApiException(BlogStatus.DELETED);
    }
}
