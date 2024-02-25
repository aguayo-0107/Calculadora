/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package proyectoed;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fernandaleonhernandez
 */
public class Test1 {
    
    public Test1() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of invertir method, of class Calculadora.
     */
    @Test
    public void testInvertir() {
        System.out.println("invertir");
        PilaA<Object> pila = new PilaA<>();
        PilaA<Object> pilaObjetivo= new PilaA<>();
        pila.push(1);
        pila.push(2);
        pila.push(4);
        pila.push(3);
        pilaObjetivo.push(3);
        pilaObjetivo.push(4);
        pilaObjetivo.push(2);
        pilaObjetivo.push(1);
        Calculadora.invertir(pila);
        assertTrue(pila.equals(pilaObjetivo));
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of chequeo method, of class Calculadora.
     */
    @Test
    public void testChequeo() {
        System.out.println("chequeo");
        String cadena = "";
        boolean expResult = false;
        boolean result = Calculadora.chequeo(cadena);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of cambioNegs method, of class Calculadora.
     */
    @Test
    public void testCambioNegs() {
        System.out.println("cambioNegs");
        String cadena = "-13+5";
        String expResult = "~13+5";
        String result = Calculadora.cambioNegs(cadena);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of mayorOper method, of class Calculadora.
     */
    @Test
    public void testMayorOper() {
        System.out.println("mayorOper");
        char operPila = '+';
        char operExp = '-';
        boolean expResult = true;
        boolean result = Calculadora.mayorOper(operPila, operExp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of cambioPostfija method, of class Calculadora.
     */
    @Test
    public void testCambioPostfija() {
        System.out.println("cambioPostfija");
        String cadena = "2+3";
        PilaA<Object> expResult = new PilaA<>();
        expResult.push(2.0);
        expResult.push(3.0);
        expResult.push('+');
        PilaA<Object> result = Calculadora.cambioPostfija(cadena);
        assertTrue(expResult.equals(result));
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of eval method, of class Calculadora.
     */
    @Test
    public void testEval() {
        System.out.println("eval");
        PilaA<Object> postfija = new PilaA<>();
        postfija.push(2.0);
        postfija.push(3.0);
        postfija.push('+');
        double expResult = 5.0;
        double result = Calculadora.eval(postfija);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of calcular method, of class Calculadora.
     */
    @Test
    public void testCalcular() {
        System.out.println("calcular");
        String cadena = "2+3";
        double expResult = 5.0;
        double result = Calculadora.calcular(cadena);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
        
}
