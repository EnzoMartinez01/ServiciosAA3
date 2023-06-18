package com.edu.certus.curso.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edu.certus.curso.dto.CursoDto;
import com.edu.certus.curso.dto.ResponseDto;
import com.edu.certus.curso.entity.CursoEntity;
import com.edu.certus.curso.repository.CursoRepository;
import com.edu.certus.curso.service.CursoService;
import com.edu.certus.curso.util.Constantes;
import com.edu.certus.curso.util.Util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CursoServiceImpl implements CursoService{
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Override
	public ResponseDto getAllCurso() {
		try {
			List<CursoEntity> listacursosEntity = cursoRepository.findAll();
			List<CursoDto> listCursoDto = new ArrayList<CursoDto>();
			for (CursoEntity cursoEntity : listacursosEntity) {
				listCursoDto.add(CursoDto.builder()
				.id(cursoEntity.getId())
				.descripcion(cursoEntity.getDescripcion())
				.estado(cursoEntity.getEstado())
				.build());
			}
			return Util.getResponse(true, Constantes.OPERATION_SUCCESS, listCursoDto);
		} catch (Exception e) {
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}
	}

	@Override
	public ResponseDto getCurso(Long id) {
		try {
			CursoEntity cursoEntity= cursoRepository.findById(id).orElse(null);
			if(null == cursoEntity) {
				return Util.getResponse(true, Constantes.NO_RECORDS_ROUND, null);
			}
			CursoDto cursoDto = CursoDto.builder()
				.id(cursoEntity.getId())
				.descripcion(cursoEntity.getDescripcion())
				.estado(cursoEntity.getEstado())
				.build();
			return Util.getResponse(true, Constantes.OPERATION_SUCCESS, cursoDto);
		} catch (Exception e) {
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}
	}

	@Override
	public ResponseDto createCurso(CursoDto curso) {
		try {
			CursoEntity cursoEntity = CursoEntity.builder()
					.descripcion(curso.getDescripcion())
					.estado(true)
					.build();
			cursoRepository.save(cursoEntity);
			curso.setId(cursoEntity.getId());
			return Util.getResponse(true, Constantes.OPERATION_SUCCESS, curso);
		} catch (Exception e) {
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}
	}

	@Override
	public ResponseDto updateCurso(CursoDto curso) {
		try {
			CursoEntity cursoEntity= cursoRepository.findById(curso.getId()).orElse(null);
			if(null == cursoEntity) {
				return Util.getResponse(true, Constantes.NO_RECORDS_ROUND, null);
			}
			cursoEntity.setDescripcion(curso.getDescripcion());
			cursoRepository.save(cursoEntity);
			return Util.getResponse(true, Constantes.OPERATION_SUCCESS, curso);
		} catch (Exception e) {
			log.error(Constantes.OPERATION_FAILED, e);
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}
	}

	@Override
	public ResponseDto deleteCurso(Long id) {
		try {
			CursoEntity cursoEntity= cursoRepository.findById(id).orElse(null);
			cursoEntity.setEstado(false);
			cursoRepository.save(cursoEntity);
			return Util.getResponse(true, Constantes.OPERATION_SUCCESS, null);
		} catch (Exception e) {
			log.error(Constantes.OPERATION_FAILED, e);
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}
	}
}