package com.educativa.ova.DAO;

import com.educativa.ova.Model.Estudiante_Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Estudiante_ActividadDAO extends JpaRepository<Estudiante_Actividad, Integer> {
    public List<Estudiante_Actividad> findAllByIdEstudiante(String idEstudiante);
}
