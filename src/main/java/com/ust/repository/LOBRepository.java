package com.ust.repository;

import com.ust.entity.LOB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LOBRepository extends JpaRepository<LOB, Long> {
}
