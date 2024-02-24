package proyectoed;

/**
 *Clase que representa una excepcion que aparece cuando hay una coleccion vacia
 * @author Fernanda Leon, Mariana Aguayo, Marcio Tellez, Silvestre Rosales, Natalia Wilson
 */

 
public class ExcepcionColeccionVacia extends RuntimeException {
    /**
     * Este constructor inicializa una nueva excepcion con un mensaje
     * especifico.
     *
     * @param message El mensaje de la excepcion. No debe ser null.
     */
    public ExcepcionColeccionVacia (String message) {
        super(message);
    }
}
