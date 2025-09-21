package com.optimus.omnitrix.repo;

import com.optimus.omnitrix.entity.asmith_entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface asmith_repositary extends JpaRepository<asmith_entity, Long>, JpaSpecificationExecutor<asmith_entity>
{
    public Optional<asmith_entity> findByusername(String username);

//interface between entity and  controler
}