/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio.controladores.JUnits;

import Dominio.clases.Casilla;
import Dominio.clases.Kakuro;
import java.util.Vector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @file KakuroTest.java
 * @date 20-12-2020
 * @author David Nogales
 * @brief Test de Kakuro
 * 
 *
 */
public class KakuroTest {
    
    public KakuroTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getFilas method, of class Kakuro.
     */
    @Test
    public void testGetFilas() {
        System.out.println("getFilas");
        Kakuro k = new Kakuro();
        int expResult = 0;
        int result = k.getFilas();
        assertEquals(expResult, result);
        Casilla[][]tablero = new Casilla[3][2];
        Kakuro k1 = new Kakuro(5,5, tablero);
        assertEquals(3, k1.getFilas());
    }

    /**
     * Test of getColumnas method, of class Kakuro.
     */
    @Test
    public void testGetColumnas() {
        System.out.println("getColumnas");
        Kakuro k = new Kakuro();
        int expResult = 0;
        int result = k.getColumnas();
        assertEquals(expResult, result);
        Casilla[][]tablero = new Casilla[3][2];
        Kakuro k1 = new Kakuro(5,5, tablero);
        assertEquals(2, k1.getColumnas());
    }

    /**
     * Test of getTablero method, of class Kakuro.
     */
    @Test
    public void testGetTablero() {
        System.out.println("getTablero");
        Kakuro k = new Kakuro();
        Casilla[][] expResult = null;
        Casilla[][] result = k.getTablero();
        assertArrayEquals(expResult, result);
        Casilla[][]tablero = new Casilla[3][2];
        Kakuro k1 = new Kakuro(5,5, tablero);
        assertNotNull(k1.getTablero());
    }

    /**
     * Test of getID_Kakuro method, of class Kakuro.
     */
    @Test
    public void testGetID_Kakuro() {
        System.out.println("getID_Kakuro");
        Kakuro k = new Kakuro();
        int expResult = 0;
        int result = k.getID_Kakuro();
        assertEquals(expResult, result);
    }

   
    /**
     * Test of getceldas_blancas method, of class Kakuro.
     */
    @Test
    public void testGetceldas_blancas() {
        System.out.println("getceldas_blancas");
        Kakuro k = new Kakuro();
        int expResult = 0;
        int result = k.getceldas_blancas();
        assertEquals(expResult, result);
    }

    /**
     * Test of getceldas_negras method, of class Kakuro.
     */
    @Test
    public void testGetceldas_negras() {
        System.out.println("getceldas_negras");
        Kakuro k = new Kakuro();
        int expResult = 0;
        int result = k.getceldas_negras();
        assertEquals(expResult, result);
    }
    
}
