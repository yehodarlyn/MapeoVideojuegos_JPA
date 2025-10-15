/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.videojuegos_jpa.dao;

import com.mycompany.videojuegos_jpa.entity.Logro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonyco
 */
public class LogroDAO {

    private EntityManager entityManager;

    public LogroDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // -------- CRUD BASICO -------
    /**
     * Agregar un nuevo logro
     */
    public void agregar(Logro logro) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(logro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error al agregar logro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Editar un logro existente
     */
    public void editar(Logro logro) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(logro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error al editar logro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Eliminar un logro
     */
    public void eliminar(Long id) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Logro logro = entityManager.find(Logro.class, id);
            if (logro != null) {
                entityManager.remove(logro);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error al eliminar logro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Buscar logro por ID
     */
    public Logro buscarPorId(Long id) {
        try {
            return entityManager.find(Logro.class, id);
        } catch (Exception e) {
            System.err.println("Error al buscar logro por ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // --------  NAMEDQUERIES ------
    /**
     * Obtener todos los logros
     */
    public List<Logro> findAll() {
        try {
            TypedQuery<Logro> query = entityManager.createNamedQuery("Logro.findAll", Logro.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al obtener todos los logros: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Buscar logro por nombre
     */
    public List<Logro> findByNombre(String nombre) {
        try {
            TypedQuery<Logro> query = entityManager.createNamedQuery("Logro.findByNombre", Logro.class);
            query.setParameter("nombre", nombre);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al buscar logro por nombre: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // -------- CONSULTAS AVANZADAS --------
    /**
     * Listar los logros cuyo puntaje sea mayor al promedio general de todos los
     * logros
     */
    public List<Logro> listarLogrosConPuntosMayorAlPromedio() {
        try {
            TypedQuery<Logro> query = entityManager.createNamedQuery("Logro.conPuntosMayorAlPromedio", Logro.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al listar logros con puntos mayor al promedio: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
