package com.biznex.model.translation;

import com.biznex.common.TechnicalFields;
import com.biznex.common.constant.Name;
import com.biznex.common.constant.TranslationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "translations")
public class Translation extends TechnicalFields {

    @Id
    @SequenceGenerator(name = "TRANSLATIONS_ID_SEQ", sequenceName = "TRANSLATIONS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSLATIONS_ID_SEQ")
    private Long id;

    @Column(name = "tag", nullable = false, unique = true)
    private String tag;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "name", nullable = false)
    private Name name;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "types", nullable = false)
    private List<TranslationType> types;
}
