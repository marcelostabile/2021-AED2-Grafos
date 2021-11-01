package ut8.TA2;



/**
 * @author Programacion2
 *
 */
@SuppressWarnings("rawtypes")
public class TElemento {
	protected Comparable etiqueta;
	protected TElemento siguiente;
	

	
	public Comparable getEtiqueta() {
		return etiqueta;
	}
	
	public TElemento getSiguiente() {
		return siguiente;
	}
	
	public void setSiguiente(TElemento siguiente) {
		this.siguiente = siguiente;
	}
	
	public TElemento (Comparable unaEtiqueta) {
		etiqueta = unaEtiqueta;
		
	}
}
