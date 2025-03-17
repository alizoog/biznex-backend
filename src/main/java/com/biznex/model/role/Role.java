package com.biznex.model.role;

import com.biznex.common.TechnicalFields;
import com.biznex.common.constant.Permission;
import com.biznex.common.constant.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role extends TechnicalFields {

    @Id
    @SequenceGenerator(name = "roles_id_seq", sequenceName = "roles_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_id_seq")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false, columnDefinition = "text")
    private String description;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Permission> permissions;

    public Role(String name, List<Permission> permissions, String description, Status status) {
        this.name = name;
        this.description = description;
        this.permissions = permissions;
        super.setStatus(status);
    }
}
