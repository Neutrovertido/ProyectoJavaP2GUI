public class Monitor extends Articulo{
    String tecnologia;
    String tamano;
    String resolucion;

    public Monitor(String codigo, double precio, int disponible, boolean estado, String marca, String tecnologia, String tamano, String resolucion) {
        super(codigo, precio, disponible, estado, marca);
        this.tecnologia = tecnologia;
        this.tamano = tamano;
        this.resolucion = resolucion;
    }

    // Getters
    public String getTecnologia() {
        return this.tecnologia;
    }

    public String getTamano() {
        return this.tamano;
    }

    public String getResolucion() {
        return this.resolucion;
    }

    // Methods
    @Override
    public String getAtributos() {
        return super.getAtributos() +
                "\nTecnología de pantalla: " + getTecnologia() +
                "\nTamaño: " + getTamano() +
                "\nResolución: " + getResolucion();
    }
}
