public class TArista {
    
    private TVertice origen;
    private TVertice destino;
    private int costo;

    public TArista(TVertice origen, TVertice destino, int costo) {
        this.origen = origen;
        this.destino = destino;
        this.costo = costo;
    }

    public TVertice getOrigen() {
        return origen;
    }

    public TVertice getDestino() {
        return destino;
    }

    public int getCosto() {
        return costo;
    }

}
