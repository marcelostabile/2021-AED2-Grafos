package uy.edu.ucu.aed2;

import java.util.Collection;
import java.util.LinkedList;

public class TVertice<T>  implements IVertice{

    private Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private T datos;


    public Comparable getEtiqueta() {
        return etiqueta;
    }


    @Override
    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }


    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
    }


    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }


    public boolean getVisitado() {
        return this.visitado;
    }


    @Override
    public TAdyacencia buscarAdyacencia(TVertice verticeDestino) {
        if (verticeDestino != null) {
            return buscarAdyacencia(verticeDestino.getEtiqueta());
        }
        return null;
    }


    /**
     * Obtener el costo de adyacencia de un vertice para otro vertice destino.
     * Si el vertice no tiene conexión con el vertice destino, retorna MAX_VALUE.
     */
    public Double obtenerCostoAdyacencia(TVertice verticeDestino) {
        TAdyacencia ady = buscarAdyacencia(verticeDestino);
        if (ady != null) {
            return ady.getCosto();
        }
        return Double.MAX_VALUE;
    }


    @Override
    public boolean insertarAdyacencia(Double costo, TVertice verticeDestino) {
        if (buscarAdyacencia(verticeDestino) == null) {
            TAdyacencia ady = new TAdyacencia(costo, verticeDestino);
            return adyacentes.add(ady);
        }
        return false;
    }


    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        TAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
        if (ady != null) {
            adyacentes.remove(ady);
            return true;
        }
        return false;
    }


    /**
     * Busca en la lista de adyacencias del vertice y si existe retorna un objeto de tipo TAdyacencia.
     */
    @Override
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (TAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0) {
                return adyacencia;
            }
        }
        return null;
    }
    

    @Override
    public TVertice primerAdyacente() {
        if (this.adyacentes.getFirst() != null) {
            return this.adyacentes.getFirst().getDestino();
        }
        return null;
    }


    @Override
    public TVertice siguienteAdyacente(TVertice w) {
        TAdyacencia adyacente = buscarAdyacencia(w.getEtiqueta());
        int index = adyacentes.indexOf(adyacente);
        if (index + 1 < adyacentes.size()) {
            return adyacentes.get(index + 1).getDestino();
        }
        return null;
    }


    public T getDatos() {
        return datos; 
    }


    /**
     * Visita el vértice, lo marca como visitado y continua recursivamente por sus adyacentes.
     * @param Collection<TVertice> visitados
     */
    @Override
    public void bpf(Collection<TVertice> visitados) {
        // Marcar el vértice visitado.
        this.setVisitado(true);
        // Agregar a la colección de visitados este vértice.
        visitados.add(this);
        // Recorrer recursivamente los vértices adyacentes.
        for (TAdyacencia adyacentes : this.adyacentes){
            TVertice destino  = adyacentes.getDestino();
            // Si el vértice destino no está visitado, recursivamente ejecutamos la BPF.
            if(!destino.getVisitado()){
                destino.bpf(visitados);
            }
        }
    }

}