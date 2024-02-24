package proyectoed;

/**
 *Interface que define los metodos que toda pila generica debe de tener
 * @author Fernanda Leon, Mariana Aguayo, Marcio Tellez, Silvestre Rosales, Natalia Wilson
 * @param <T> Los elementos genericos que se almacenaran en la pila.
 */
public interface PilaADT <T> {
    public void push (T dato);
    public T pop();
    public boolean isEmpty();
    public T peek();
}
