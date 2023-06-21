public class Cliente {
    String id;
    String nombre;
    String rtn;

    public Cliente(String id, String nombre, String rtn) {
        this.id = id;
        this.nombre = nombre;
        this.rtn = rtn;
    }

    // Constructor overload, no rtn
    public Cliente(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.rtn = "";
    }

    // Getters
    public String getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getRtn() {
        return this.rtn;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRtn(String rtn) {
        this.rtn = rtn;
    }

    // Methods
    public String getAtributos() {
        return "ID: " + getId() +
                "\nNombre: " + getNombre() +
                "\nRTN: " + getRtn();
    }
}
