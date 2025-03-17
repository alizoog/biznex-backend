package com.biznex.model.banner;

import com.biznex.common.TechnicalFields;
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
@Table(name = "banners")
public class Banner extends TechnicalFields {

    @Id
    @SequenceGenerator(name = "BANNERS_ID_SEQ", sequenceName = "BANNERS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BANNERS_ID_SEQ")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", nullable = false, referencedColumnName = "id")
    private File photo;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Name title;
}
