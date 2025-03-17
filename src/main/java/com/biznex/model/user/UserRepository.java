package com.biznex.model.user;

import com.biznex.common.JpaGenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaGenericRepository<User> {

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM User u WHERE LOWER(u.username) = LOWER(?1)")
    boolean existsByUsername(String username);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM User u WHERE u.id <> ?1 AND LOWER(u.username) = LOWER(?2)")
    boolean existsByIdNotAndUsername(Long id, String username);

    Optional<User> findByUsername(String username);
}
