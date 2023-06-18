package com.edu.certus.profesor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.certus.profesor.entity.CursoProfesorEntity;

@Repository
public interface CursoProfesorRepository extends JpaRepository<CursoProfesorEntity, Long>{
	CursoProfesorEntity findByIdCurso(Long id);

}
