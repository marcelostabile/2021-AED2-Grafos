public class TAdyacencia {
    
    private TVertice destino;
    private int costo;

    public TAdyacencia(TVertice destino, int costo) {
        this.destino = destino;
        this.costo = costo;
    }

    public TVertice getDestino() {
        return destino;
    }

    public int getCosto() {
        return costo;
    }

}
