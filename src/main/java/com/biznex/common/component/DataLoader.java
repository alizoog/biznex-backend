package com.biznex.common.component;

import com.biznex.common.constant.Permission;
import com.biznex.common.constant.Status;
import com.biznex.common.constant.SystemRole;
import com.biznex.model.user.User;
import com.biznex.model.user.UserRepository;
import com.biznex.model.role.Role;
import com.biznex.model.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String initialMode;

    @Override
    public void run(String... args) {
        if (initialMode.equals("always")) {
            Permission[] values = Permission.values();

            Optional<Role> optionalRole = roleRepository.findByName(SystemRole.ADMIN.name());
            Role admin = null;
            if (optionalRole.isEmpty()) {
                admin = roleRepository.save(new Role(
                        SystemRole.ADMIN.name(),
                        Arrays.asList(values),
                        "System owner",
                        Status.ACTIVE
                ));
            }

            Optional<User> optionalUser = userRepository.findByUsername("admin");
            if (optionalUser.isEmpty()) {
                userRepository.save(new User(
                        "Ibrokhim",
                        "Khamidov",
                        "alizoog",
                        passwordEncoder.encode("ali786"),
                        admin,
                        Status.ACTIVE
                ));
            }
        }
    }
}
