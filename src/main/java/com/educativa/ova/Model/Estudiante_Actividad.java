package com.educativa.ova.Model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "estudiante_actividad")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Estudiante_Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "id_estudiante")
    private String idEstudiante;
    @Column(name = "id_actividad")
    private int idActividad;
    @Column(name = "calificacion")
    private float calificacion;
    @Column(name = "estado_actividad")
    private int estadoActividad;
}
