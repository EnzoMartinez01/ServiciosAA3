package com.edu.certus.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.certus.curso.entity.CursoEntity;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Long>{

}
