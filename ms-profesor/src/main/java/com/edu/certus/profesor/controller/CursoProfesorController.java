package com.edu.certus.profesor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.certus.profesor.dto.CursoProfesorDto;
import com.edu.certus.profesor.dto.ResponseDto;
import com.edu.certus.profesor.service.CursoProfesorService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/curso-profesor")
public class CursoProfesorController {
	
	@Autowired
	private CursoProfesorService cursoProfesorService;
	
	@ApiOperation("Método para lista todos los cursos-profesores")
	@GetMapping
	public ResponseEntity<ResponseDto> getAllCursoProfesor(){
		return ResponseEntity.status(HttpStatus.OK).body(cursoProfesorService.getAllCursoProfesor());
	}
	
	@ApiOperation("Método para lista un curso-profesor")
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDto> getCursoProfesor(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(cursoProfesorService.getCursoProfesor(id));
	}
	
	@ApiOperation("Método para crear un curso-profesor")
	@PostMapping
	public ResponseEntity<ResponseDto> createCursoProfesor(@RequestBody CursoProfesorDto cursoProfesorDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(cursoProfesorService.createCursoProfesor(cursoProfesorDto));
	}
	
	@ApiOperation("Método para actualizar un curso-profesor")
	@PutMapping
	public ResponseEntity<ResponseDto> updateAlumno(@RequestBody CursoProfesorDto cursoProfesorDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(cursoProfesorService.updateCursoProfesor(cursoProfesorDto));
	}
	
	@ApiOperation("Método para eliminar un curso-profesor")
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDto> deleteCursoProfesor(@PathVariable("id")Long id){
		return ResponseEntity.status(HttpStatus.OK).body(cursoProfesorService.deleteCursoProfesor(id));
	}
}
