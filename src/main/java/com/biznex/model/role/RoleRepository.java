package com.biznex.model.role;

import com.biznex.common.JpaGenericRepository;
import com.biznex.common.constant.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, JpaGenericRepository<Role> {

    @Query("select case when count(r) > 0 then true else false end from Role r where lower(r.name) = lower(?1)")
    boolean existsByName(@Param("name") String name);

    @Query("select case when count(r) > 0 then true else false end from Role r where r.id <> ?1 and lower(r.name) = lower(?2)")
    boolean existsByIdNotAndName(Long id, String name);

    List<Role> findAllByStatus(Status status);

    Optional<Role> findByName(String name);
}
