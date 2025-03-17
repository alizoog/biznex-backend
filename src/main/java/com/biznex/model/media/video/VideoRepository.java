package com.biznex.model.media.video;

import com.biznex.common.JpaGenericRepository;
import com.biznex.common.constant.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>, JpaGenericRepository<Video> {

    Optional<Video> findTopByStatusOrderByCreatedAtDesc(Status status);
}
