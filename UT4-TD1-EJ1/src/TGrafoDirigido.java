import java.util.LinkedList;

public class TGrafoDirigido {
    
    private LinkedList<TVertice> vertices;
    private LinkedList<TArista> aristas;

    public LinkedList<TVertice> getVertices() {
        return vertices;
    }

    public LinkedList<TArista> getAristas() {
        return aristas;
    }

    public void insertarVertice(TVertice vertice) {
        vertices.add(vertice);
    }

    public void insertarArista(TArista arista) {
        aristas.add(arista);
    }

    public void imprimirCamino(TArista[] vectorPrecedentes, TVertice verticeOrigen, TVertice verticeDestino) { 
        boolean imprime = false;
        for (int i = 0; i < vectorPrecedentes.length-1; i++) {
            if (vectorPrecedentes[i].getOrigen() == verticeOrigen) {
                imprime = true;
            }
            if (imprime) {
                System.out.println(vectorPrecedentes[i]);
            }
            if (vectorPrecedentes[i].getDestino() == verticeDestino) {
                imprime = false;
            }
        }
    }

}
