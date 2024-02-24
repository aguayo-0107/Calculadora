package proyectoed;

/**
 *Clase con una excepcion que se extiende de RuntimeException,
 * Maneja errores de sintaxis en expresiones matematicas.
 * @author Fernanda Leon, Mariana Aguayo, Marcio Tellez, Silvestre Rosales, Natalia Wilson
 */


public class SintaxErr extends RuntimeException{
    /**
     * El constructor de la clase, Crea una nueva excepción de error de sintaxis
     * con un mensaje detallado.
     *
     * @param msg El mensaje.
     */
    public SintaxErr(String msg) {
        super(msg);
    }
}
