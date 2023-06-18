package com.edu.certus.profesor.service;

import com.edu.certus.profesor.dto.CursoProfesorDto;
import com.edu.certus.profesor.dto.ResponseDto;

public interface CursoProfesorService {

	public ResponseDto getAllCursoProfesor();
	public ResponseDto getCursoProfesor(Long id);
	public ResponseDto createCursoProfesor(CursoProfesorDto cursoProfesorDto);
	public ResponseDto updateCursoProfesor(CursoProfesorDto cursoProfesorDto);
	public ResponseDto deleteCursoProfesor(Long id);
}
