/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio.controladores.JUnits;

import Dominio.clases.Casilla;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @file CasillaTest.java
 * @date 20-12-2020
 * @author David Nogales
 * @brief Test de casilla
 * 
 *
 */


public class CasillaTest {
    
    public CasillaTest() {
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
     * Test of setTipo method, of class Casilla.
     */
    @Test
    public void testSetTipo() {
        System.out.println("setTipo");
        boolean t = false;
        Casilla c = new Casilla();
        c.setTipo(t);
        assertFalse(c.getTipo());
        t = true;
        c.setTipo(t);
        assertTrue(c.getTipo());
    }

    /**
     * Test of setHorizontal method, of class Casilla.
     */
    @Test
    public void testSetHorizontal() {
        System.out.println("setHorizontal");
        boolean h = true;
        Casilla c = new Casilla();
        assertFalse(c.getHorizontal());
        c.setHorizontal(h);
        assertTrue(c.getHorizontal());
    }

    /**
     * Test of setVertical method, of class Casilla.
     */
    @Test
    public void testSetVertical() {
        System.out.println("setVertical");
        boolean v = true;
        Casilla c = new Casilla();
        assertFalse(c.getVertical());
        c.setVertical(v);
        assertTrue(c.getVertical());
    }

    /**
     * Test of setValor method, of class Casilla.
     */
    @Test
    public void testSetValor() {
        System.out.println("setValor");
        int val = 2;
        Casilla c = new Casilla();
        assertEquals(0,c.getValor());
        c.setValor(val);
        assertEquals(val,c.getValor());
    }

    /**
     * Test of setValorAux method, of class Casilla.
     */
    @Test
    public void testSetValorAux() {
        System.out.println("setValorAux");
        int val = 3;
        Casilla c = new Casilla();
        assertEquals(0,c.getValorAux());
        c.setValorAux(val);
        assertEquals(val,c.getValorAux());
    }

    /**
     * Test of setX method, of class Casilla.
     */
    @Test
    public void testSetX() {
        System.out.println("setX");
        int x = 1;
        Casilla c = new Casilla();
        assertEquals(0,c.getX());
        c.setX(x);
        assertEquals(x,c.getX());
    }

    /**
     * Test of setY method, of class Casilla.
     */
    @Test
    public void testSetY() {
        System.out.println("setY");
        int y = 5;
        Casilla c = new Casilla();
        assertEquals(0,c.getY());
        c.setY(y);
        assertEquals(y,c.getY());
    }

    /**
     * Test of getTipo method, of class Casilla.
     */
    @Test
    public void testGetTipo() {
        System.out.println("getTipo");
        Casilla c = new Casilla();
        boolean expResult = true;
        boolean result = c.getTipo();
        assertEquals(expResult, result);
        expResult = false;
        c.setTipo(expResult);
        assertEquals(expResult, c.getTipo());
        
    }

    /**
     * Test of getValor method, of class Casilla.
     */
    @Test
    public void testGetValor() {
        System.out.println("getValor");
        Casilla c = new Casilla();
        int expResult = 0;
        int result = c.getValor();
        assertEquals(expResult, result);
        expResult = 5;
        c.setValor(5);
        assertEquals(expResult, c.getValor());
    }

    /**
     * Test of getValorAux method, of class Casilla.
     */
    @Test
    public void testGetValorAux() {
        System.out.println("getValorAux");
        Casilla c = new Casilla();
        int expResult = 0;
        int result = c.getValorAux();
        assertEquals(expResult, result);
        expResult = 5;
        c.setValorAux(5);
        assertEquals(expResult, c.getValorAux());
    }

    /**
     * Test of getHorizontal method, of class Casilla.
     */
    @Test
    public void testGetHorizontal() {
        System.out.println("getHorizontal");
        Casilla c = new Casilla();
        boolean expResult = false;
        boolean result = c.getHorizontal();
        assertEquals(expResult, result);
        c.setHorizontal(true);
        assertTrue(c.getHorizontal());
    }

    /**
     * Test of getVertical method, of class Casilla.
     */
    @Test
    public void testGetVertical() {
        System.out.println("getVertical");
        Casilla c = new Casilla();
        boolean expResult = false;
        boolean result = c.getVertical();
        assertEquals(expResult, result);
        c.setVertical(true);
        assertTrue(c.getVertical());
    }

    /**
     * Test of getY method, of class Casilla.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        Casilla c = new Casilla();
        int expResult = 0;
        int result = c.getY();
        assertEquals(expResult, result);
        c.setY(4);
        expResult = 4;
        assertEquals(expResult,c.getY());
    }

    /**
     * Test of getX method, of class Casilla.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Casilla c = new Casilla();
        int expResult = 0;
        int result = c.getX();
        assertEquals(expResult, result);
        c.setX(5);
        expResult = 5;
        assertEquals(expResult,c.getX());
    }

    /**
     * Test of toString method, of class Casilla.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Casilla c = new Casilla();
        String expResult = "?";
        String result = c.toString();
        assertEquals(expResult, result);
        c.setTipo(false);
        expResult = "*";
        assertEquals(expResult, c.toString());
        c.setValor(2);
        expResult = "2";
        assertEquals(expResult, c.toString());
        c.setVertical(true);
        expResult = "C2";
        assertEquals(expResult, c.toString());
        c.setValorAux(1);
        expResult = "C2";
        assertEquals(expResult, c.toString());
        c.setHorizontal(true);
        expResult = "C2F1";
        assertEquals(expResult, c.toString());
    }
    
}
