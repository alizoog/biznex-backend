package com.biznex.model.media.video;

import com.biznex.common.TechnicalFields;
import com.biznex.common.constant.Name;
import com.biznex.model.file.File;
import com.biznex.model.mediacategory.MediaCategory;
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
@Table(name = "videos")
public class Video extends TechnicalFields {

    @Id
    @SequenceGenerator(name = "VIDEOS_ID_SEQ", sequenceName = "VIDEOS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VIDEOS_ID_SEQ")
    private Long id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "title", nullable = false)
    private Name title;

    @Column(name = "url", nullable = false)
    private String url;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private File photo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "category_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "video_category_fk")
    )
    private MediaCategory category;

    @Column(name = "views")
    private Integer views = 0;
}
