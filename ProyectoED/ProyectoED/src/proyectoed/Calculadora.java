package proyectoed;

/**
 *Clase que contiene metodos que realizan operaciones de estilo
 * calculadora con cadenas
 * @author Fernanda Leon, Mariana Aguayo, Marcio Tellez, Silvestre Rosales, Natalia Wilson
 */
public class Calculadora {
    /**
     * Este metodo invierte el orden de los elementos en una pila,
     * utilizando dos pilas auxiliares.
     *
     * @param pila la pila de objetos que se va a invertir  
     */
    public static void invertir (PilaA <Object> pila) {
        // Crea dos pilas auxiliares
        PilaA<Object>  extra = new PilaA<Object> ();
        PilaA<Object>  extra2 = new PilaA<Object> ();
        
        // Mueve todos los elementos de la pila original a la primera pila auxiliar
        while(!pila.isEmpty()) {
            extra.push(pila.pop());
        }
        // Mueve todos los elementos de la primera pila a la segunda pila auxiliar
        while(!extra.isEmpty()) {
            extra2.push(extra.pop());
        }
         // Regresa todos los elementos de la segunda pila a la pila original
        while(!extra2.isEmpty()) {
            pila.push(extra2.pop());
        }
    }
    
    /**
     * Este metodo verifica si una cadena que representa una expresion matematica es valida,
     * tambien comprueba que la expresion no termine en un operador, que los parentesis esten emparejados,
     * que no haya dos operadores juntos (con el caso del negativo por aparte), 
     * y que no falten operadores entre numeros y parentesis.
     *
     * @param cadena La expresion matematica a checar.
     * @return true si la cadena es una expresion matematica valida, false en caso contrario.
     */
    public static boolean chequeo (String cadena) {
        PilaA<Object>  chequeo = new PilaA<Object>  ();
        boolean res = true;
        
        //checar que no termine en operador
        if (cadena.endsWith("+") || cadena.endsWith("-") || cadena.endsWith("*") || cadena.endsWith("/") || cadena.endsWith("^")) {
            res = false;
        }
        else {
            //checar que parentesis empiecen y terminen
           for (int i=0; i<cadena.length(); i++) {
            switch (cadena.charAt(i)) {
                case '(':
                    chequeo.push(cadena.charAt(i));
                    break;
                case ')':
                    if (chequeo.isEmpty()) {
                        res = false;
                    }
                    else {
                        if((char)chequeo.peek() == '(') {
                            chequeo.pop();
                        }
                    }
                    break;
                //checar que no haya 2 operadores juntos (excepto negativo)
                case '+':
                case '*':
                case '/':
                case '^':
                case '-':
                case '.':
                    if (i < cadena.length()-1) { 
                        if (cadena.charAt(i+1) == '+' || cadena.charAt(i+1) == '*' || cadena.charAt(i+1) == '/' || cadena.charAt(i+1) == '^' || cadena.charAt(i+1) == '.') {
                            res = false;   
                        }
                    }
                    break;
                default:
                    //checar que no falten operadores entre numeros y parentesis
                    if (i < cadena.length()-1 && cadena.charAt(i+1) == '(') {
                        res = false;
                    }
                } 
            }
        }       
        if (!chequeo.isEmpty()) {
            res = false;
        }
        return res;
    }
    
    /**
     * Este metodo reemplaza los signos negativos en la cadena que estamos manejando por
     * con un caracter especial (~) para indicar que el siguiente numero es negativo.
     *
     * @param cadena La cadena que representa la expresion matematica
     * @return La cadena modificada con los signos negativos reemplazados por ~.
     */
    public static String cambioNegs (String cadena) {
        String [] charas;
        StringBuilder cad = new StringBuilder();
        
        //se separa la cadena en un arreglo de String
        charas = cadena.split("||");
        for (int i=0; i<charas.length; i++) {
            //si hay un negativo como primer caracter, entonces lo siguiente sera negativo
            if (charas[i].equals("-")) {
                if (i == 0) {
                    //checa que el siguiente caracter no sea un parentesis, ya que en ese caso se debe multiplicar todo el parentesis
                    if (!charas[i+1].equals("(")) {
                        charas[i] = "~";
                    }
                }
                //si hay un negativo despues de un operador, entonces lo siguiente es negativo
                else {
                    switch (charas[i-1].charAt(0)) {
                        case '+': 
                        case '-':
                        case '*':
                        case '/':
                        case '^':
                        case '(':
                        case ')':
                            charas[i] = "~";
                            break;
                        default:
                    } 
                }
            }
        }
        //unir el arreglo de neuvo
        for (int i=0; i<charas.length; i++) {
            cad.append(charas[i]);
        }
        return cad.toString();
    }
    
    /**
     * Este metodo determina si un operador tiene una mayor jerarquia que otro,
     * evalua expresiones matematicas en notacion infija.
     *
     * @param operPila El operador que esta actualmente en la cima de la pila.
     * @param operExp El operador que se esta considerando para agregar a la pila.
     * @return true si el operador de la pila tiene mayor precedencia, false en caso contrario.
     */
    public static boolean mayorOper(char operPila, char operExp) {
        boolean res = true;
       
        switch (operPila) {
            case '+':
            case '-':
                if (operExp == '*' || operExp == '/' || operExp == '^') {
                    res = false;
                }
                break;
            case '*':
            case '/':
                if (operExp == '^') {
                    res = false;
                }
               break;
            case '(':
            case ')':
                res = false;
                break;
            default:
        }
        return res;
    }
    
