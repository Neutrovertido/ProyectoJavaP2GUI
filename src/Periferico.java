public class Periferico extends Articulo {
    String tipoF;
    boolean inalambrico;

    public Periferico(String codigo, double precio, int disponible, boolean estado, String marca, String tipoF, boolean inalambrico) {
        super(codigo, precio, disponible, estado, marca);
        this.tipoF = tipoF;
        this.inalambrico = inalambrico;
    }

    // Getters
    public String getTipoF() {
        return this.tipoF;
    }

    public boolean getInalambrico() {
        return this.inalambrico;
    }

    // Methods
    @Override
    public String getAtributos() {
        String inalambrico;
        if (getInalambrico()) {
            inalambrico = "Inalámbrico";
        } else {
            inalambrico = "Alámbrico";
        }

        return super.getAtributos() +
                "\nTipo: " + tipoF +
                "\nAlámbrico/Inalámbrico: " + inalambrico;
    }

}
