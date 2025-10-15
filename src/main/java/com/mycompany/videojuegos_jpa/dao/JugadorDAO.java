/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.videojuegos_jpa.dao;

import com.mycompany.videojuegos_jpa.entity.Jugador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonyco
 */
public class JugadorDAO {

    private EntityManager entityManager;

    public JugadorDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    
    /**
     * Agregar un nuevo jugador
     */
    public void agregar(Jugador jugador) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(jugador);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error al agregar jugador: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Editar un jugador existente
     */
    public void editar(Jugador jugador) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(jugador);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error al editar jugador: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Eliminar un jugador
     */
    public void eliminar(Long id) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Jugador jugador = entityManager.find(Jugador.class, id);
            if (jugador != null) {
                entityManager.remove(jugador);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error al eliminar jugador: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Buscar jugador por id 
     */
    public Jugador buscarPorId(Long id) {
        try {
            return entityManager.find(Jugador.class, id);
        } catch (Exception e) {
            System.err.println("Error al buscar jugador por ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // ------  NAMEDQUERIES ------ 
    /**
     * Obtener todos los jugadores
     */
    public List<Jugador> findAll() {
        try {
            TypedQuery<Jugador> query = entityManager.createNamedQuery("Jugador.findAll", Jugador.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al obtener todos los jugadores: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Buscar jugador por pseudonimo
     */
    public List<Jugador> findByPseudonimo(String pseudonimo) {
        try {
            TypedQuery<Jugador> query = entityManager.createNamedQuery("Jugador.findByPseudonimo", Jugador.class);
            query.setParameter("pseudonimo", pseudonimo);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al buscar jugador por pseudonimo: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Buscar jugadores por sexo
     */
    public List<Jugador> findBySexo(String sexo) {
        try {
            TypedQuery<Jugador> query = entityManager.createNamedQuery("Jugador.findBySexo", Jugador.class);
            query.setParameter("sexo", sexo);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al buscar jugadores por sexo: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // -------- CONSULTAS AVANZADAS --------
    /**
     * Listar los jugadores con más videojuegos registrados, mostrando su
     * cantidad total
     */
    public List<Object[]> listarJugadoresConMasVideojuegos() {
        try {
            TypedQuery<Object[]> query = entityManager.createNamedQuery("Jugador.conMasVideojuegos", Object[].class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al listar jugadores con más videojuegos: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Obtener los jugadores cuya suma total de puntos (por logros de los
     * videojuegos que juegan) sea mayor a un valor dado
     */
    public List<Jugador> obtenerJugadoresConPuntosMayoresA(int puntos) {
        try {
            TypedQuery<Jugador> query = entityManager.createNamedQuery("Jugador.conPuntosMayoresA", Jugador.class);
            query.setParameter("puntos", puntos);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al obtener jugadores con puntos mayores: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Listar los jugadores que viven en una colonia especifica y tienen mas de
     * un videojuego asociado
     */
    public List<Jugador> listarJugadoresPorColoniaConMasDeUnVideojuego(String colonia) {
        try {
            TypedQuery<Jugador> query = entityManager.createNamedQuery("Jugador.porColoniaConMasDeUnVideojuego", Jugador.class);
            query.setParameter("colonia", colonia);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al listar jugadores por colonia con más de un videojuego: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Listar los jugadores ordenados por edad (de mayor a menor)
     */
    public List<Jugador> listarJugadoresOrdenadosPorEdad() {
        try {
            TypedQuery<Jugador> query = entityManager.createNamedQuery("Jugador.ordenadosPorEdad", Jugador.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al listar jugadores ordenados por edad: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Listar los jugadores junto con el nombre de su calle y colonia, ordenados
     * alfabeticamente por colonia
     */
    public List<Jugador> listarJugadoresConDireccionOrdenadoPorColonia() {
        try {
            TypedQuery<Jugador> query = entityManager.createNamedQuery("Jugador.conDireccionOrdenadoPorColonia", Jugador.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al listar jugadores con dirección ordenado por colonia: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
