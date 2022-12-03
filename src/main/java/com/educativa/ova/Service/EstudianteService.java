package com.educativa.ova.Service;

import com.educativa.ova.Model.Estudiante;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EstudianteService {
    @Transactional
    public Estudiante createEstudiante(Estudiante estudiante) throws Exception;
    @Transactional(readOnly = true)
    public Estudiante loginEstudiante(String email, String clave) throws Exception;
    @Transactional(readOnly = true)
    public List<Estudiante> getEstudiantes();
    @Transactional(readOnly = true)
    public Estudiante findByEstudiante(String codigo);
}
