package Proyecto;
public class Pruebas {
    
    public static void main(String[] args) {
        PilaA postfija = new PilaA();
        String cadena = "-1+2*(3-1)";
        String cadena1 = cadena;
        boolean resB;

        //chequeo de operacion
        System.out.println("Chequeo");
        System.out.println(cadena);
        resB = Calculadora.chequeo(cadena);
        System.out.println(resB);
        
        if (resB) {
            //cambio negs
            System.out.println("\nNegativos");
            cadena = Calculadora.cambioNegs(cadena);
            System.out.println(cadena);

            //infija a postfija
            System.out.println("\nPostfija");
            postfija = Calculadora.cambioPostfija(cadena);
            System.out.println(postfija.toString());
        }
        
        //evaluacion
        if (resB) {
            System.out.println("\nEvaluacion");
            System.out.println(Calculadora.eval(postfija)); 
        }
        
        System.out.println("\nAll Together");
        System.out.println(Calculadora_String.calcular(cadena1));
    }
}
