package ut8.TA2;



/**
 * Breve descripcion de lo que estamos desarrollando.
 * 
 * @author Nro. de Grupo 
 * @version 0.0 
 *  
 */

public class TGrafoDirigido {
	public TLista vertices; //lista de v�rtices del grafo.-

	/**
	 * Constructor del grafo.-
	 */
	public TGrafoDirigido() {
		vertices = new TLista();
	}

	/**
	 * M�todo encargado de eliminar una adyacencia dada por un origen y destino.
	 * En caso de no existir la adyacencia, retorna falso.
	 * En caso de que las etiquetas sean inv�lidas, retorna falso. 
	 * 
	 */
	public boolean eliminarAdyacencia(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
		if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
			TVertice vertOrigen = buscarVertice(nomVerticeOrigen);
			if (vertOrigen != null) {
				return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
			}
		}
		return false;
	}

	/**
	 * M�todo encargado de eliminar un v�rtice en el grafo.
	 * En caso de no existir el v�rtice, retorna falso.
	 * En caso de que la etiqueta sea inv�lida, retorna false. 
	 * 
	 */
	public boolean eliminarVertice(Comparable nombreVertice) {
		if (nombreVertice != null) {
			return vertices.eliminar(nombreVertice) != null;
		}
		return false;
	}

	/**
	 * M�todo encargado de verificar la existencia de una adyacencia.
	 * Las etiquetas pasadas por par�metro deben ser v�lidas.
	 * 
	 * @return True si existe la adyacencia, false en caso contrario
	 */
	public boolean existeAdyacencia(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
		TVertice vertOrigen = buscarVertice(etiquetaOrigen);
		TVertice vertDestino = buscarVertice(etiquetaDestino);
		if ((vertOrigen != null) && (vertDestino != null)) {
			return vertOrigen.buscarAdyacencia(vertDestino) != null;
		}
		return false;
	}

	/**
	 * M�todo encargado de verificar la existencia de un v�rtice dentro del grafo.-
	 * 
	 * La etiqueta especificada como par�metro debe ser v�lida.
	 * 
	 * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
	 * @return True si existe el vertice con la etiqueta indicada, false en caso contrario
	 */
	public boolean existeVertice(Comparable unaEtiqueta) {
		return vertices.buscar(unaEtiqueta) != null;
	}

	/**
	 * M�todo encargado de verificar buscar un v�rtice dentro del grafo.-
	 * 
	 * La etiqueta especificada como par�metro debe ser v�lida.
	 * 
	 * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
	 * @return El vertice encontrado. En caso de no existir, retorna nulo.
	 */
	private TVertice buscarVertice(Comparable unaEtiqueta) {
		if (unaEtiqueta != null) {
			TElemento elem = vertices.buscar(unaEtiqueta);
			if (elem != null) {
				return (TVertice) elem;
			}
		}
		return null;
	}
	
	/**
	 * M�todo encargado de insertar una adyacencia en el grafo (con un cierto costo), dado su vertice origen y destino.-
	 * Para que la adyacencia sea v�lida, se deben cumplir los siguientes casos:
	 * 1) Las etiquetas pasadas por par�metros son v�lidas.-
	 * 2) Los v�rtices (origen y destino) existen dentro del grafo.-
	 * 3) No es posible ingresar una adyacencia ya existente (miso origen y mismo destino, aunque el costo sea diferente).-
	 * 4) El costo debe ser mayor que 0. 
	 * 
	 * @return True si se pudo insertar la adyacencia, false en caso contrario
	 */
	public boolean insertarAdyacencia(Comparable etiquetaOrigen, Comparable etiquetaDestino, Comparable costo) {
		if ((etiquetaOrigen != null) && (etiquetaDestino != null) && (costo != null)) {
			TVertice vertOrigen = buscarVertice(etiquetaOrigen);
			TVertice vertDestino = buscarVertice(etiquetaDestino);
			if ((vertOrigen != null) && (vertDestino != null)) {
				return vertOrigen.insertarAdyacencia(costo, vertDestino);
			}
		}
		return false;
	}

	/**
	 * M�todo encargado de insertar un v�rtice en el grafo.
	 *  
	 * No pueden ingresarse v�rtices con la misma etiqueta.
	 * La etiqueta especificada como par�metro debe ser v�lida. 
	 *  
	 * @param unaEtiqueta Etiqueta del v�rtice a ingresar.
	 * @return True si se pudo insertar el vertice, false en caso contrario
	 */
	public boolean insertarVertice(Comparable unaEtiqueta) {
		if ((unaEtiqueta != null) && (! existeVertice(unaEtiqueta))) {
			TVertice vert = new TVertice(unaEtiqueta);
			return vertices.insertarAlFinal(vert);
		}
		return false;
	}

	
}
