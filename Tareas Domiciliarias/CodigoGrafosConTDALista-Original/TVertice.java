package ut8.TA2;


public class TVertice extends TElemento{
		private TLista adyacentes;

	public Comparable getEtiqueta() {
		return etiqueta;
	}

	public TLista getAdyacentes() {
		return adyacentes;
	}

	public TVertice (Comparable unaEtiqueta) {
		super (unaEtiqueta);
		adyacentes = new TLista();
	}
	
	public TAdyacencia buscarAdyacencia(TVertice verticeDestino) {
	// a desarrollar
		return null;
	}
	
	public Integer obtenerCostoAdyacencia(TVertice verticeDestino) {
		TAdyacencia ady = buscarAdyacencia(verticeDestino);
		if (ady != null) {
			return (Integer)ady.getCosto();
		}
		return -1;
	}
	
	public boolean insertarAdyacencia(Comparable costo, TVertice verticeDestino) {
	// a desarrollar
		return false;
	}

	public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
		return adyacentes.eliminar(nomVerticeDestino) != null;
	}
	
}
