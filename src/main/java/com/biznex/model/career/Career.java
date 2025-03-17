package com.biznex.model.career;

import com.biznex.common.TechnicalFields;
import com.biznex.common.constant.JobTypeEnum;
import com.biznex.common.constant.Name;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "career")
public class Career extends TechnicalFields {

    @Id
    @SequenceGenerator(name = "CAREER_ID_SEQ", sequenceName = "CAREER_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAREER_ID_SEQ")
    private Long id;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Name name;

    @Lob
    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Name description;

    @Enumerated(EnumType.STRING)
    private JobTypeEnum jobType = JobTypeEnum.FULL_TIME_ONSITE;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Name salaryRange;
}
