package com.ust.repository;

import com.ust.entity.ITRC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITRCRepository extends JpaRepository<ITRC,Long> {
}
