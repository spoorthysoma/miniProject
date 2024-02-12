package com.jasper.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jasper.entity.AuditEntity;

@Repository
public interface AuditRepository extends JpaRepository<AuditEntity, Integer> {

	List<AuditEntity> findAllByCreatedTimeBetween(LocalDateTime fromDate, LocalDateTime toDate);

	AuditEntity save(AuditEntity auditEntity);
}
