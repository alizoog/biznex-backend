package com.biznex.model.media.photo;

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
@Table(name = "photos")
public class Photo extends TechnicalFields {

    @Id
    @SequenceGenerator(name = "PHOTOS_ID_SEQ", sequenceName = "PHOTOS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PHOTOS_ID_SEQ")
    private Long id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "title", nullable = false)
    private Name title;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", nullable = false, referencedColumnName = "id")
    private File photo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "category_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "photo_category_fk")
    )
    private MediaCategory category;

    @Column(name = "views")
    private Integer views = 0;
}
