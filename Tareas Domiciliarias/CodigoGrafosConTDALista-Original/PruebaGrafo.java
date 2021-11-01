package ut8.TA2;


public class PruebaGrafo {

	public static void main(String[] args) {
		TGrafoDirigido gd = new TGrafoDirigido();
			
		gd.insertarVertice("A");
		gd.insertarVertice("B");
		gd.insertarVertice("C");
		gd.insertarVertice("D");
		gd.insertarVertice("E");
		
		gd.insertarAdyacencia("A", "B", 10);
		gd.insertarAdyacencia("B", "C", 20);
		gd.insertarAdyacencia("B", "D", 40);
		gd.insertarAdyacencia("A", "E", 80);
		gd.insertarAdyacencia("D", "C", 30);
		gd.insertarAdyacencia("C", "E", 55);
		
		Integer[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.vertices);
		UtilGrafos.imprimirMatriz(matriz, gd.vertices.etiquetasToArray());
	}

}
