package uy.edu.ucu.aed2;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Ernesto
 */
public class TGrafoDirigido implements IGrafoDirigido {

    private final Map<Comparable, TVertice> vertices; // vertices del grafo.-

    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        for (TVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (TArista arista : aristas) {
            insertarArista(arista);
        }
    }

    /**
     * Metodo encargado de eliminar una arista dada por un origen y destino.
     * En caso de no existir la adyacencia, retorna falso. En caso de que las
     * etiquetas sean inv�lidas, retorna falso.
     *
     * @param nomVerticeOrigen
     * @param nomVerticeDestino
     * @return 
     */
    @Override
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            TVertice vertOrigen = buscarVertice(nomVerticeOrigen);
            if (vertOrigen != null) {
                return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
            }
        }
        return false;
    }

    /**
     * Metodo encargado de eliminar un vertice en el grafo. En caso de no
     * existir el v�rtice, retorna falso. En caso de que la etiqueta sea
     * inv�lida, retorna false.
     *
     * @param nombreVertice
     * @return 
     */
    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo encargado de verificar la existencia de una arista. Las
     * etiquetas pasadas por par�metro deben ser v�lidas.
     *
     * @param etiquetaOrigen
     * @param etiquetaDestino
     * @return True si existe la adyacencia, false en caso contrario
     */
    @Override
    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de un vertice dentro del
     * grafo.-
     *
     * La etiqueta especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
     * @return True si existe el vertice con la etiqueta indicada, false en caso
     * contrario
     */
    @Override
    public boolean existeVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }

    /**
     * Metodo encargado de verificar buscar un vertice dentro del grafo.-
     *
     * La etiqueta especificada como parametro debe ser valida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
     * @return El vertice encontrado. En caso de no existir, retorna nulo.
     */
    private TVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    /**
     * Metodo encargado de insertar una arista en el grafo (con un cierto costo), dado su vertice origen y destino.- 
     * Para que la arista sea valida, se deben cumplir los siguientes casos: 
     * 1) Las etiquetas pasadas por parametros son v�lidas.- 
     * 2) Los vertices (origen y destino) existen dentro del grafo.- 
     * 3) No es posible ingresar una arista ya existente (mismo origen y mismo destino, aunque el costo sea diferente).- 
     * 4) El costo debe ser mayor que 0.
     * @param arista
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    @Override
    public boolean insertarArista(TArista arista) {
        if ((arista.getEtiquetaOrigen()!= null) && (arista.getEtiquetaDestino() != null)) {
            TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            if ((vertOrigen != null) && (vertDestino != null)) {
                return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino);
            }
        }
        return false;
    }
 
    /**
     * Metodo encargado de insertar un vertice en el grafo.
     *
     * No pueden ingresarse v�rtices con la misma etiqueta. La etiqueta
     * especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a ingresar.
     * @return True si se pudo insertar el vertice, false en caso contrario
     */
    @Override
    public boolean insertarVertice(Comparable unaEtiqueta) {
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            TVertice vert = new TVertice(unaEtiqueta);
            getVertices().put(unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }


    @Override
    public boolean insertarVertice(TVertice vertice) {

        Comparable unaEtiqueta = vertice.getEtiqueta();
        if (!existeVertice(unaEtiqueta)) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }


    /**
     * Devolver las etiquetas de los vertices ordenadas.
     * @return arreglo con etiquetas de vertices ordenadas.
     */
    public Object[] getEtiquetasOrdenado() {

        // Se crea un TreeMap a partir del Map vertices del grafo.
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        // Retornamos un arreglo de claves (etiquetas) ordenadas.
        return mapOrdenado.keySet().toArray();
    }


    /**
     * Retornamos el Map de vertices del grafo.
     * @return the vertices
     */
    public Map<Comparable, TVertice> getVertices() {

        return vertices;
    }


    @Override
    public Comparable centroDelGrafo() {

        // Obtener etiquetas de los vertices y matriz Floyd.
        Object[] eFloyd = this.getVertices().keySet().toArray();
        Double[][] mFloyd = this.floyd();
        // Excentricidad de los vértices.
        Double[][] excentricidad = new Double[eFloyd.length][2];
        for (int i = 0; i < eFloyd.length; i++) {                       // fila.
            excentricidad[i][1] = 0.00;
            for (int j = 0; j < eFloyd.length; j++) {                   // Columna.
                if (mFloyd[i][j] > (double) excentricidad[i][1]) {
                    excentricidad[i][0] = Double.valueOf(j);
                    excentricidad[i][1] = mFloyd[i][j];
                }
            }
        }
        // Centro del grafo.
        String destino = "";                                // opcional.
        Double centro = excentricidad[0][1];
        for (int i = 0; i < eFloyd.length; i++) {
            if (centro > excentricidad[i][1]) {
                destino = eFloyd[excentricidad[i][0].intValue()].toString();   // opcional.
                centro = excentricidad[i][1];                
            }
        }
        System.out.println("Ubicación del centro: " + destino);        // opcional.
        return (Comparable) centro;
    }


    /**
     * Floyd 
     * UT4-TA3
     */
    @Override
    public Double[][] floyd() {

        Double[][] matrizCostos = UtilGrafos.obtenerMatrizCostos(this.getVertices());
        int i, j, k;
        for (k = 0; k < matrizCostos.length; k++) {
            for (i = 0; i < matrizCostos.length; i++) {
                for (j = 0; j < matrizCostos.length; j++) {
                    if (matrizCostos[i][k] + matrizCostos[k][j] < matrizCostos[i][j]) {
                        matrizCostos[i][j] = matrizCostos[i][k] + matrizCostos[k][j];
                    }
                }
            }
        }
        return matrizCostos;
    }


    /**
     * Obtener la excentricidad de un vértice a partir de una etiqueta dada.
     * @param etiquetaVertice
     */
    @Override
    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {

        // Verificamos la existencia de la etiqueta solicitada.
        if (!this.vertices.containsKey(etiquetaVertice)) {
            return "NO EXISTE " + etiquetaVertice;
        }
        // obtener etiquetas de los vértices y determinar posicion en la matriz Floyd.
        Object[] eFloyd = this.getVertices().keySet().toArray();
        int pos = 0;
        for (int i = 0; i < eFloyd.length; i++) {
            if (eFloyd[i] == etiquetaVertice) {
                pos = i;
            }
        }
        // Matriz Floyd y Resultado: la máxima longitud de los Caminos más cortos.
        Double[][] matrizFloyd = this.floyd();
        Comparable resultado = matrizFloyd[pos][pos];
        String destinoResultado = "";                               // opcional.
        for (int i = 0; i < eFloyd.length; i++) {
            if (matrizFloyd[pos][i] > (Double) resultado) {
                resultado = (Comparable) matrizFloyd[pos][i];
                destinoResultado = eFloyd[i].toString();            // opcional.
            }
        }
        // Retornamos el valor resultado.
        System.out.println(destinoResultado + " es");               // opcional.
        return resultado;
    }


    @Override
    public boolean[][] warshall() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    /**
     * Devuelve una colección de vértices visitados, para todos los vértices del grafo.
     * @return Collection<TVertice>
     */
    @Override
    public Collection<TVertice> bpf() {

        // Desvisitar los vértices.
        desvisitarVertices();
        // Lista de vértices visitados.
        LinkedList<TVertice> visitados = new LinkedList<>();
        // Hacemos BPF en todos los vértices del grafo y retornamos una lista de visitados.
        for(TVertice vertice : vertices.values()){
            if(!vertice.getVisitado()){
                vertice.bpf(visitados);
            }
        }
        return visitados;
    }


    /**
     * Devuelve una colección de vértices visitados por BPF a partir de una etiqueta dada.
     * @param etiquetaOrigen 
     * @return Collection<TVertice>
     */
    @Override
    public Collection<TVertice> bpf(Comparable etiquetaOrigen) {

        // Desvisitar los vértices.
        desvisitarVertices();
        // Lista de vértices visitados.
        LinkedList<TVertice> visitados = new LinkedList<>();
        // Obtenemos a partir de la etiqueta dada.
        TVertice verticeAux = this.vertices.get(etiquetaOrigen);
        // Hacemos BPF y retornamos la lista de visitados.
        verticeAux.bpf(visitados);
        return visitados;
    }


    /**
     * Devuelve una colección de vértices visitados por BPF a partir de un vértice dado.
     * @param vertice 
     * @return Collection<TVertice>
     */
    @Override
    public Collection<TVertice> bpf(TVertice vertice) {

        // Desvisitar los vértices.
        desvisitarVertices();
        // Lista de vértices visitados.
        LinkedList<TVertice> visitados = new LinkedList<>();
        // Obtenemos el vértice a partir de la etiqueta del vértice dado.
        TVertice verticeAux = this.vertices.get(vertice.getEtiqueta());
        // Hacemos BPF y retornamos la lista de visitados.
        verticeAux.bpf(visitados);
        return visitados;
    }


    /**
     * Desvisitar los vértices del grafo.
     */
    @Override
    public void desvisitarVertices() {

        for(TVertice verticeAux : vertices.values()){
            verticeAux.setVisitado(false);
        }
    }

}