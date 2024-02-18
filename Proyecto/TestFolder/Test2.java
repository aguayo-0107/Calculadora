/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Proyecto.TestFolder;
import Proyecto.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel Silvestre Rosales Aguilar
 */
public class Test2 {
    
    public Test2() {
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
        String cadena = "2+3/(5+2)";
        boolean expResult = true;
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
        String cadena = "-(13+5)";
        String expResult = "-(13+5)";
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
        char operExp = '*';
        boolean expResult = false;
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
        String cadena = "2+3/8";
        PilaA<Object> expResult = new PilaA<>();
        expResult.push(2.0);
        expResult.push(3.0);
        expResult.push(8.0);
        expResult.push('/');
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
        postfija.push(8.0);
        postfija.push('/');
        postfija.push('+');
        double expResult = 2.0+(3.0/8.0);
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
        String cadena = "2+3/8";
        double expResult = 2.0+3.0/8.0;
        double result = Calculadora.calcular(cadena);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
