package com.educativa.ova.Controller;

import com.educativa.ova.DTO.UpdateActivityRequest;
import com.educativa.ova.Service.Implementation.EstudianteActividadServiceImp;
import com.educativa.ova.usecase.CargarActividadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actividades")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EstudianteActividadController {
    private final CargarActividadUseCase cargarActividadUseCase;
    private final EstudianteActividadServiceImp estudianteActividadServiceImp;

    @GetMapping(value = "/{codigo}")
    public ResponseEntity<?> getActividadesEstudiante(@PathVariable("codigo") String codigo){
        return ResponseEntity.status(HttpStatus.OK).body(cargarActividadUseCase.getActividades(codigo));
    }

    @GetMapping
    public ResponseEntity<?> getAllActividadesEstudiantes(){
        return ResponseEntity.status(HttpStatus.OK).body(cargarActividadUseCase.getActividades("0"));
    }
    @PutMapping(value = "/updatenota")
    public ResponseEntity<?> updateActividadEstudiante(@RequestBody UpdateActivityRequest updateActivityRequest){
        return ResponseEntity.status(HttpStatus.OK).body(estudianteActividadServiceImp.updateActivity(updateActivityRequest));
    }
}
