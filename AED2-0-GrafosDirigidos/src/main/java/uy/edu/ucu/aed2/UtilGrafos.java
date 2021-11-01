package uy.edu.ucu.aed2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtilGrafos {

    /**
     * Devuelve una matriz de costos.
     * @param vertices
     * @return
     */
    public static Double[][] obtenerMatrizCostos(Map<Comparable, TVertice> vertices) {
        
        // crear matriz a partir de la cantidad de vertices.
        int cantidadVertices = vertices.size();
        Double[][] matrizCostos = new Double[cantidadVertices][cantidadVertices];

        // Se inicializa la matriz de costos, con ceros para el mismo vertice o con MAX_VALUE el resto de la matriz.
        // Luego estos valores serán sustituidos con los costos de los vertices en los casos que correspondan.
        for (int i = 0; i < matrizCostos.length; i++) {
            for (int j = 0; j < matrizCostos.length; j++) {
                if (i == j) {
                    // Inicializando: Si coincide i con j el costo es cero (caso es el mismo vertice).
                    matrizCostos[i][j] = 0.0;
                } else {
                    // Inicializando: Si no se trata del mismo vertice, se inicializa con valor MAX_VALUE.
                    matrizCostos[i][j] = Double.MAX_VALUE;
                }
            }
        }

        int i = 0;

        // Almacenamos las etiquetas de los vertices en dos arreglos para I y para J.
        Set<Comparable> etiquetasVertices = vertices.keySet();
        Object[] VerticesIArr = etiquetasVertices.toArray();
        Object[] VerticesJArr = etiquetasVertices.toArray();

        // Llenar la matriz con costos reales.
        while (i < cantidadVertices) {
            int j = 0;
            while (j < cantidadVertices) {
                // Obtener los vertices i y j.
                TVertice elemVerticeI = vertices.get(VerticesIArr[i]);
                TVertice elemVerticeJ = vertices.get(VerticesJArr[j]);
                // Se controla que no se trata del mismo vertice.
                if (!elemVerticeI.getEtiqueta().equals(elemVerticeJ.getEtiqueta())) {
                    // Obtener el costo de adyacencia y almacenarla en la matriz para los vertices i y j.
                    TVertice verticeI = (TVertice) elemVerticeI;
                    TVertice verticeJ = (TVertice) elemVerticeJ;
                    Double costoAdyacencia = verticeI.obtenerCostoAdyacencia(verticeJ);
                    matrizCostos[i][j] = costoAdyacencia;
                }
                j++;
            }
            i++;
        }
        // retornar la matriz.
        return matrizCostos;
    }

    public static void imprimirMatriz(Comparable[][] matriz, Map<Comparable, TVertice> vertices) {
        Object[] etiquetas = vertices.keySet().toArray();
        System.out.print("  ");
        for (int i = 0; i < matriz.length; i++) {
            System.out.print(etiquetas[i] + "  ");
        }
        System.out.println();
        for (int i = 0; i < matriz.length; i++) {
            System.out.print(etiquetas[i] + " ");
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j].compareTo(Double.MAX_VALUE) == 0) {
                    System.out.print(" INF ");
                } else {
                    System.out.print(matriz[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    
    public static void imprimirMatrizCsv(Comparable[][] matriz, Map<Comparable, TVertice> vertices) {
        Object[] etiquetas = vertices.keySet().toArray();
        System.out.print("Vertice/Vertice,");
        for (int i = 0; i < matriz.length; i++) {
            System.out.print(etiquetas[i]);
            if (i != matriz.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println();
        for (int i = 0; i < matriz.length; i++) {
            System.out.print(etiquetas[i] + ", ");
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j].compareTo(Double.MAX_VALUE) == 0) {
                    System.out.print(" INF ");
                } else {
                    System.out.print(matriz[i][j]);
                }
                if (j != matriz.length - 1) {
                    System.out.print(",");
                }
            }
            System.out.println();
        }
    }

    /**
     * Imprime la matriz de costos.
     * @param matriz
     * @param vertices
     * @param titulo
     */
    public static void imprimirMatrizMejorado(Comparable[][] matriz, Map<Comparable, TVertice> vertices, String titulo) {

        // Verificamos que existen los vertices y la matriz tiene el mismo tamaño que las claves.
        if (vertices != null && matriz.length == vertices.keySet().size()) {

            // Almacenamos las etiquetas de los vertices en un arreglo.
            Comparable[] etiquetas = vertices.keySet().toArray(new Comparable[vertices.keySet().size()]);

            // Obtenemos el largo de la mayor etiqueta.
            int etiquetaMasLarga = stringMasLargo(etiquetas);
            int datoMasLargo = 0;
            String infinito = "Inf";
            String nulo = "Nulo";
            int separacionEntreColumnas = 3;

            // Arreglo con los datos.
            Comparable[] datos = new Comparable[matriz.length];

            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz.length; j++) {
                    if (matriz[i][j] == null) {
                        datos[j] = nulo;
                    } else if (matriz[i][j].compareTo(Double.MAX_VALUE) == 0) {
                        datos[j] = infinito;
                    } else {
                        datos[j] = matriz[i][j];
                    }
                }
                if (stringMasLargo(datos) > datoMasLargo) {
                    datoMasLargo = stringMasLargo(datos);
                }
            }

            // Largo de los columnas.
            int largo = Math.max(etiquetaMasLarga, datoMasLargo) + separacionEntreColumnas;

            for (int i = 0; i < etiquetas.length; i++) {
                etiquetas[i] = rellenar(etiquetas[i].toString(), largo, ' ');
            }

            int tope = (largo) * (etiquetas.length + 1);

            String linea = rellenar("", tope, '-');
            String separador = rellenar("", largo, ' ');
            String sepTitulo = rellenar("", tope, '*');

            // Imprimir encabezado.
            System.out.println(sepTitulo);
            System.out.println(devolverCentrado(titulo, tope));
            System.out.println(sepTitulo);
            System.out.println(linea);

            // Imprimir etiquetas.
            System.out.print(separador);
            for (int i = 0; i < matriz.length; i++) {
                System.out.print(etiquetas[i]);
            }

            System.out.println();
            System.out.println(linea);

            // Imprimir lineas.
            for (int i = 0; i < matriz.length; i++) {
                System.out.print(etiquetas[i]);
                for (int j = 0; j < matriz.length; j++) {

                    if (matriz[i][j] == null) {
                        System.out.print(rellenar(nulo, largo, ' '));
                    } else if (matriz[i][j].compareTo(Double.MAX_VALUE) == 0) {
                        System.out.print(rellenar(infinito, largo, ' '));
                    } else {
                        System.out.print(rellenar(matriz[i][j].toString(), largo, ' '));
                    }
                }
                System.out.println();
                System.out.println(linea);
            }
        }
        System.out.println();
    }

    /**
     * Recibe un texto y completa la linea con el relleno hasta alcanzar el largo total.
     * @param textoARellenar
     * @param largoTotal
     * @param relleno
     * @return
     */
    public static String rellenar(String textoARellenar, int largoTotal, char relleno) {
        while (textoARellenar.length() < largoTotal) {
            textoARellenar += relleno;
        }
        return textoARellenar;
    }

    /**
     * Devuelve el valor (largo) de la mayor etiqueta.
     * 
     * @param etiquetas
     * @return
     */
    public static int stringMasLargo(Comparable[] etiquetas) {

        // Inicializa "mayor" con el largo de la primera etiqueta del arreglo. 
        int mayor = etiquetas[0].toString().length();

        // Recorre el arreglo evualuando para cada etiqueta su largo respecto al mayor registrado, 
        // si es mayor almacena el nuevo largo mayor.
        for (int i = 1; i < etiquetas.length; i++) {
            if (etiquetas[i].toString().length() > mayor) {
                mayor = etiquetas[i].toString().length();
            }
        }
        // Retorna un entero correspondiente al string de mayor largo.
        return mayor;
    }

    public static String devolverCentrado(String texto, int largo) {
        boolean pos = false;
        while (texto.length() < largo) {
            if (pos == false) {
                texto += " ";
                pos = true;
            } else {
                texto = " " + texto;
                pos = false;
            }
        }
        return texto;
    }

    public static <T extends IGrafoDirigido> T cargarGrafo(String nomArchVert, String nomArchAdy, 
            boolean ignoreHeader, Class t  ) {

        // Guardar en arreglos los vertices y las aristas a partir de archivos de texto.
        String[] vertices = ManejadorArchivosGenerico.leerArchivo(nomArchVert, ignoreHeader);
        String[] aristas = ManejadorArchivosGenerico.leerArchivo(nomArchAdy, ignoreHeader);

        // Crear listas para los vertices y aristas.
        List<TVertice> verticesList = new ArrayList<TVertice>(vertices.length);
        List<TArista> aristasList = new ArrayList<TArista>(aristas.length);

        // Para cada registro en el arreglo "vertices" (Montevideo)
        for (String verticeString : vertices) {
            if ((verticeString != null) && (! verticeString.trim().equals(""))) {
                // Obtener la ciudad origen.
                verticeString = verticeString.split(",")[0];
                // Creamos un TVertice y se agrega a la lista de vertices.
                verticesList.add(new TVertice(verticeString));
            }
        }

        // Para cada registro en el arreglo "aristas" (Montevideo,Buenos_Aires,200)
        for (String aristaString : aristas) {
            if ((aristaString != null) && (aristaString.trim() != "")) {
                // Obtener los datos Origen, Destino, Costo.
                String[] datos = aristaString.split(",");
                // Creamos una TArista y se agrega a la lista de aristas.
                aristasList.add(new TArista(datos[0], datos[1], Double.parseDouble(datos[2])));
            }
        }

        try {
            // Retornamos un Grafo Dirigido cargado.
            t.getConstructor(Collection.class, Collection.class);
            return (T) (t.getConstructor(Collection.class, Collection.class).newInstance(verticesList, aristasList));
        } catch (Exception ex) {
            Logger.getLogger(UtilGrafos.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
}
