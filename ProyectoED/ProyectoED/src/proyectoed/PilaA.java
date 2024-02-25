package proyectoed;

/**
 *Clase que contiene metodos que modifican a una pila generica
 * @author Fernanda Leon, Mariana Aguayo, Marcio Tellez, Silvestre Rosales, Natalia Wilson
 */
public class PilaA <T> implements PilaADT <T> {
    private T[] pila;
    private int tope;
    private final int MAX = 100;
    
    /**
     * Este constructor inicializa una nueva pila 
     * con una capacidad maxima predeterminada.
     * 
     * @throws ClassCastException si el objeto no es del tipo esperado.
     */
    @SuppressWarnings("unchecked")
    public PilaA() {
        pila = (T[])new Object[MAX];
        tope = -1;
    }
    
    /**
     * Este constructor inicializa una nueva pila con una capacidad maxima.
     * 
     * @param max La capacidad maxima de la pila.
     * @throws ClassCastException si el objeto no es del tipo esperado.
     */
    @SuppressWarnings("unchecked")
    public PilaA (int max) {
        pila = (T[]) new Object [max];
        tope = -1;
    }

    /**
     * Este metodo agrega un elemento a la parte superior de la pila.
     * 
     * @param dato El elemento que se va a agregar a la pila
     * @throws ClassCastException si el objeto no es del tipo esperado.
     */
    @SuppressWarnings("unchecked")
    public void push(Object dato) {
        if (tope+1 == pila.length) {
            expande();
        }
        tope++;
        pila[tope] = (T)dato;
    }
    
    /**
     * Este metodo expande la capacidad de la pila 
     * al doble de su tamaño original.
     */
    public void expande() {
        @SuppressWarnings("unchecked")
        T[] masGrande = (T[]) new Object [pila.length*2];
        
        for (int i=0; 1<pila.length; i++) {
            masGrande[i] = pila[i];
        }
        pila = masGrande;
    }

    /**
     * Este metodo elimina y devuelve el elemento en la parte superior de la pila.
     * 
     * @return El elemento que fue eliminado de la parte superior de la pila.
     * @throws ExcepcionColeccionVacia si la pila esta vacia.
     */
    public T pop() {
        T eliminado;
        
        if (this.isEmpty()) {
            throw new ExcepcionColeccionVacia("La pila esta vacia");
        }
        eliminado = pila[tope];
        pila[tope] = null;
        tope--;
        return eliminado;
        
    }

    /**
     * Este metodo verifica si la pila esta vacia.
     * 
     * @return true si la pila esta vacia, false en caso contrario.
     */
    public boolean isEmpty() {
        return tope == -1;
    }

    /**
     * Este metodo devuelve el elemento que se encuentra hasta el final
     * de la pila, sin eliminarlo.
     * 
     * @return El elemento en la parte superior de la pila.
     * @throws ExcepcionColeccionVacia si la pila esta vacia.
     */
    public T peek() {
        if (this.isEmpty()){
            throw new ExcepcionColeccionVacia("La pila esta vacia");
        }
        return pila[tope];
    }
    
    /**
     * Este metodo convierte la pila a una cadena
     * 
     * @return Una cadena que representa la pila desde el indice 0 hasta el tope.
     */
    public String toString() {
        StringBuilder res = new StringBuilder ("Pila de 0 a tope\n");
        
        for (int i=0; i<= tope; i++) {
            res.append(pila[i] + ", ");
        }
        return res.toString();
    }
    
      public boolean equals(PilaA<Object> otro){
         boolean ans=true;
         PilaA<Object> aux1= new PilaA<>();
         PilaA<Object> aux2=new PilaA<>();
         while(!this.isEmpty() && !otro.isEmpty()){
             aux1.push(this.pop());
             aux2.push(otro.pop());
             ans&= (aux1.peek()).equals(aux2.peek());
         }
         while(!aux1.isEmpty() && !aux2.isEmpty()){
             this.push(aux1.pop());
             otro.push(aux2.pop());
         }
         return ans;
     }
}
