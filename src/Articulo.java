public class Articulo {
    String codigo;
    double precio;
    int disponible;
    boolean estado;
    String marca;

    public Articulo(String codigo, double precio, int disponible, boolean estado, String marca) {
        this.codigo = codigo;
        this.precio = precio;
        this.disponible = disponible;
        this.estado = estado;
        this.marca = marca;
    }

    // Getters
    public String getCodigo() {
        return this.codigo;
    }

    public double getPrecio() {
        return this.precio;
    }

    public int getDisponible() {
        return this.disponible;
    }

    public boolean getEstado() {
        return this.estado;
    }

    public String getMarca() {
        return this.marca;
    }

    // Setters
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    // Methods
    public String getAtributos() {
        String estado;
        if (getEstado()) {
            estado = "Nuevo";
        } else {
            estado = "Usado";
        }

        return "Código: " + getCodigo() +
                "\nPrecio: " + getPrecio() +
                "\nCantidad disponible: " + getDisponible() +
                "\nEstado: " + estado +
                "\nMarca: " + getMarca();
    }
}
