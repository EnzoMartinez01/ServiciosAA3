package com.edu.certus.alumno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.certus.alumno.entity.AlumnoCursoEntity;

@Repository
public interface AlumnoCursoRepository extends JpaRepository<AlumnoCursoEntity, Long>{
	AlumnoCursoEntity findByIdAlumno(Long id);
}
