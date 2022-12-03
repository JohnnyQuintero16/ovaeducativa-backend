package com.educativa.ova.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "estudiante")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Estudiante {
    @Id
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "email")
    private String email;
    @Column(name = "clave")
    private String clave;

}
