import java.util.ArrayList;
import java.util.Calendar;
public class Factura {
    String codigoF;
    String fecha;
    Cliente cliente;
    ArrayList<Articulo> articulo;
    ArrayList<Integer> cantidad;
    double total;

    public Factura(ArrayList<Articulo> articulo, ArrayList<Integer> cantidad, String numero, String fecha, Cliente cliente) {
        this.codigoF = numero;
        this.fecha = fecha;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;

        calcularTotal();
    }

    public Factura(ArrayList<Articulo> articulo, ArrayList<Integer> cantidad, String numero, Cliente cliente) {
        this.codigoF = numero;
        this.fecha = fechaActual();
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;

        calcularTotal();
    }

    // Getters
    public String getCodigoF() {
        return this.codigoF;
    }

    public String getFecha() {
        return this.fecha;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public ArrayList<Articulo> getArticulo() {
        return this.articulo;
    }

    public ArrayList<Integer> getCantidad() {
        return this.cantidad;
    }

    public double getTotal() {
        return this.total;
    }

    // Methods
    public String fechaActual() {
        int dia, mes, anho;
        dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        mes = Calendar.getInstance().get(Calendar.MONTH);
        anho = Calendar.getInstance().get(Calendar.YEAR);
        return dia + "/" + mes +  "/" + anho;
    }

    private void calcularTotal() {
        this.total = 0.0;
        for (int i = 0; i < cantidad.size(); i++) {
            this.total += articulo.get(i).getPrecio() * cantidad.get(i);
        }
    }

    public String getAtributos() {

        String articulos = "";
        String cantidades = "";

        for (int i = 0; i < getArticulo().size(); i++) {
            articulos += getArticulo().get(i).getAtributos() + " ";
        };

        for (int i = 0;i < getCantidad().size(); i++) {
            cantidades += getCantidad().get(i) + " ";
        };

        return "No: " + getCodigoF() +
                "\nFecha: " + getFecha() +
                "\n" + getCliente().getAtributos() +
                "\nProductos: \n[ " + articulos +
                "]\nCantidades: \n[ " + cantidades + "]" +
                "\nTotal: " + getTotal();
    }
}
