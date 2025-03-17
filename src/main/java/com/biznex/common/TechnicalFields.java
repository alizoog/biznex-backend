package com.biznex.common;

import com.biznex.common.constant.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
@ToString(callSuper = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class TechnicalFields implements Serializable {

    @Serial
    private static final long serialVersionUID = 4681401402666658611L;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    @JsonIgnore
    protected Long createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    @JsonIgnore
    protected Long updatedBy;

    @Column(name = "status", columnDefinition = "varchar(20)")
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    private String message;
}
