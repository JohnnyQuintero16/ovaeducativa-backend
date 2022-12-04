package com.educativa.ova.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateActivityRequest {
    private String codigo;
    private int idActividad;
    private float calificacion;
}
