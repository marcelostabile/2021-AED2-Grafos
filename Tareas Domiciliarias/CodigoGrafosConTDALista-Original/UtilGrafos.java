package ut8.TA2;


public class UtilGrafos {
	public static Integer[][] obtenerMatrizCostos(TLista vertices) {
		int cantidadVertices = vertices.obtenerCantidadElementos();
		Integer[][] matrizCostos = new Integer[cantidadVertices][cantidadVertices];
		
		for(int i = 0;i < matrizCostos.length; i++) {
			for(int j = 0; j < matrizCostos.length; j++) {
				matrizCostos[i][j] = -1;
			}
		}
		
		int i = 0;
		TElemento elemVerticeI = vertices.obtenerPrimero();
		while (elemVerticeI != null) {
			int j = 0;
			TElemento elemVerticeJ = vertices.obtenerPrimero();
			while (elemVerticeJ != null) {
				if (!elemVerticeI.getEtiqueta().equals(elemVerticeJ.getEtiqueta())) {
					TVertice verticeI = (TVertice) elemVerticeI;
					TVertice verticeJ = (TVertice) elemVerticeJ;
					Integer costoAdyacencia = (Integer) verticeI.obtenerCostoAdyacencia(verticeJ);
					matrizCostos[i][j] = costoAdyacencia;
				}
				elemVerticeJ = elemVerticeJ.getSiguiente();
				j++;
			}
			elemVerticeI = elemVerticeI.getSiguiente();
			i++;
		}
		return matrizCostos;
	}
	
	public static void imprimirMatriz(Integer[][] matriz, Comparable[] etiquetas) {
		System.out.print("  ");
		for(int i = 0;i < matriz.length; i++) {
			System.out.print(etiquetas[i] + "  ");
		}
		System.out.println();
		for(int i = 0;i < matriz.length; i++) {
			System.out.print(etiquetas[i] + " ");
			for(int j = 0; j < matriz.length; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}
	}

}
