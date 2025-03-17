package com.biznex.model.blog;

import com.biznex.common.JpaGenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaGenericRepository<Blog> {

}
