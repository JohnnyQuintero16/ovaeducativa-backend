package com.educativa.ova.Controller;

import com.educativa.ova.DTO.LoginDTO;
import com.educativa.ova.DTO.Response;
import com.educativa.ova.Model.Estudiante;
import com.educativa.ova.Model.Estudiante_Actividad;
import com.educativa.ova.Service.Implementation.EstudianteActividadServiceImp;
import com.educativa.ova.Service.Implementation.EstudianteServiceImp;
import com.educativa.ova.usecase.CargarActividadUseCase;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/estudiante")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteServiceImp estudianteServiceImp;
    private final CargarActividadUseCase cargarActividadUseCase;

    @PostMapping("/registro")
    public ResponseEntity<?> guardarUsuario(@RequestBody Estudiante estudiante){
        try{
            if(this.estudianteServiceImp.findByEstudiante(estudiante.getCodigo()) != null)
                return new ResponseEntity<>(new Response(HttpStatus.OK.value(), "Usuario ya se encuentra registrado, por favor verifica los datos"), HttpStatus.OK);
            else{
                this.estudianteServiceImp.createEstudiante(estudiante);
                cargarActividadUseCase.cargarActividades(estudiante.getCodigo());
            }
        }catch (Exception e){
            if(e.getClass().equals(DataIntegrityViolationException.class)){
                return new ResponseEntity<>(new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Por favor verifica tus datos, ya tenemos un correo registrado igual que el tuyo"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Lo sentimos ha ocurrido un error :("), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(estudiante);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginUsuario) throws Exception{
        Estudiante user = this.estudianteServiceImp.loginEstudiante(loginUsuario.getEmail(), loginUsuario.getClave());
        if(user != null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(user);
        }
        return new ResponseEntity<>(new Response(HttpStatus.FORBIDDEN.value(), "No has logrado iniciar sesión, vuelve a intentar"), HttpStatus.FORBIDDEN);
    }
}
