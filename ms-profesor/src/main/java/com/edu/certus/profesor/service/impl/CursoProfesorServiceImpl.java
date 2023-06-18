package com.edu.certus.profesor.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.certus.profesor.client.CursoClient;
import com.edu.certus.profesor.dto.CursoDto;
import com.edu.certus.profesor.dto.CursoProfesorDto;
import com.edu.certus.profesor.dto.ResponseDto;
import com.edu.certus.profesor.entity.CursoProfesorEntity;
import com.edu.certus.profesor.entity.ProfesorEntity;
import com.edu.certus.profesor.repository.CursoProfesorRepository;
import com.edu.certus.profesor.repository.ProfesorRepository;
import com.edu.certus.profesor.service.CursoProfesorService;
import com.edu.certus.profesor.util.Constantes;
import com.edu.certus.profesor.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CursoProfesorServiceImpl implements CursoProfesorService {
	
	@Autowired
	private CursoProfesorRepository cursoProfesorRepository;
	
	@Autowired
	private ProfesorRepository profesorRepository;
	
	@Autowired
	private CursoClient cursoClient;

	@Override
	public ResponseDto getAllCursoProfesor() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			//Aqui recorremos la lista alumnocursoentity
			List<CursoProfesorEntity> listCursoProfesorEntity = cursoProfesorRepository.findAll();
			List<CursoProfesorDto> listCursoProfesorDto = new ArrayList<CursoProfesorDto>();
			
			//El for va a recorrer la cantidad que tiene la lista
			for (int i = 0; i < listCursoProfesorEntity.size(); i++) {
				ProfesorEntity profesorEntity = profesorRepository.findById(listCursoProfesorEntity.get(i).getIdProfesor()).orElse(null);
				
				ResponseDto responseDto = cursoClient.readCurso(listCursoProfesorEntity.get(i).getIdCurso());
				CursoDto cursoDto = mapper.convertValue(responseDto.getData(), CursoDto.class);
				
				listCursoProfesorDto.add(CursoProfesorDto.builder()
						.idCurso(cursoDto.getId())
						.nombreCurso(cursoDto.getDescripcion())
						.estado(cursoDto.getEstado())
						.idProfesor(profesorEntity.getId())
						.nombreProfesor(profesorEntity.getNombre() + " " + profesorEntity.getApellidos())
						.estadoProfesor(profesorEntity.getEstado())
						.build());
			}
			return Util.getResponse(true, Constantes.OPERATION_SUCCESS, listCursoProfesorDto);
		}catch (RetryableException ex) {
			log.error("RetryableException: " + Constantes.SERVICE_NOT_AVAILABLE + " " + ex);
			return Util.getResponse(false, Constantes.SERVICE_NOT_AVAILABLE, null);
		} catch (Exception e) {
			log.error("Exception: " + Constantes.OPERATION_FAILED + " " + e);
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}
	}

	@Override
	public ResponseDto getCursoProfesor(Long id) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			CursoProfesorEntity cursoProfesorEntity = cursoProfesorRepository.findByIdCurso(id);
			if(cursoProfesorEntity == null) {
				return Util.getResponse(true, Constantes.NO_RECORDS_FOUND, cursoProfesorEntity);
			}
			
			ProfesorEntity profesorEntity = profesorRepository.findById(cursoProfesorEntity.getIdProfesor()).orElse(null);
			ResponseDto responseDto = cursoClient.readCurso(cursoProfesorEntity.getIdCurso());
			CursoDto cursoDto = mapper.convertValue(responseDto.getData(), CursoDto.class);
			
			CursoProfesorDto cursoProfesorDto = CursoProfesorDto.builder()
							.idCurso(cursoDto.getId())
							.nombreCurso(cursoDto.getDescripcion())
							.estado(cursoDto.getEstado())
							.idProfesor(profesorEntity.getId())
							.nombreProfesor(profesorEntity.getNombre() + " " + profesorEntity.getApellidos())
							.estadoProfesor(profesorEntity.getEstado())
							.build();
			return Util.getResponse(true, Constantes.OPERATION_SUCCESS, cursoProfesorDto);
		}catch (RetryableException ex) {
			log.error("RetryableException: " + Constantes.SERVICE_NOT_AVAILABLE + " " + ex);
			return Util.getResponse(false, Constantes.SERVICE_NOT_AVAILABLE, null);
		} catch (Exception e) {
			log.error("Exception: " + Constantes.OPERATION_FAILED + " " + e);
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}
	}

	@Override
	public ResponseDto createCursoProfesor(CursoProfesorDto cursoProfesorDto) {
		try {
			CursoProfesorEntity cursoProfesorEntity = CursoProfesorEntity.builder()
				.idProfesor(cursoProfesorDto.getIdProfesor())
				.idCurso(cursoProfesorDto.getIdCurso())
				.build();
			
			cursoProfesorRepository.save(cursoProfesorEntity);
			return Util.getResponse(true, Constantes.OPERATION_SUCCESS, null);
		} catch (Exception e) {
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}
	}
	
	@Override
	public ResponseDto updateCursoProfesor(CursoProfesorDto cursoProfesorDto) {
		try {
			CursoProfesorEntity cursoProfesorEntity = cursoProfesorRepository.findById(cursoProfesorDto.getIdProfesor()).orElse(null);
			if(null == cursoProfesorEntity) {
				return Util.getResponse(true, Constantes.NO_RECORDS_FOUND, null);
			}
			cursoProfesorEntity.setIdCurso(cursoProfesorDto.getIdCurso());
			cursoProfesorEntity.setIdProfesor(cursoProfesorDto.getIdProfesor());
			cursoProfesorRepository.save(cursoProfesorEntity);
			return Util.getResponse(true, Constantes.OPERATION_SUCCESS, cursoProfesorDto);
		} catch (Exception e) {
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}
	}
	
	@Override
	public ResponseDto deleteCursoProfesor(Long id) {
		try {
			CursoProfesorEntity cursoProfesorEntity = cursoProfesorRepository.findById(id).orElse(null);
			cursoProfesorRepository.delete(cursoProfesorEntity);
			return Util.getResponse(true, Constantes.OPERATION_SUCCESS, null);
		}catch (Exception e) {
			log.error(Constantes.OPERATION_FAILED, e);
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}
	}

}
