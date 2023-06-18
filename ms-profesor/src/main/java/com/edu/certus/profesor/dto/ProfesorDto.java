package com.edu.certus.profesor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorDto {
	private Long id;
	private String nombre;
	private String apellidos;
	private String sexo;
	private Boolean estado;
}
