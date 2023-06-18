package com.edu.certus.profesor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CursoDto {
	private Long id;
	private String descripcion;
	private Boolean estado;
}