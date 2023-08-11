import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class MenuConsultas {
    private JTabbedPane tabPrincipal;
    public JPanel pnlConsultas;
    private JLabel lblTitulo;
    private JPanel pnlClientes;
    private JPanel pnlArticulos;
    private JPanel pnlFacturas;
    private JTextField txtClientes;
    private JTextField txtArticulos;
    private JTextField txtFacturas;
    private JTable tblClientes;
    private JTable tblArticulos;
    private JTable tblFacturas;
    private DefaultTableModel mdlClientes;
    private DefaultTableModel mdlArticulos;
    private DefaultTableModel mdlFacturas;
    private Tienda t1;
    public MenuConsultas(Tienda t) {
        t1 = t;

        cargarClientes();
        cargarArticulos();
        cargarFacturas();

        txtClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) tblClientes.getModel()));
                sorter.setRowFilter(RowFilter.regexFilter(txtClientes.getText()));
                tblClientes.setRowSorter(sorter);
            }
        });
        txtArticulos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) tblArticulos.getModel()));
                sorter.setRowFilter(RowFilter.regexFilter(txtArticulos.getText()));
                tblArticulos.setRowSorter(sorter);
            }
        });
        txtFacturas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) tblFacturas.getModel()));
                sorter.setRowFilter(RowFilter.regexFilter(txtFacturas.getText()));
                tblFacturas.setRowSorter(sorter);
            }
        });
    }

    // Load to table methods
    public void cargarClientes() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("RTN");

        for (Cliente c : t1.getClientes()) {
            model.addRow(new Object[]{c.id, c.nombre, c.rtn});
        }

        this.mdlClientes = model;
        this.tblClientes.setModel(model);
    }

    public void cargarArticulos() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Código");
        model.addColumn("Precio");
        model.addColumn("Cantidad");
        model.addColumn("Estado");
        model.addColumn("Marca");
        model.addColumn("Descripción");

        for (PC pc : t1.getPCs()) {
            String estado = "";
            if (pc.getEstado()) {
                estado = "Nuevo";
            } else {
                estado = "Usado";
            }

            String descripcion = pc.getTipoPC() + ", " + pc.getSpecs();

            model.addRow(new Object[]{pc.codigo, pc.precio, pc.disponible, estado, pc.marca, descripcion});
        }

        for (Monitor mon : t1.getMonitores()) {
            String estado = "";
            if (mon.getEstado()) {
                estado = "Nuevo";
            } else {
                estado = "Usado";
            }

            String descripcion = mon.getTecnologia() + ", " + mon.getTamano() + ", " + mon.getResolucion();

            model.addRow(new Object[]{mon.codigo, mon.precio, mon.disponible, estado, mon.marca, descripcion});
        }

        for (Periferico per : t1.getPerifericos()) {
            String estado = "";
            if (per.getEstado()) {
                estado = "Nuevo";
            } else {
                estado = "Usado";
            }

            String inalambrico = "";
            if (per.getInalambrico()) {
                inalambrico = "Inalámbrico";
            } else {
                inalambrico = "Alámbrico";
            }

            String descripcion = per.getTipoF() + ", " + inalambrico;

            model.addRow(new Object[]{per.codigo, per.precio, per.disponible, estado, per.marca, descripcion});
        }

        this.mdlArticulos = model;
        this.tblArticulos.setModel(model);
    }

    public void cargarFacturas() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Número");
        model.addColumn("Fecha");
        model.addColumn("Cliente");
        model.addColumn("Detalles");
        model.addColumn("Descuento");
        model.addColumn("Total");

        for (Factura f : t1.getFacturas()) {

            String cliente = f.getCliente().getId() + ", " + f.getCliente().getNombre();

            String detalles = "";
            for (int i = 0; i < f.getCantidad().size(); i++) {
                detalles += f.getArticulo().get(i).codigo;
                detalles += ": ";
                detalles += f.getCantidad().get(i);
                detalles += "; ";
            }

            model.addRow(new Object[]{f.codigoF, f.fecha, cliente, detalles, f.descuento, f.total});
        }

        this.mdlFacturas = model;
        this.tblFacturas.setModel(model);
    }
}
