import java.util.ArrayList;

public class Tienda {
    private String codigoT;
    private String nombreT;
    private String sitioWeb;
    private String direccion;
    private String noTel;
    private ArrayList<Cliente> clientes;
    private ArrayList<PC> pcs;
    private ArrayList<Monitor> monitores;
    private ArrayList<Periferico> perifericos;
    private ArrayList<Factura> facturas;

    public Tienda(String codigoT, String nombreT, String sitioWeb, String direccion, String noTel) {
        this.codigoT = codigoT;
        this.nombreT = nombreT;
        this.sitioWeb = sitioWeb;
        this.direccion = direccion;
        this.noTel = noTel;
        this.pcs = new ArrayList<PC>();
        this.monitores = new ArrayList<Monitor>();
        this.perifericos = new ArrayList<Periferico>();
        this.facturas = new ArrayList<Factura>();
    }

    // Constructor overload: only two variables
    public Tienda(String codigoT, String nombreT) {
        this.codigoT = codigoT;
        this.nombreT = nombreT;
        this.sitioWeb = "www.example.net";
        this.direccion = "Honduras";
        this.noTel = "9999-9999";
        this.pcs = new ArrayList<PC>();
        this.monitores = new ArrayList<Monitor>();
        this.perifericos = new ArrayList<Periferico>();
        this.facturas = new ArrayList<Factura>();
    }

    // Getters
    public String getCodigoT() {
        return this.codigoT;
    }

    public String getNombreT() {
        return this.nombreT;
    }

    public String getSitioWeb() {
        return this.sitioWeb;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public String getNoTel() {
        return this.noTel;
    }

    public ArrayList<Cliente> getClientes() {
        return this.clientes;
    }

    public ArrayList<PC> getPCs() {
        return this.pcs;
    }

    public ArrayList<Monitor> getMonitores() {
        return this.monitores;
    }

    public ArrayList<Periferico> getPerifericos() {
        return this.perifericos;
    }

    public ArrayList<Factura> getFacturas() {
        return this.facturas;
    }

    // Methods
    public void registrarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void registrarPC(PC pc) {
        this.pcs.add(pc);
    }

    public void registrarMonitor(Monitor monitor) {
        this.monitores.add(monitor);
    }

    public void registrarPeriferico(Periferico periferico) {
        this.perifericos.add(periferico);
    }

    public void registrarFactura(Factura factura) {
        this.facturas.add(factura);
    }

    public void imprimirClientes() {
        for (Cliente i : getClientes()) {
            System.out.println(i.getAtributos());
        }
    }

    public void imprimirPCs() {
        for (PC i : getPCs()) {
            System.out.println(i.getAtributos());
        }
    }

    public void imprimirMonitores() {
        for (Monitor i : getMonitores()) {
            System.out.println(i.getAtributos());
        }
    }

    public void imprimirPerifericos() {
        for (Periferico i : getPerifericos()) {
            System.out.println(i.getAtributos());
        }
    }

    public void imprimirFacturas() {
        for (Factura i : getFacturas()) {
            System.out.println(i.getAtributos());
        }
    }

    public String getAtributos() {
        String cl = "";
        String pc = "";
        String mn = "";
        String pf = "";
        String ft = "";

        for (Cliente i : getClientes()) {
            cl += i.getAtributos();
            cl += "\n";
        }
        cl += " ]";

        for (PC i : getPCs()) {
            pc += i.getAtributos();
            pc += "\n";
        }
        pc += " ]";

        for (Monitor i : getMonitores()) {
            mn += i.getAtributos();
            mn += "\n";
        }
        mn += " ]";

        for (Periferico i : getPerifericos()) {
            pf += i.getAtributos();
            pf += "\n";
        }

        for (Factura i : getFacturas()) {
            ft += i.getAtributos();
            ft += "\n";
        }
        ft += " ]";

        return "Código:" + getCodigoT() +
                "\nNombre: " + getNombreT() +
                "\nSitio Web: " + getSitioWeb() +
                "\nDirección: " + getDireccion() +
                "\nNo. Teléfono: " + getNoTel() +
                "\nClientes: [\n" + cl +
                "\nPCs: [\n" + pc +
                "\nMonitores: [\n" + mn +
                "\nPeriféricos: [\n" + pf +
                "\nFacturas: [ " + ft +
                "\nTotal Clientes: " + getClientes().size() +
                "\nTotal PCs: " + getPCs().size() +
                "\nTotal Monitores: " + getMonitores().size() +
                "\nTotal Periféricos: " + getPerifericos().size() +
                "\nTotal Facturas: " + getFacturas().size();
    }

}
