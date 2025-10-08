/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.videojuegos_jpa.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author Laboratorios
 */
public class Videojuego implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nombre;
    private int puntaje;
    private String desarrolladora;
    
    @OneToMany(mappedBy = "videojuego", cascade = CascadeType.ALL)
    private Set<Logro> logros;

    @ManyToMany
    @JoinTable(
            name = "videojuego_jugador",
            joinColumns = @JoinColumn(name = "videojuego_id"),
            inverseJoinColumns = @JoinColumn(name = "jugador_id")
    )
    
    private Set<Jugador> jugadores;
    public Videojuego() {
    }

    public Videojuego(Long id, String nombre, int puntaje, String desarrolladora, Set<Logro> logros, Set<Jugador> jugadores) {
        this.id = id;
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.desarrolladora = desarrolladora;
        this.logros = logros;
        this.jugadores = jugadores;
    }

    
    



    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getDesarrolladora() {
        return desarrolladora;
    }

    public void setDesarrolladora(String desarrolladora) {
        this.desarrolladora = desarrolladora;
    }

    public Set getLogros() {
        return logros;
    }

    public void setLogros(Set logros) {
        this.logros = logros;
    }
    
}
