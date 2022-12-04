package com.educativa.ova.Service;


import com.educativa.ova.DTO.UpdateActivityRequest;
import com.educativa.ova.Model.Estudiante_Actividad;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EstudianteActividadService {
    @Transactional
    public Estudiante_Actividad createActivity(Estudiante_Actividad estudiante_actividad);

    @Transactional(readOnly = true)
    public List<Estudiante_Actividad> getActivityEstudiante(String codigo);

    @Transactional
    public Estudiante_Actividad updateActivity(UpdateActivityRequest updateActivityRequest);

    @Transactional(readOnly = true)
    public List<Estudiante_Actividad> getActividades();
}
