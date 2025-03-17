package com.biznex.model.file;

import com.biznex.common.TechnicalFields;
import com.biznex.common.constant.FileType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "files")
public class File extends TechnicalFields {

    @Id
    @SequenceGenerator(name = "FILES_ID_SEQ", sequenceName = "FILES_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILES_ID_SEQ")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "extension")
    private String extension;

    @Column(name = "size")
    private Long size;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "upload_path")
    private String uploadPath;

    @Column(name = "type", columnDefinition = "varchar(20)")
    @Enumerated(EnumType.STRING)
    private FileType type = FileType.DRAFT;

    @PreRemove
    public void preRemove() {
        try {
            Files.deleteIfExists(Paths.get(this.uploadPath));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @PrePersist
    public void prePersist() {
        this.uuid = UUID.randomUUID();
    }
}
