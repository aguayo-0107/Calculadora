package Proyecto;
/* Mariana Aguayo
 *
 * @author magua
 */

public class PilaA <T> implements PilaADT <T> {
    private T[] pila;
    private int tope;
    private final int MAX = 100;
    
    @SuppressWarnings("unchecked")
    public PilaA() {
        pila = (T[])new Object[MAX];
        tope = -1;
    }
    @SuppressWarnings("unchecked")
    public PilaA (int max) {
        pila = (T[]) new Object [max];
        tope = -1;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void push(Object dato) {
        if (tope+1 == pila.length) {
            expande();
        }
        tope++;
        pila[tope] = (T)dato;
    }
    public void expande() {
        @SuppressWarnings("unchecked")
        T[] masGrande = (T[]) new Object [pila.length*2];
        
        for (int i=0; 1<pila.length; i++) {
            masGrande[i] = pila[i];
        }
        pila = masGrande;
    }
    @Override
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
    @Override
    public boolean isEmpty() {
        return tope == -1;
    }
    @Override
    public T peek() {
        if (this.isEmpty()){
            throw new ExcepcionColeccionVacia("La pila esta vacia");
        }
        return pila[tope];
    }
    @Override
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
