/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio.controladores.JUnits;

import Dominio.clases.Kakuro;
import Dominio.clases.partida;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @file KakuroTest.java
 * @date 20-12-2020
 * @author Pere Masip
 * @brief Test de partida
 * 
 *
 */
public class partidaTest {
    public partidaTest() {
        
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
     * Test of getID method, of class partida.
     */
    @Test
    public void testgetID() {
        System.out.println("getID");
        partida p = new partida();       
        String res = "";       
        assertEquals(p.getID(), res);       
    }
    /**
     * Test of setidPartida method, of class partida.
     */
    @Test
    public void testsetIdPartida() {
        System.out.println("setIdPartida");
        partida p = new partida();
        String id = "hola";
        p.setIdPartida(id);
        String res = "hola";       
        assertEquals(p.getIDPartida(), res);       
    }
    /**
     * Test of getIDPartida method, of class partida.
     */
    @Test
    public void testgetIDPartida() {
        System.out.println("getIDPartida");
        partida p = new partida();
        String id = "hola";        
        String res = "";       
        assertEquals(p.getIDPartida(), res);
    }
    /**
     * Test of getKakuro method, of class partida.
     */
    @Test
    public void testgetKakuro() {
        System.out.println("getKakuro");
        partida p = new partida();              
        Kakuro res = null;       
        assertEquals(res, p.getKakuro());       
    }
    /**
     * Test of getTime method, of class partida.
     */
    @Test
    public void testgetTime() {
        System.out.println("getTime");
        partida p = new partida();              
        int time = 50000;
        int res = 0;
        assertEquals(res, p.getTime());
        p.setTime(50000);
        assertEquals(time, p.getTime());
    }
}
