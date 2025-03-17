package com.biznex.model.banner;

import com.biznex.common.JpaGenericRepository;
import com.biznex.common.constant.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long>, JpaGenericRepository<Banner> {

    List<Banner> findAllByStatus(Status status);
}
