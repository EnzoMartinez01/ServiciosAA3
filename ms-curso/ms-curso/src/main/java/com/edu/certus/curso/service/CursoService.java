package com.edu.certus.curso.service;

import com.edu.certus.curso.dto.CursoDto;
import com.edu.certus.curso.dto.ResponseDto;

public interface CursoService {
	public ResponseDto getAllCurso();
	public ResponseDto getCurso(Long id);
	public ResponseDto createCurso(CursoDto curso);
	public ResponseDto updateCurso(CursoDto curso);
	public ResponseDto deleteCurso(Long id);
}
