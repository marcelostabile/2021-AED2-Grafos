public class App {
    public static void main(String[] args) throws Exception {

        TGrafoDirigido grafoDirigido = new TGrafoDirigido();

        TVertice artigas = new TVertice("Artigas", "Artigas");
        TVertice canelones = new TVertice("Canelones", "Canelones");
        TVertice colonia = new TVertice("Colonia", "Colonia");
        TVertice durazno = new TVertice("Durazno", "Durazno");
        TVertice florida = new TVertice("Florida", "Florida");
        TVertice montevideo = new TVertice("Montevideo", "Montevideo");
        TVertice puntaDelEste = new TVertice("PuntaDelEste", "PuntaDelEste");
        TVertice rocha = new TVertice("Rocha", "Rocha");
        
        grafoDirigido.insertarVertice(artigas);
        grafoDirigido.insertarVertice(canelones);
        grafoDirigido.insertarVertice(colonia);
        grafoDirigido.insertarVertice(durazno);
        grafoDirigido.insertarVertice(florida);
        grafoDirigido.insertarVertice(montevideo);
        grafoDirigido.insertarVertice(puntaDelEste);
        grafoDirigido.insertarVertice(rocha);
        
        TArista arista01 = new TArista(artigas, rocha, 400);
        TArista arista02 = new TArista(canelones, artigas, 500);
        TArista arista03 = new TArista(canelones, colonia, 200);
        TArista arista04 = new TArista(canelones, durazno, 170);
        TArista arista05 = new TArista(canelones, puntaDelEste, 90);
        TArista arista06 = new TArista(colonia, montevideo, 180);
        TArista arista07 = new TArista(florida, durazno, 60);
        TArista arista08 = new TArista(montevideo, artigas, 700);
        TArista arista09 = new TArista(montevideo, canelones, 30);
        TArista arista10 = new TArista(montevideo, puntaDelEste, 130);
        TArista arista11 = new TArista(puntaDelEste, rocha, 90);
        TArista arista12 = new TArista(rocha, montevideo, 270);
 
        grafoDirigido.insertarArista(arista01);
        grafoDirigido.insertarArista(arista02);
        grafoDirigido.insertarArista(arista03);
        grafoDirigido.insertarArista(arista04);
        grafoDirigido.insertarArista(arista05);
        grafoDirigido.insertarArista(arista06);
        grafoDirigido.insertarArista(arista07);
        grafoDirigido.insertarArista(arista08);
        grafoDirigido.insertarArista(arista09);
        grafoDirigido.insertarArista(arista10);
        grafoDirigido.insertarArista(arista11);
        grafoDirigido.insertarArista(arista12);
        




        
    }
}
