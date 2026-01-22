package com.jonatas.rbacapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jonatas.rbacapi.domain.entity.Lead;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {

}
