package com.biznex.model.career;

import com.biznex.common.JpaGenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepository extends JpaRepository<Career, Long>, JpaGenericRepository<Career> {
}
