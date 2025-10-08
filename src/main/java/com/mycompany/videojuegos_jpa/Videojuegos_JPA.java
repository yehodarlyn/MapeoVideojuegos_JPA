/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.videojuegos_jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author Laboratorios
 */
public class Videojuegos_JPA {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideoJuegosPU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.getTransaction().commit();;
        
        em.close();
        emf.close();
                
        
        
    }
}
