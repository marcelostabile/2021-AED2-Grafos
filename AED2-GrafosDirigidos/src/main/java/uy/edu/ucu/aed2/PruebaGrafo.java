package uy.edu.ucu.aed2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class PruebaGrafo {

    public static void main(String[] args) {

        //   UT4-TA3 - Representación gráfica de relaciones, Floyd.
        System.out.println("UT4-TA3");

        String ruta = "./src/main/java/uy/edu/ucu/aed2/TA3/";
        String aero = ruta + "aeropuertos_1.txt";
        String conn = ruta + "conexiones_1.txt";
        
        // Cargar archivos de texto de aeropuertos y conexiones en el grafo dirigido.
        TGrafoDirigido gdta3 = (TGrafoDirigido) UtilGrafos.cargarGrafo(aero, conn, false, TGrafoDirigido.class);

        // Obtener las etiquetas ordenadas y guardarlas en un arreglo.
        Object[] etiquetasarray = gdta3.getEtiquetasOrdenado();

        // Crear una matriz de costos a partir de los vertices.
        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gdta3.getVertices());

        // Imprime la matriz de costos.
        UtilGrafos.imprimirMatrizMejorado(matriz, gdta3.getVertices(), "Matriz");

        // *** IMPLEMENTAR TA-03 Floyd
        Double[][] mfloyd = gdta3.floyd();

        UtilGrafos.imprimirMatrizMejorado(mfloyd, gdta3.getVertices(), "Matriz FLOYD - Caminos más cortos entre nodos.");

        // *** IMPLEMENTAR TA-03 Excentricidad
        for (int i = 0; i < etiquetasarray.length; i++) {
            System.out.println("excentricidad de " + etiquetasarray[i] + " : " + gdta3.obtenerExcentricidad( (Comparable) etiquetasarray[i]));
        }

        // *** IMPLEMENTAR TA-03 Centro del grafo.
        System.out.println();
        System.out.println("Centro del grafo: " + gdta3.centroDelGrafo());
        

        // *** IMPLEMENTAR TA-03 Búsqueda profundidad.

        // Recorrido para todo el grafo.
        Collection<TVertice> recorrido = gdta3.bpf();

        // imprimir etiquetas del bpf de todo el grafo.
        System.out.println();
        for (TVertice tVertice : recorrido) {
            System.out.println(tVertice.getEtiqueta());
        }

        // Recorrido para la cuidad Asunción
        Collection<TVertice> recorrido_Asuncion = gdta3.bpf("Asuncion");

        // imprimir etiquetas del bpf desde Asunción.
        System.out.println();
        ArrayList<String> salida = new ArrayList<>();
        for (TVertice tVertice : recorrido_Asuncion) {
            salida.add(tVertice.getEtiqueta().toString());
            System.out.println(tVertice.getEtiqueta());
        }
        ManejadorArchivosGenerico.escribirArchivo(ruta + "salida.txt", salida.toArray(new String[0]));

    }
}
