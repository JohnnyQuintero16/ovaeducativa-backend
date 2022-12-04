package com.educativa.ova.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estudiante_ActResponse {
    private String codigo;
    private String nombreEstudiante;
    private String apellidoEstudiante;
    private String email;
    private List<ActividadResponse> actividadResponseList;
}
