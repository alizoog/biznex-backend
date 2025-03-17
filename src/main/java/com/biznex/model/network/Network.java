package com.biznex.model.network;

import com.biznex.common.TechnicalFields;
import com.biznex.model.file.File;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "networks")
public class Network extends TechnicalFields {

    @Id
    @SequenceGenerator(name = "VIDEOS_ID_SEQ", sequenceName = "VIDEOS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VIDEOS_ID_SEQ")
    private Integer id;

    @Column(name = "url", nullable = false)
    private String url;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", nullable = false, referencedColumnName = "id")
    private File photo;
}
