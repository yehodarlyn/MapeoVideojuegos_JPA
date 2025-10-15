/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.videojuegos_jpa.dao;

import com.mycompany.videojuegos_jpa.entity.Videojuego;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonyco
 */
public class VideojuegoDAO {

    private EntityManager entityManager;

    public VideojuegoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // ----- CRUD BASICO ----
    /**
     * Agregar un nuevo videojuego
     */
    public void agregar(Videojuego videojuego) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(videojuego);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error al agregar videojuego: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Editar un videojuego existente
     */
    public void editar(Videojuego videojuego) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(videojuego);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error al editar videojuego: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Eliminar un videojuego
     */
    public void eliminar(Long id) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Videojuego videojuego = entityManager.find(Videojuego.class, id);
            if (videojuego != null) {
                entityManager.remove(videojuego);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error al eliminar videojuego: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Buscar videojuego por ID
     */
    public Videojuego buscarPorId(Long id) {
        try {
            return entityManager.find(Videojuego.class, id);
        } catch (Exception e) {
            System.err.println("Error al buscar videojuego por ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // -------- NAMEDQUERIES -------
    /**
     * Obtener todos los videojuegos
     */
    public List<Videojuego> findAll() {
        try {
            TypedQuery<Videojuego> query = entityManager.createNamedQuery("Videojuego.findAll", Videojuego.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al obtener todos los videojuegos: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Buscar videojuego por nombre
     */
    public List<Videojuego> findByNombre(String nombre) {
        try {
            TypedQuery<Videojuego> query = entityManager.createNamedQuery("Videojuego.findByNombre", Videojuego.class);
            query.setParameter("nombre", nombre);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al buscar videojuego por nombre: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Buscar videojuegos por desarrolladora
     */
    public List<Videojuego> findByDesarrolladora(String desarrolladora) {
        try {
            TypedQuery<Videojuego> query = entityManager.createNamedQuery("Videojuego.findByDesarrolladora", Videojuego.class);
            query.setParameter("desarrolladora", desarrolladora);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al buscar videojuegos por desarrolladora: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // -----CONSULTAS AVANZADAS -------
    /**
     * Listar los videojuegos con mayor numero de logros asociados
     */
    public List<Object[]> listarVideojuegosConMasLogros() {
        try {
            TypedQuery<Object[]> query = entityManager.createNamedQuery("Videojuego.conMasLogros", Object[].class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al listar videojuegos con más logros: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Listar los videojuegos que no tienen ningun logro registrado
     */
    public List<Videojuego> listarVideojuegosSinLogros() {
        try {
            TypedQuery<Videojuego> query = entityManager.createNamedQuery("Videojuego.sinLogros", Videojuego.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al listar videojuegos sin logros: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Listar los videojuegos cuya suma total de puntos de logros supera un
     * valor dado
     */
    public List<Videojuego> listarVideojuegosConSumaPuntosSupera(int puntos) {
        try {
            TypedQuery<Videojuego> query = entityManager.createNamedQuery("Videojuego.conSumaPuntosLogrosSupera", Videojuego.class);
            query.setParameter("puntos", puntos);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al listar videojuegos con suma de puntos superior: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Mostrar el videojuego con el logro mas alto en puntos
     */
    public List<Videojuego> obtenerVideojuegoConLogroMasAlto() {
        try {
            TypedQuery<Videojuego> query = entityManager.createNamedQuery("Videojuego.conLogroMasAlto", Videojuego.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al obtener videojuego con logro más alto: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
