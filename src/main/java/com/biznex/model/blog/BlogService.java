package com.biznex.model.blog;

import com.biznex.common.constant.FileType;
import com.biznex.common.exception.ApiException;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.request.PageableRequestUtil;
import com.biznex.common.request.SearchSpecification;
import com.biznex.common.response.status.BlogStatus;
import com.biznex.model.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final FileService fileService;

    public Optional<Blog> findById(Long id) {
        if (id == null) return Optional.empty();
        return blogRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<Blog> getList(PageableRequest pageable) {
        return blogRepository.findAll(new SearchSpecification<>(pageable.getSearch()), PageableRequestUtil.toPageable(pageable));
    }

    public Blog getById(Long id) {
        return findById(id).orElseThrow(() -> new ApiException(BlogStatus.NOT_FOUND));
    }

    public Blog changeViews(Long id) {
        Blog blog = getById(id);
        blog.setViews(blog.getViews() + 1);
        return blogRepository.saveAndFlush(blog);
    }

    public Blog save(Blog blog, BlogPayload payload) {
        blog.setName(payload.getName());
        blog.setTitle(payload.getTitle());
        blog.setDescription(payload.getDescription());
        if (payload.getPhotoId() != null) {
            blog.setPhoto(fileService.changeType(payload.getPhotoId(), FileType.ACTIVE));
        }
        blog.setType(payload.getType());
        blog.setStatus(payload.getStatus());
        return blogRepository.saveAndFlush(blog);
    }

    public Blog create(BlogPayload payload) {
        Blog blog = new Blog();
        return save(blog, payload);
    }

    public Blog update(Long id, BlogPayload payload) {
        Blog blog = getById(id);
        return save(blog, payload);
    }

    public void delete(Long id) {
        Blog blog = getById(id);
        blogRepository.delete(blog);
    }
}
