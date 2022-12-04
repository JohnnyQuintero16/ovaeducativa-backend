package com.educativa.ova.usecase;

import com.educativa.ova.DTO.ActividadResponse;
import com.educativa.ova.DTO.Estudiante_ActResponse;
import com.educativa.ova.Model.Actividad;
import com.educativa.ova.Model.Estudiante;
import com.educativa.ova.Model.Estudiante_Actividad;
import com.educativa.ova.Service.Implementation.ActividadServiceImp;
import com.educativa.ova.Service.Implementation.EstudianteActividadServiceImp;
import com.educativa.ova.Service.Implementation.EstudianteServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class CargarActividadUseCase {
    private final ActividadServiceImp actividadServiceImp;
    private final EstudianteActividadServiceImp estudianteActividadServiceImp;
    private final EstudianteServiceImp estudianteServiceImp;

    public void cargarActividades(String codigo){
        List<Actividad> actividadList = actividadServiceImp.getActividades();
        for(Actividad act:actividadList){
            Estudiante_Actividad estudiante_actividad = new Estudiante_Actividad(null,codigo,act.getId(),0.0F,2);
            estudianteActividadServiceImp.createActivity(estudiante_actividad);
        }
    }

    public List<Estudiante_ActResponse> getActividades(String codigo){
        List<Estudiante_Actividad> estudiante_actividadList = estudianteActividadServiceImp.getActividades();
        List<Estudiante_ActResponse> actResponses = new ArrayList<>();
        Map<Integer,String> mapActividad = new HashMap<>();
        for(Actividad activity:actividadServiceImp.getActividades()){
            mapActividad.put(activity.getId(),activity.getNombre());
        }
        boolean isVisited = false;
        for(Estudiante estudiante:estudianteServiceImp.getEstudiantes()){
            if(isVisited) break;

            Estudiante_ActResponse estudiante_actResponse = new Estudiante_ActResponse();
            estudiante_actResponse.setCodigo(estudiante.getCodigo());
            estudiante_actResponse.setNombreEstudiante(estudiante.getNombre());
            estudiante_actResponse.setApellidoEstudiante(estudiante.getApellido());
            estudiante_actResponse.setEmail(estudiante.getEmail());
            List<ActividadResponse> actividadResponseList = new ArrayList<>();
            int cntActivity = mapActividad.size();
            for(Estudiante_Actividad estudiante_actividad:estudiante_actividadList){
                if(cntActivity == 0) break;
                ActividadResponse actividadResponse = new ActividadResponse();
                if(codigo.equals("0")){
                    if(estudiante.getCodigo().equals(estudiante_actividad.getIdEstudiante())){
                        actividadResponse.setEstadoActividad(estudiante_actividad.getEstadoActividad());
                        actividadResponse.setCalificacion(estudiante_actividad.getCalificacion());
                        actividadResponse.setNombreActividad(mapActividad.get(estudiante_actividad.getIdActividad()));
                        cntActivity--;
                    }
                }else{
                    if(estudiante.getCodigo().equals(codigo)){
                        actividadResponse.setEstadoActividad(estudiante_actividad.getEstadoActividad());
                        actividadResponse.setCalificacion(estudiante_actividad.getCalificacion());
                        actividadResponse.setNombreActividad(mapActividad.get(estudiante_actividad.getIdActividad()));
                        isVisited = true;
                        cntActivity--;
                    }
                }

                if(actividadResponse.getEstadoActividad() != 0)
                    actividadResponseList.add(actividadResponse);
            }
            estudiante_actResponse.setActividadResponseList(actividadResponseList);
            if(!actividadResponseList.isEmpty())
                actResponses.add(estudiante_actResponse);
        }
        Collections.sort(actResponses, new Comparator<Estudiante_ActResponse>() {
            @Override
            public int compare(Estudiante_ActResponse o1, Estudiante_ActResponse o2) {
                return (o1.getCodigo().compareTo(o2.getCodigo()));
            }
        });
        return actResponses;
    }
}
