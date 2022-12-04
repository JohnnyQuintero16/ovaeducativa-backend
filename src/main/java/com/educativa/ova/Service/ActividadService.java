package com.educativa.ova.Service;

import com.educativa.ova.Model.Actividad;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ActividadService {

    @Transactional(readOnly = true)
    public List<Actividad> getActividades();
}
