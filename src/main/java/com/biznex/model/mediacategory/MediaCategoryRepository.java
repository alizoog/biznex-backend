package com.biznex.model.mediacategory;

import com.biznex.common.JpaGenericRepository;
import com.biznex.common.constant.MediaType;
import com.biznex.common.constant.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaCategoryRepository extends JpaRepository<MediaCategory, Integer>, JpaGenericRepository<MediaCategory> {

    List<MediaCategory> findAllByTypeAndStatus(MediaType type, Status status);
}
