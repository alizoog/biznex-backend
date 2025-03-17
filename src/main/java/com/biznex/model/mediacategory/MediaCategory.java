package com.biznex.model.mediacategory;

import com.biznex.common.TechnicalFields;
import com.biznex.common.constant.MediaType;
import com.biznex.common.constant.Name;
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
@Table(name = "media_categories")
public class MediaCategory extends TechnicalFields {

    @Id
    @SequenceGenerator(name = "MEDIA_CATEGORIES_ID_SEQ", sequenceName = "MEDIA_CATEGORIES_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEDIA_CATEGORIES_ID_SEQ")
    private Integer id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "name", nullable = false)
    private Name name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private MediaType type = MediaType.AUDIO;
}
