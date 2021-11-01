package uy.edu.ucu.aed2;

import java.util.Collection;

public class TCamino {

    public TVertice origen;
    public Collection<Comparable> otrosVertices; // ATENCIÓN: PONER LA CLASE CONCRETA QUE SEA MÁS APROPIADA
    private Double costoTotal;

    // Constructor
    public TCamino(TVertice v){
        this.origen = v;
        this.otrosVertices = new Collection<Comparable>(); // ATENCIÓN: PONER LA CLASE CONCRETA QUE SEA MÁS APROPIADA
        this.costoTotal = 0.0;
    }

    /**
     * 
     */
    //private void ImprimirEtiquetas(){ }; // IMPLEMENTAR

    /**
     * 
     * @return un string.
     */
    //private String ImprimirEtiquetas();// IMPLEMENTAR

    /**
     * Agregar adyacencia actual en el camino.
     * @param adyacenciaActual
     * @return true o false.
     */
    public boolean agregarAdyacencia(TAdyacencia adyacenciaActual) {
        if (adyacenciaActual.getDestino() != null) {
            costoTotal = costoTotal + ((Number)adyacenciaActual.getCosto()).doubleValue();
            return otrosVertices.add(adyacenciaActual.getDestino().getEtiqueta());
        }
        return false;
    }

    /**
     * eliminar una adyacencia dada por parámetro.
     * @param adyacenciaActual
     * @return true o false.
     */
    public boolean eliminarAdyacencia(TAdyacencia adyacenciaActual) {
        if (otrosVertices.contains(adyacenciaActual.getDestino().getEtiqueta())) {
            costoTotal = costoTotal - ((Number)adyacenciaActual.getCosto()).doubleValue();
            return (otrosVertices.remove(adyacenciaActual.getDestino().getEtiqueta()));
        }
        return false;
    }

    public TCamino copiar(){

        TVertice origen = new TVertice(this.getOrigen().getEtiqueta());
        TCamino copia = new TCamino(origen);
        origen.getAdyacentes().addAll(this.getOrigen().getAdyacentes());
        copia.getOtrosVertices().addAll(this.getOtrosVertices());
        return copia;
    }
}
