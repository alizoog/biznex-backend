package com.biznex.model.media.photo;

import com.biznex.common.JpaGenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long>, JpaGenericRepository<Photo> {

}
