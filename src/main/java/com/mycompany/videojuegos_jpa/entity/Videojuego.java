/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.videojuegos_jpa.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Laboratorios
 */
@Entity
@Table(name = "videojuego")
@NamedQueries({
    @NamedQuery(name = "Videojuego.findAll", query = "SELECT v FROM Videojuego v"),
    @NamedQuery(name = "Videojuego.findByNombre", query = "SELECT v FROM Videojuego v WHERE v.nombre = :nombre"),
    @NamedQuery(name = "Videojuego.findByDesarrolladora", query = "SELECT v FROM Videojuego v WHERE v.desarrolladora = :desarrolladora"),
    @NamedQuery(name = "Videojuego.conMasLogros",
            query = "SELECT v, COUNT(l) as numLogros FROM Videojuego v LEFT JOIN v.logros l GROUP BY v ORDER BY numLogros DESC"),
    @NamedQuery(name = "Videojuego.sinLogros",
            query = "SELECT v FROM Videojuego v WHERE v.logros IS EMPTY"),
    @NamedQuery(name = "Videojuego.conSumaPuntosLogrosSupera",
            query = "SELECT v FROM Videojuego v JOIN v.logros l GROUP BY v HAVING SUM(l.puntos) > :puntos"),
    @NamedQuery(name = "Videojuego.conLogroMasAlto",
            query = "SELECT v FROM Videojuego v JOIN v.logros l WHERE l.puntos = (SELECT MAX(l2.puntos) FROM Logro l2)")
})
public class Videojuego implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "puntaje")
    private Integer puntaje;

    @Column(name = "desarrolladora")
    private String desarrolladora;

    @Column(name = "nombre")
    private String nombre;

    @ManyToMany
    @JoinTable(
            name = "videojuego_jugador",
            joinColumns = @JoinColumn(name = "videojuego_id"),
            inverseJoinColumns = @JoinColumn(name = "jugador_id")
    )
    private List<Jugador> jugadores = new ArrayList<>();

    @OneToMany(mappedBy = "videojuego", cascade = CascadeType.ALL)
    private List<Logro> logros = new ArrayList<>();

    public Videojuego() {
    }

    public Videojuego(String nombre, String desarrolladora, Integer puntaje) {
        this.nombre = nombre;
        this.desarrolladora = desarrolladora;
        this.puntaje = puntaje;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public String getDesarrolladora() {
        return desarrolladora;
    }

    public void setDesarrolladora(String desarrolladora) {
        this.desarrolladora = desarrolladora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public List<Logro> getLogros() {
        return logros;
    }

    public void setLogros(List<Logro> logros) {
        this.logros = logros;
    }

}
