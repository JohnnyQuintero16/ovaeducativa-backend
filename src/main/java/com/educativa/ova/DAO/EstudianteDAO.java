package com.educativa.ova.DAO;

import com.educativa.ova.Model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudianteDAO extends JpaRepository<Estudiante, String> {
    public Optional<Estudiante> findByEmail(String email);
}
