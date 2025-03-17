package com.biznex.model.user;

import com.biznex.common.TechnicalFields;
import com.biznex.common.constant.Status;
import com.biznex.model.file.File;
import com.biznex.model.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User extends TechnicalFields implements UserDetails {

    @Id
    @SequenceGenerator(name = "USERS_ID_SEQ", sequenceName = "USERS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_ID_SEQ")
    private Long id;

    @Column(name = "first_name", nullable = false)
    @Size(min = 1, max = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Size(min = 1, max = 100)
    private String lastName;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{6,}$",
            message = "Password must be at least 6 characters and contain at least one letter and one number")
    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private File photo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role", nullable = false)
    private Role role;

    @Column(name = "account_non_expired")
    private boolean accountNonExpired = true;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired = true;

    public User(String firstName, String lastName, String username, String password, Role role, Status status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
        super.setStatus(status);
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRole().getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .toList();
    }

    @Override
    public boolean isEnabled() {
        return Status.ACTIVE.equals(getStatus());
    }
}
