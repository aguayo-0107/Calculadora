package Proyecto;
/**
 *
 * Calculadora, devuelve un string con la respuesta o error
 */
public class Calculadora_String {
    public static void invertir (PilaADT pila) {
        PilaA extra = new PilaA();
        PilaA extra2 = new PilaA();
        
        while(!pila.isEmpty()) {
            extra.push(pila.pop());
        }
        while(!extra.isEmpty()) {
            extra2.push(extra.pop());
        }
        while(!extra2.isEmpty()) {
            pila.push(extra2.pop());
        }
    }
    
    public static boolean chequeo (String cadena) {
        PilaA chequeo = new PilaA();
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
                    if (i < cadena.length()-1) { 
                        if (cadena.charAt(i+1) == '+' || cadena.charAt(i+1) == '*' || cadena.charAt(i+1) == '/' || cadena.charAt(i+1) == '^') {
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
    
    public static String cambioNegs (String cadena) {
        String [] charas;
        StringBuilder cad = new StringBuilder();
        
        //se separa la cadena en un arreglo de String
        charas = cadena.split("||");
        for (int i=0; i<charas.length; i++) {
            //si hay un negativo como primer caracter, entonces lo siguiente sera negativo
            if (charas[i].equals("-")) {
                if (i == 0) {
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
    
    //jerarquia de operaciones
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
   
    public static PilaA cambioPostfija (String cadena) {
        PilaA postfija = new PilaA();
        PilaA pilaOperadores = new PilaA();
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
   
   public static String eval (PilaA postfija) {
        PilaA extra = new PilaA();
        boolean ab = false;
        char oper;
        double b, a=0, x=0;
        String res;
        
        res = "";
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
                        if (b != 0) {
                            x = a/b;
                        }
                        else {
                            res = "ERROR DIV BY 0";
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
        while (!extra.isEmpty() && res.isBlank()) {
         res = extra.pop().toString();   
        }
        return res;
    }
   public static String calcular (String cadena) {
       PilaA postfija;
       String res;
       
       if(chequeo(cadena)) {
           cadena = cambioNegs(cadena);
           postfija = cambioPostfija(cadena);
           res = eval(postfija);
       }
       else {
           res = "SINTAX ERR";
       }
       return res;
   }
}

