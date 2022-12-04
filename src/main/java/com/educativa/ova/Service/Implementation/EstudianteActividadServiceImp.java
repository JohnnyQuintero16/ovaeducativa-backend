package com.educativa.ova.Service.Implementation;

import com.educativa.ova.DAO.Estudiante_ActividadDAO;
import com.educativa.ova.Model.Estudiante;
import com.educativa.ova.Model.Estudiante_Actividad;
import com.educativa.ova.Service.EstudianteActividadService;
import com.educativa.ova.Service.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudianteActividadServiceImp implements EstudianteActividadService {

    private final Estudiante_ActividadDAO estudiante_actividadDAO;

    @Override
    public Estudiante_Actividad createActivity(Estudiante_Actividad estudiante_actividad){
        try {
            return this.estudiante_actividadDAO.save(estudiante_actividad);
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Estudiante_Actividad> getActivityEstudiante(String codigo) {
        return this.estudiante_actividadDAO.findAllByIdEstudiante(codigo);
    }

    public List<Estudiante_Actividad> getActividades(){
        return this.estudiante_actividadDAO.findAll();
    }
}
