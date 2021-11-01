package ut8.TA2;


/**
 * @author Programacion2
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public class TLista {
	private static final String SEPARADOR_ELEMENTOS_IMPRESOS = "-";
	private TElemento primero = null;
	private int cantidadElementos;
	/**
	 * Constructor por defecto 
	 */
	public TLista () {
		primero = null;
		cantidadElementos = 0;
	}
 
	/**
	 * @return Retorna el Primer elemento de la lista
	 */
	public TElemento obtenerPrimero() {
		return primero;
	}

	/**
	 * @return Elemento colocado al final de la lista
	 */
	public TElemento obtenerUltimo() {
		if (esVacia()) {
			return null;
		}
		TElemento elem = primero;
		while (elem.getSiguiente() != null) {
			elem = elem.getSiguiente();
		}
		return elem;
	}
	
	/**
	 * Metodo encargado de insertar un elemento en la lista
	 * 
	 * @param unElemento El elemento que queremos insertar
	 * @return true si se pudo insertar
	 */
	public boolean insertarAlPrincipio (TElemento unElemento){
		if (unElemento != null) {
			unElemento.setSiguiente(primero);
			primero = unElemento;
			cantidadElementos++;
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo encargado de insertar un elemento al final de la lista.
	 * 
	 * @param unElemento El elemento que queremos insertar
	 * @return true si se pudo insertar
	 */
	public boolean insertarAlFinal (TElemento unElemento) {
		if (unElemento != null) {
			if (esVacia()) {
				primero = unElemento;
			} else {
				TElemento auxiliar = obtenerUltimo();
				auxiliar.setSiguiente(unElemento);
			}
			cantidadElementos++;
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo encargado de indicar si la lista no contiene elementos.-
	 * 
	 * @return true si la lista no tiene elementos
	 */
	public boolean esVacia () {
		return (primero == null);
	}
	
	/**
	 * @param unaEtiqueta Etiqueta del elemento a localizar
	 * @return el elemento a localizar
	 */
	@SuppressWarnings("unchecked")
	public TElemento buscar(Comparable unaEtiqueta) {
		TElemento elem = primero;
		while (elem != null){
			if (elem.getEtiqueta().compareTo(unaEtiqueta) == 0) {
				return elem;
			}
			elem = elem.getSiguiente();
		}
		return null;
	}
	
	/**
	 * @param unaEtiqueta Etiqueta del elemento a eliminar
	 * @return true sii se pudo eliminar el elemento
	 */
	@SuppressWarnings("unchecked")
	public TElemento eliminar(Comparable unaEtiqueta) {
		if (esVacia() || (unaEtiqueta == null)){
			return null;
		} else {
			if (primero.getEtiqueta().compareTo(unaEtiqueta)==0){
				TElemento elementoADevolver = primero;
				primero = primero.getSiguiente();
				cantidadElementos--;
				return elementoADevolver;
			} else {
				return eliminarInterno(primero.getSiguiente(),primero,unaEtiqueta);
			}
		}
	}
	
	@SuppressWarnings({ "unchecked" })	
	private TElemento eliminarInterno(TElemento elementoActual, TElemento anterior, Comparable etiquetaAEliminar) {
		if (elementoActual==null){
			return null;
		} else {
			if (elementoActual.getEtiqueta().compareTo(etiquetaAEliminar)==0){ //lo encontre, elimino
				anterior.setSiguiente(elementoActual.getSiguiente());
				cantidadElementos--;
				return anterior;
			} else {
				return eliminarInterno(elementoActual.getSiguiente(),elementoActual,etiquetaAEliminar);
			}
		}
	}
	
	/**
	 * @return Cantidad de elementos de la lista
	 */
	public int obtenerCantidadElementos() {
		return cantidadElementos;
	}

	/**
	 * @param unElemento El elemento que queremos insertar 
	 * @return true sii se pudo insertar 
	 */
	@SuppressWarnings("unchecked")
	
	/**
	 * 
	 */
	public void vaciar() {
		primero = null;
		cantidadElementos = 0;
	}
	
	/**
	 * Invierte el contenido de la lista
	 */
		
	/**
	 * @param unaEtiqueta Etiqueta del elemento cuyo anterior queremos localizar
	 * @return Elemento anterior
	 */
	public TElemento anterior(Comparable unaEtiqueta) {
		TElemento anterior = null;
		TElemento actual = primero;
		while(actual != null){
			if (actual.getEtiqueta().equals(unaEtiqueta)){
				break;
			}
			anterior = actual;
			actual = actual.getSiguiente();
		}
		return anterior;
	}
	
	
	/**
	 * @return las etiquetas de la lista concatenadas, separadas por el separador definido
	 */
	public Comparable[] etiquetasToArray() {
		Comparable[] salida = new Comparable[obtenerCantidadElementos()];
		TElemento elementoActual = primero;
		int posicionActual = 0;
		while(elementoActual != null){
			salida[posicionActual] = elementoActual.getEtiqueta();
			elementoActual = elementoActual.getSiguiente();
			posicionActual++;
		}
		return salida;
	}
	
	public String imprimirEtiquetas() {
		if (esVacia()) {
			return null;
		}
		StringBuilder salida = new StringBuilder();
		TElemento elementoActual = primero;
		while(elementoActual != null){
			if (salida.length() > 0){
				salida.append(SEPARADOR_ELEMENTOS_IMPRESOS);
			}
			salida.append(elementoActual.getEtiqueta());
			elementoActual = elementoActual.getSiguiente();
		}
		return salida.toString();
	}

	public String toString(){
		return imprimirEtiquetas();
	}
	
	

	public TElemento quitarPrimero() {
		TElemento elem = primero;
		if (elem != null){
			primero = elem.getSiguiente();
		}
		return elem;
	}
}
