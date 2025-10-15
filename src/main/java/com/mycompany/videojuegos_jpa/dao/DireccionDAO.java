/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.videojuegos_jpa.dao;

import com.mycompany.videojuegos_jpa.entity.Direccion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonyco
 */
public class DireccionDAO {

    private EntityManager entityManager;

    public DireccionDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    
    /**
     * Agregar una nueva direccion
     */
    public void agregar(Direccion direccion) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(direccion);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error al agregar dirección: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Editar una direccion existente
     */
    public void editar(Direccion direccion) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(direccion);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error al editar dirección: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Eliminar una direccion
     */
    public void eliminar(Long id) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Direccion direccion = entityManager.find(Direccion.class, id);
            if (direccion != null) {
                entityManager.remove(direccion);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error al eliminar dirección: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Buscar dirección por ID
     */
    public Direccion buscarPorId(Long id) {
        try {
            return entityManager.find(Direccion.class, id);
        } catch (Exception e) {
            System.err.println("Error al buscar dirección por ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // ----------NAMEDQUERIES -------
    /**
     * Obtener todas las direcciones
     */
    public List<Direccion> findAll() {
        try {
            TypedQuery<Direccion> query = entityManager.createNamedQuery("Direccion.findAll", Direccion.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al obtener todas las direcciones: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Buscar direcciones por colonia
     */
    public List<Direccion> findByColonia(String colonia) {
        try {
            TypedQuery<Direccion> query = entityManager.createNamedQuery("Direccion.findByColonia", Direccion.class);
            query.setParameter("colonia", colonia);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al buscar direcciones por colonia: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Buscar direcciones por calle
     */
    public List<Direccion> findByCalle(String calle) {
        try {
            TypedQuery<Direccion> query = entityManager.createNamedQuery("Direccion.findByCalle", Direccion.class);
            query.setParameter("calle", calle);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al buscar direcciones por calle: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
