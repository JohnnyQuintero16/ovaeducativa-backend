package com.educativa.ova.Service.Implementation;

import com.educativa.ova.DAO.ActividadDAO;
import com.educativa.ova.Model.Actividad;
import com.educativa.ova.Service.ActividadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActividadServiceImp implements ActividadService {

    private final ActividadDAO actividadDAO;
    @Override
    public List<Actividad> getActividades() {
        return actividadDAO.findAll();
    }
}