    /**
     * Este metodo convierte una expresion matematica de notacion infija
     * a notacion postfija.
     *
     * @param cadena La cadena que representa la expresion matematica en notacion infija.
     * @return Una pila que representa la expresion matematica en notacion postfija.
     */
    public static PilaA<Object>  cambioPostfija (String cadena) {
        PilaA<Object>  postfija = new PilaA<Object> ();
        PilaA<Object>  pilaOperadores = new PilaA<Object> ();
        boolean neg = false;
        Double numero;
        String [] charas;
        StringBuilder num = new StringBuilder();
        int i = 0;
        
        //separamos la cadena en un arreglo de String
        charas = cadena.split("||");
        while (!cadena.isEmpty() && i<charas.length) {
            switch (charas[i].charAt(0)) {
                case '+': 
                case '-': 
                case '*':
                case '/':
                case '^':
                    while (charas[i].length()!= 0 && !pilaOperadores.isEmpty() && Calculadora.mayorOper((char)pilaOperadores.peek(), charas[i].charAt(0))) {
                        postfija.push(pilaOperadores.pop());
                    }
                    pilaOperadores.push(charas[i].charAt(0));
                    break;
                case '(':
                    pilaOperadores.push(charas[i].charAt(0)); 
                    break;
                case ')':
                    while (!pilaOperadores.isEmpty() && (char)pilaOperadores.peek() != '(' ) {
                        postfija.push(pilaOperadores.pop());
                    }
                    pilaOperadores.pop();
                    break;
                case '~':
                    neg = true;
                    break;
                default:
                    num.delete(0, num.length());
                    while (i<charas.length && !charas[i].equals("+") && !charas[i].equals("-") && !charas[i].equals("*") && !charas[i].equals("/") && !charas[i].equals("^") && !charas[i].equals("(") && !charas[i].equals(")")){
                        num.append(charas[i]);
                        i++;
                    }
                    i--;
                    numero = Double.valueOf(num.toString());
                    if (neg) {
                        numero = -1*numero;
                        neg = false;
                    }
                    postfija.push(numero);
            }
            i++;
        }
        while (!pilaOperadores.isEmpty()) {
            postfija.push(pilaOperadores.pop());
        }
        return postfija;
    }
   
    /**
     * Este metodo evalua una expresion matematica en notacion postfija,
     * La expresion es una pila de objetos, los cuales pueden ser
     * numeros o caracteres que representan operadores,
     * Y utiliza una pila auxiliar para ayudar en la evaluacion de la expresion.
     *
     * @param postfija La pila que representa la expresion matematica en notacion postfija.
     * @return El resultado de evaluar la expresion matematica.
     * @throws SintaxErr si se intenta dividir por cero.
     */
   public static double eval (PilaA<Object>  postfija) {
        PilaA<Object>  extra = new PilaA<Object> ();
        boolean ab = false;
        char oper;
        double b, res=0, a=0, x=0;
        
        invertir(postfija);
        while (!postfija.isEmpty()) {
            if (!postfija.peek().equals('+') && !postfija.peek().equals('-') && !postfija.peek().equals('*') && !postfija.peek().equals('/') && !postfija.peek().equals('^')) {
                extra.push(postfija.pop());
            }
            else {
                oper = (char)postfija.pop();            
                b = Double.parseDouble(extra.pop().toString());
                if (!extra.isEmpty()) {
                    a = Double.parseDouble(extra.pop().toString());
                    ab =true;
                }
                if (ab) {
                    switch (oper) {
                    case '+':
                        x = a+b;
                        break;
                    case '-':
                        x = a-b;
                        break;
                    case '*':
                        x = a*b;
                        break;
                    case '/':
                        //throw exception
                        if (b != 0) {
                            x = a/b;
                        }
                        else {
                            throw new SintaxErr("Error, division entre cero");
                        }
                        break;
                    case '^':
                        x = Math.pow(a, b);
                        break;
                    default:
                    }
                }
                else {
                    switch (oper) {
                    case '+':
                        x = b*1;
                        break;
                    case '-':
                        x = b*-1;
                        break;
                    default:
                    }
                }
                
                extra.push(x);
                ab = false;
            }
        }
        while (!extra.isEmpty()) {
         res = (double)extra.pop();   
        }
        return res;
    }
   
   /**
     * Este metodo calcula el valor de una expresion matematica
     * Primero verifica la sintaxis de la cadena, 
     * luego la convierte a notacion postfija 
     * y finalmente evalua la expresion.
     *
     * @param cadena La cadena que representa la expresion matematica a calcular.
     * @return El resultado de calcular la expresion matematica.
     * @throws SintaxErr si la cadena tiene un error de sintaxis.
     */
   public static double calcular (String cadena) {
       PilaA<Object>  postfija;
       double res;
       
       if(chequeo(cadena)) {
           cadena = cambioNegs(cadena);
           postfija = cambioPostfija(cadena);
           res = eval(postfija);
       }
       else {
           throw new SintaxErr ("Error de sintaxis, cheque de nuevo");
       }
       return res;
   }
}
