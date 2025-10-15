/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.videojuegos_jpa;

import com.mycompany.videojuegos_jpa.dao.DireccionDAO;
import com.mycompany.videojuegos_jpa.dao.JugadorDAO;
import com.mycompany.videojuegos_jpa.dao.LogroDAO;
import com.mycompany.videojuegos_jpa.dao.VideojuegoDAO;
import com.mycompany.videojuegos_jpa.entity.Direccion;
import com.mycompany.videojuegos_jpa.entity.Jugador;
import com.mycompany.videojuegos_jpa.entity.Logro;
import com.mycompany.videojuegos_jpa.entity.Videojuego;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Laboratorios
 */
public class Videojuegos_JPA {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideoJuegosPU");
        EntityManager em = emf.createEntityManager();

        // Crear instancias de los DAOs
        DireccionDAO direccionDAO = new DireccionDAO(em);
        JugadorDAO jugadorDAO = new JugadorDAO(em);
        VideojuegoDAO videojuegoDAO = new VideojuegoDAO(em);
        LogroDAO logroDAO = new LogroDAO(em);

        try {
            System.out.println("=".repeat(80));
            System.out.println("INICIO DE PRUEBAS - SISTEMA DE VIDEOJUEGOS");
            System.out.println("=".repeat(80));

            // --------PRUEBAS DE CRUD ------
            System.out.println("\n" + "=".repeat(80));
            System.out.println("1. PRUEBAS DE CRUD BÁSICO");
            System.out.println("=".repeat(80));

            // Crear direcciones
            System.out.println("\n--- Creando Direcciones ---");
            Direccion dir1 = new Direccion("Av. Principal", "Centro", "123");
            Direccion dir2 = new Direccion("Calle Secundaria", "Norte", "456");
            Direccion dir3 = new Direccion("Av. Universidad", "Centro", "789");
            direccionDAO.agregar(dir1);
            direccionDAO.agregar(dir2);
            direccionDAO.agregar(dir3);
            System.out.println(" Direcciones creadas exitosamente");

            // Crear jugadores
            System.out.println("\n--- Creando Jugadores ---");
            Jugador jugador1 = new Jugador(LocalDate.of(1995, 5, 15), "DragonSlayer", "M");
            jugador1.setDireccion(dir1);
            Jugador jugador2 = new Jugador(LocalDate.of(1998, 8, 20), "PhoenixRider", "F");
            jugador2.setDireccion(dir1);
            Jugador jugador3 = new Jugador(LocalDate.of(2000, 3, 10), "ShadowNinja", "M");
            jugador3.setDireccion(dir2);
            Jugador jugador4 = new Jugador(LocalDate.of(1992, 12, 5), "MysticMage", "F");
            jugador4.setDireccion(dir3);
            jugadorDAO.agregar(jugador1);
            jugadorDAO.agregar(jugador2);
            jugadorDAO.agregar(jugador3);
            jugadorDAO.agregar(jugador4);
            System.out.println(" Jugadores creados exitosamente");

            // Crear videojuegos
            System.out.println("\n--- Creando Videojuegos ---");
            Videojuego juego1 = new Videojuego("Dark Souls III", "FromSoftware", 95);
            Videojuego juego2 = new Videojuego("The Witcher 3", "CD Projekt Red", 98);
            Videojuego juego3 = new Videojuego("Minecraft", "Mojang", 90);
            Videojuego juego4 = new Videojuego("Hollow Knight", "Team Cherry", 92);
            Videojuego juego5 = new Videojuego("Indie Game Test", "Small Studio", 85);
            videojuegoDAO.agregar(juego1);
            videojuegoDAO.agregar(juego2);
            videojuegoDAO.agregar(juego3);
            videojuegoDAO.agregar(juego4);
            videojuegoDAO.agregar(juego5);
            System.out.println(" Videojuegos creados exitosamente");

            // Asociar jugadores con videojuegos
            System.out.println("\n--- Asociando Jugadores con Videojuegos ---");
            juego1.getJugadores().add(jugador1);
            juego1.getJugadores().add(jugador2);
            jugador1.getVideojuegos().add(juego1);
            jugador2.getVideojuegos().add(juego1);

            juego2.getJugadores().add(jugador1);
            juego2.getJugadores().add(jugador3);
            juego2.getJugadores().add(jugador4);
            jugador1.getVideojuegos().add(juego2);
            jugador3.getVideojuegos().add(juego2);
            jugador4.getVideojuegos().add(juego2);

            juego3.getJugadores().add(jugador2);
            juego3.getJugadores().add(jugador3);
            jugador2.getVideojuegos().add(juego3);
            jugador3.getVideojuegos().add(juego3);

            juego4.getJugadores().add(jugador4);
            jugador4.getVideojuegos().add(juego4);

            videojuegoDAO.editar(juego1);
            videojuegoDAO.editar(juego2);
            videojuegoDAO.editar(juego3);
            videojuegoDAO.editar(juego4);
            System.out.println(" Asociaciones creadas exitosamente");

            // Crear logros
            System.out.println("\n--- Creando Logros ---");
            Logro logro1 = new Logro("Primer Jefe Derrotado", 100);
            logro1.setVideojuego(juego1);
            Logro logro2 = new Logro("Maestro de Combate", 250);
            logro2.setVideojuego(juego1);
            Logro logro3 = new Logro("Final del Juego", 500);
            logro3.setVideojuego(juego1);

            Logro logro4 = new Logro("Cazador Novato", 50);
            logro4.setVideojuego(juego2);
            Logro logro5 = new Logro("Cazador Experto", 200);
            logro5.setVideojuego(juego2);
            Logro logro6 = new Logro("Todos los Contratos", 400);
            logro6.setVideojuego(juego2);
            Logro logro7 = new Logro("Final Epico", 600);
            logro7.setVideojuego(juego2);

            Logro logro8 = new Logro("Primera Casa", 30);
            logro8.setVideojuego(juego3);
            Logro logro9 = new Logro("Derrota al Ender Dragon", 300);
            logro9.setVideojuego(juego3);

            Logro logro10 = new Logro("Todos los Amuletos", 150);
            logro10.setVideojuego(juego4);

            logroDAO.agregar(logro1);
            logroDAO.agregar(logro2);
            logroDAO.agregar(logro3);
            logroDAO.agregar(logro4);
            logroDAO.agregar(logro5);
            logroDAO.agregar(logro6);
            logroDAO.agregar(logro7);
            logroDAO.agregar(logro8);
            logroDAO.agregar(logro9);
            logroDAO.agregar(logro10);
            System.out.println(" Logros creados exitosamente (Juego5 sin logros)");

            // Prueba de busqueda por ID
            System.out.println("\n--- Prueba de Busqueda por ID ---");
            Jugador jugadorBuscado = jugadorDAO.buscarPorId(jugador1.getId());
            if (jugadorBuscado != null) {
                System.out.println("✓ Jugador encontrado: " + jugadorBuscado.getPseudonimo());
            }

            // -------- CONSULTAS AVANZADAS -------
            System.out.println("\n" + "=".repeat(80));
            System.out.println("2. CONSULTAS AVANZADAS");
            System.out.println("=".repeat(80));

            // 1. Jugadores con mas videojuegos
            System.out.println("\n--- 1. Jugadores con mas videojuegos registrados ---");
            List<Object[]> jugadoresConMasVideojuegos = jugadorDAO.listarJugadoresConMasVideojuegos();
            for (Object[] resultado : jugadoresConMasVideojuegos) {
                Jugador j = (Jugador) resultado[0];
                Long count = (Long) resultado[1];
                System.out.println("  - " + j.getPseudonimo() + ": " + count + " videojuegos");
            }

            // 2. Videojuegos con mas logros
            System.out.println("\n--- 2. Videojuegos con mayor numero de logros asociados ---");
            List<Object[]> videojuegosConMasLogros = videojuegoDAO.listarVideojuegosConMasLogros();
            for (Object[] resultado : videojuegosConMasLogros) {
                Videojuego v = (Videojuego) resultado[0];
                Long count = (Long) resultado[1];
                System.out.println("  - " + v.getNombre() + ": " + count + " logros");
            }

            // 3. Jugadores con suma de puntos mayor a un valor
            System.out.println("\n--- 3. Jugadores con mas de 500 puntos totales ---");
            List<Jugador> jugadoresConPuntos = jugadorDAO.obtenerJugadoresConPuntosMayoresA(500);
            for (Jugador j : jugadoresConPuntos) {
                System.out.println("  - " + j.getPseudonimo());
            }

            // 4. Videojuegos sin logros
            System.out.println("\n--- 4. Videojuegos sin logros registrados ---");
            List<Videojuego> juegosSinLogros = videojuegoDAO.listarVideojuegosSinLogros();
            for (Videojuego v : juegosSinLogros) {
                System.out.println("  - " + v.getNombre());
            }

            // 5. Jugadores por colonia con mas de un videojuego
            System.out.println("\n--- 5. Jugadores de la colonia 'Centro' con mas de 1 videojuego ---");
            List<Jugador> jugadoresPorColonia = jugadorDAO.listarJugadoresPorColoniaConMasDeUnVideojuego("Centro");
            for (Jugador j : jugadoresPorColonia) {
                System.out.println("  - " + j.getPseudonimo() + " (Colonia: " + j.getDireccion().getColonia() + ")");
            }

            // 6. Logros con puntos mayor al promedio
            System.out.println("\n--- 6. Logros con puntaje mayor al promedio ---");
            List<Logro> logrosSuperiores = logroDAO.listarLogrosConPuntosMayorAlPromedio();
            for (Logro l : logrosSuperiores) {
                System.out.println("  - " + l.getNombre() + ": " + l.getPuntos() + " puntos");
            }

            // 7. Videojuegos con suma de puntos de logros superior
            System.out.println("\n--- 7. Videojuegos con suma de puntos de logros > 400 ---");
            List<Videojuego> juegosConPuntos = videojuegoDAO.listarVideojuegosConSumaPuntosSupera(400);
            for (Videojuego v : juegosConPuntos) {
                System.out.println("  - " + v.getNombre());
            }

            // 8. Jugadores ordenados por edad (de mayor a menor)
            System.out.println("\n--- 8. Jugadores ordenados por edad (mayor a menor) ---");
            List<Jugador> jugadoresPorEdad = jugadorDAO.listarJugadoresOrdenadosPorEdad();
            for (Jugador j : jugadoresPorEdad) {
                int edad = LocalDate.now().getYear() - j.getFechaNacimiento().getYear();
                System.out.println("  - " + j.getPseudonimo() + " (Edad: " + edad + " años)");
            }

            // 9. Videojuego con logro mas alto
            System.out.println("\n--- 9. Videojuego con el logro mas alto en puntos ---");
            List<Videojuego> juegoConLogroAlto = videojuegoDAO.obtenerVideojuegoConLogroMasAlto();
            for (Videojuego v : juegoConLogroAlto) {
                System.out.println("  - " + v.getNombre());
            }

            // 10. Jugadores con direccion ordenados por colonia
            System.out.println("\n--- 10. Jugadores con su direccion (ordenados por colonia) ---");
            List<Jugador> jugadoresConDireccion = jugadorDAO.listarJugadoresConDireccionOrdenadoPorColonia();
            for (Jugador j : jugadoresConDireccion) {
                System.out.println("  - " + j.getPseudonimo() + " - Calle: "
                        + j.getDireccion().getCalle() + ", Colonia: "
                        + j.getDireccion().getColonia());
            }

            // -------- PRUEBAS DE NAMEDQUERIES --------
            System.out.println("\n" + "=".repeat(80));
            System.out.println("3. PRUEBAS DE NAMEDQUERIES");
            System.out.println("=".repeat(80));

            // Videojuego por nombre
            System.out.println("\n--- Buscar videojuego 'Dark Souls III' ---");
            List<Videojuego> juegosPorNombre = videojuegoDAO.findByNombre("Dark Souls III");
            for (Videojuego v : juegosPorNombre) {
                System.out.println("  - Encontrado: " + v.getNombre() + " (Desarrolladora: " + v.getDesarrolladora() + ")");
            }

            // Jugadores por sexo
            System.out.println("\n--- Jugadores femeninos ---");
            List<Jugador> jugadorasFemeninas = jugadorDAO.findBySexo("F");
            for (Jugador j : jugadorasFemeninas) {
                System.out.println("  - " + j.getPseudonimo());
            }

            // Direcciones por colonia
            System.out.println("\n--- Direcciones en la colonia 'Centro' ---");
            List<Direccion> direccionesCentro = direccionDAO.findByColonia("Centro");
            for (Direccion d : direccionesCentro) {
                System.out.println("  - " + d.getCalle() + " " + d.getNumero());
            }

            // Logros por nombre
            System.out.println("\n--- Buscar logro 'Final del Juego' ---");
            List<Logro> logrosPorNombre = logroDAO.findByNombre("Final del Juego");
            for (Logro l : logrosPorNombre) {
                System.out.println("  - Encontrado: " + l.getNombre() + " (" + l.getPuntos() + " puntos)");
            }

            // --------- PRUEBA DE EDICION -------
            System.out.println("\n" + "=".repeat(80));
            System.out.println("4. PRUEBA DE EDICION");
            System.out.println("=".repeat(80));

            Jugador jugadorEditar = jugadorDAO.buscarPorId(jugador1.getId());
            System.out.println("\nPseudonimo original: " + jugadorEditar.getPseudonimo());
            jugadorEditar.setPseudonimo("Pepito2023");
            jugadorDAO.editar(jugadorEditar);

            Jugador jugadorEditado = jugadorDAO.buscarPorId(jugador1.getId());
            System.out.println("Pseudonimo actualizado: " + jugadorEditado.getPseudonimo());

            System.out.println("\n" + "=".repeat(80));
            System.out.println("TODAS LAS PRUEBAS COMPLETADAS EXITOSAMENTE");
            System.out.println("=".repeat(80));

        } catch (Exception e) {
            System.err.println("\nError durante las pruebas: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
