package com.biznex.model.network;

import com.biznex.common.JpaGenericRepository;
import com.biznex.common.constant.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NetworkRepository extends JpaRepository<Network, Integer>, JpaGenericRepository<Network> {

    List<Network> findAllByStatus(Status status);
}
