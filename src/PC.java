public class PC extends Articulo{
    String tipoPC;
    String specs;

    public PC(String codigo, double precio, int disponible, boolean estado, String marca, String tipoPC, String specs) {
        super(codigo, precio, disponible, estado, marca);
        this.tipoPC = tipoPC;
        this.specs = specs;
    }

    // Getters
    public String getTipoPC() {
        return this.tipoPC;
    }

    public String getSpecs() {
        return this.specs;
    }

    // Methods
    @Override
    public String getAtributos() {
        return super.getAtributos() +
                "\nTipo: " + getTipoPC() +
                "\nEspecificaciones: " + getSpecs();
    }
}
