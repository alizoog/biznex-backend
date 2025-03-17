package com.biznex.model.media.audio;

import com.biznex.common.JpaGenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudioRepository extends JpaRepository<Audio, Long>, JpaGenericRepository<Audio> {

}
