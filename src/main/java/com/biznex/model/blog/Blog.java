package com.biznex.model.blog;

import com.biznex.common.TechnicalFields;
import com.biznex.common.constant.BlogType;
import com.biznex.common.constant.Name;
import com.biznex.model.file.File;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blogs")
public class Blog extends TechnicalFields {

    @Id
    @SequenceGenerator(name = "VIDEOS_ID_SEQ", sequenceName = "VIDEOS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VIDEOS_ID_SEQ")
    private Long id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "name", nullable = false)
    private Name name;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "title", nullable = false)
    private Name title;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "description", nullable = false)
    private Name description;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private File photo;

    @Column(name = "views")
    private Integer views = 0;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private BlogType type = BlogType.NEWS;
}
