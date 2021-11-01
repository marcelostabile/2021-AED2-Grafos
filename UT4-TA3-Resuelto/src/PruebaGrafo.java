public class PruebaGrafo {

    public static void main(String[] args) {

        // Cargar los archivos de texto de aeropuertos y conexiónes (adyacentes) un grafo dirigido.
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("./src/aeropuertos_1.txt","./src/conexiones_1.txt",
                false, TGrafoDirigido.class);

        // Obtener las etiquetas ordenadas y guardarlas en un arreglo.
        Object[] etiquetasarray = gd.getEtiquetasOrdenado();

        // Crear una matriz de costos a partir de los vertices.
        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());

        // Imprime la matriz de costos.
        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz");

        // *** IMPLEMENTAR TA-03 Floyd
        Double[][] mfloyd = gd.floyd();

        UtilGrafos.imprimirMatrizMejorado(mfloyd, gd.getVertices(), "Matriz luego de FLOYD");

        // *** IMPLEMENTAR TA-03 Excentricidad
        // for (int i = 0; i < etiquetasarray.length; i++) {
        //     System.out.println("excentricidad de " + etiquetasarray[i] + " : " + gd.obtenerExcentricidad((Comparable) etiquetasarray[i]));
        // }

        // *** IMPLEMENTAR TA-03 Centro del grafo.
        // System.out.println();
        // System.out.println("Centro del grafo: " + gd.centroDelGrafo());

    }
}
