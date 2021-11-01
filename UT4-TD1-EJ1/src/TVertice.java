public class TVertice {
    
    private Comparable etiqueta;
    private boolean visitado;
    private Object datos;

    public TVertice(Comparable etiqueta, Object datos) {
        this.etiqueta = etiqueta;
        this.visitado = false;
        this.datos = datos;
    }

    public Comparable getEtiqueta() {
        return etiqueta;
    }

    public boolean esVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public Object getDatos() {
        return datos;
    }

}