/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.videojuegos_jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Laboratorios
 */
@Entity
@Table(name = "jugador")
@NamedQueries({
    @NamedQuery(name = "Jugador.findAll", query = "SELECT j FROM Jugador j"),
    @NamedQuery(name = "Jugador.findByPseudonimo", query = "SELECT j FROM Jugador j WHERE j.pseudonimo = :pseudonimo"),
    @NamedQuery(name = "Jugador.findBySexo", query = "SELECT j FROM Jugador j WHERE j.sexo = :sexo"),
    @NamedQuery(name = "Jugador.conMasVideojuegos",
            query = "SELECT j, COUNT(v) as numVideojuegos FROM Jugador j LEFT JOIN j.videojuegos v GROUP BY j ORDER BY numVideojuegos DESC"),
    @NamedQuery(name = "Jugador.conPuntosMayoresA",
            query = "SELECT DISTINCT j FROM Jugador j JOIN j.videojuegos v JOIN v.logros l GROUP BY j HAVING SUM(l.puntos) > :puntos"),
    @NamedQuery(name = "Jugador.porColoniaConMasDeUnVideojuego",
            query = "SELECT j FROM Jugador j WHERE j.direccion.colonia = :colonia AND SIZE(j.videojuegos) > 1"),
    @NamedQuery(name = "Jugador.ordenadosPorEdad",
            query = "SELECT j FROM Jugador j ORDER BY j.fechaNacimiento ASC"),
    @NamedQuery(name = "Jugador.conDireccionOrdenadoPorColonia",
            query = "SELECT j FROM Jugador j JOIN FETCH j.direccion d ORDER BY d.colonia ASC")
})
public class Jugador {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fechaNacimiento")
    private LocalDate fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;

    @Column(name = "pseudonimo")
    private String pseudonimo;

    @Column(name = "sexo")
    private String sexo;

    @ManyToMany(mappedBy = "jugadores")
    private List<Videojuego> videojuegos = new ArrayList<>();

    public Jugador() {
    }

    public Jugador(LocalDate fechaNacimiento, String pseudonimo, String sexo) {
        this.fechaNacimiento = fechaNacimiento;
        this.pseudonimo = pseudonimo;
        this.sexo = sexo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getPseudonimo() {
        return pseudonimo;
    }

    public void setPseudonimo(String pseudonimo) {
        this.pseudonimo = pseudonimo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public List<Videojuego> getVideojuegos() {
        return videojuegos;
    }

    public void setVideojuegos(List<Videojuego> videojuegos) {
        this.videojuegos = videojuegos;
    }

}
