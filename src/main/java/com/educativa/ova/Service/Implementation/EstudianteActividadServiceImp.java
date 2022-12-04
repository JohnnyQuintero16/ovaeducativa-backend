package com.educativa.ova.Service.Implementation;

import com.educativa.ova.DAO.Estudiante_ActividadDAO;
import com.educativa.ova.DTO.UpdateActivityRequest;
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

    @Override
    public Estudiante_Actividad updateActivity(UpdateActivityRequest updateActivityRequest) {
        try{
            Estudiante_Actividad estudiante_actividad = estudiante_actividadDAO.findAllByIdEstudiante(updateActivityRequest.getCodigo())
                    .stream()
                    .filter(est -> est.getIdActividad() == updateActivityRequest.getIdActividad()).findFirst().get();
            estudiante_actividad.setCalificacion(updateActivityRequest.getCalificacion());
            estudiante_actividad.setEstadoActividad(1);
            return estudiante_actividadDAO.save(estudiante_actividad);
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
    @Override
    public List<Estudiante_Actividad> getActividades(){
        return this.estudiante_actividadDAO.findAll();
    }
}
