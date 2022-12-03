package com.educativa.ova.Controller;

import com.educativa.ova.DTO.LoginDTO;
import com.educativa.ova.DTO.Response;
import com.educativa.ova.Model.Estudiante;
import com.educativa.ova.Service.Implementation.EstudianteServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estudiante")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteServiceImp estudianteServiceImp;

    @PostMapping("/registro")
    public ResponseEntity<?> guardarUsuario(@RequestBody Estudiante estudiante){
        System.out.println("entrando a registrar");
        try{
            if(this.estudianteServiceImp.findByEstudiante(estudiante.getCodigo()) != null)
                return ResponseEntity.status(HttpStatus.OK)
                        .body("Usuario Ya se encuetra registrado con el mismo correo " + estudiante);
            else{
                this.estudianteServiceImp.createEstudiante(estudiante);
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(estudiante);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginUsuario) throws Exception{
        Estudiante user = this.estudianteServiceImp.loginEstudiante(loginUsuario.getEmail(), loginUsuario.getClave());
        if(user != null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(user);
        }
        return new ResponseEntity(new Response(HttpStatus.NOT_ACCEPTABLE.value(), "No logueado"), HttpStatus.NOT_ACCEPTABLE);
    }

}
