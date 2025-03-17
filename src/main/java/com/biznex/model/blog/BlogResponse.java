package com.biznex.model.blog;

import com.biznex.common.constant.BlogType;
import com.biznex.common.constant.Name;
import com.biznex.common.response.TechnicalFieldsResponse;
import com.biznex.model.file.FileResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlogResponse extends TechnicalFieldsResponse {
    private Long id;
    private Name name;
    private Name title;
    private Name description;
    private FileResponse photo;
    private Integer views;
    private BlogType type;

    public BlogResponse(Blog blog) {
        this.id = blog.getId();
        this.name = blog.getName();
        this.title = blog.getTitle();
        this.description = blog.getDescription();
        if (blog.getPhoto() != null) {
            this.photo = FileResponse.minResponse(blog.getPhoto());
        }
        this.views = blog.getViews();
        this.type = blog.getType();
        this.createdAt = blog.getCreatedAt();
        this.updatedAt = blog.getUpdatedAt();
        this.status = blog.getStatus();
    }

    public static BlogResponse minResponse(Blog blog) {
        BlogResponse blogResponse = new BlogResponse();
        blogResponse.setId(blog.getId());
        blogResponse.setName(blog.getName());
        blogResponse.setTitle(blog.getTitle());
        blogResponse.setDescription(blog.getDescription());
        blogResponse.setPhoto(FileResponse.minResponse(blog.getPhoto()));
        blogResponse.setCreatedAt(blog.getCreatedAt());
        blogResponse.setViews(blog.getViews());
        blogResponse.setType(blog.getType());
        return blogResponse;
    }
}
