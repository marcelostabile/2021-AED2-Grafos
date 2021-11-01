package ut8.TA2;


public class TAdyacencia extends TElemento{
	// hereda etiqueta, en la cual se pondrá la etiqueta del vertice destino
	private int costo;
	private TVertice destino;

	public int getCosto() {
		return costo;
	}

	public TVertice getDestino() {
		return destino;
	}
	
	public TAdyacencia(int costo, TVertice destino) {
	  super (destino.getEtiqueta());
	  this.costo = costo;
		this.destino = destino;
	}
}
