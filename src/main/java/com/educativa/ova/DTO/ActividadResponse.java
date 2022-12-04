package com.educativa.ova.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActividadResponse {
    private String nombreActividad;
    private float calificacion;
    private int estadoActividad;
}
