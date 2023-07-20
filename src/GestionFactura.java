import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class GestionFactura extends JFrame {
    private JLabel lblTitulo;
    private JLabel lblCodigo;
    private JTextField txtCodigo;
    private JLabel lblCliente;
    private JTextField txtCliente;
    private JSpinner spnCantidad;
    private JLabel lblCantidad;
    private JTextArea txaCliente;
    private JTable tblFactura;
    private JTextArea txaArticulo;
    private JLabel lblNumero;
    private JTextField txtNumero;
    private JButton btnAdd;
    public JPanel pnlFactura;
    private JLabel lblSubtotal;
    private JTextField txtSubtotal;
    private JLabel lblDescuento;
    private JLabel lblTotal;
    private JTextField txtDescuento;
    private JTextField txtTotal;
    private JButton btnBuscar;
    private JButton btnMenu;
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JLabel lblFecha;
    private JTextField txtFecha;
    private Tienda t1;
    private DefaultTableModel model;

    public GestionFactura(Tienda t) {
    t1 = t;

    cargarFicheroC();
    cargarFicheroPC();
    cargarFicheroMon();
    cargarFicheroPer();

    txtNumero.setText(Integer.toString(t1.getFacturas().size() + 1));
    txtFecha.setText(Factura.fechaActual());

    model = new DefaultTableModel();
    model.addColumn("Código");
    model.addColumn("Tipo");
    model.addColumn("Descripción");
    model.addColumn("Precio");
    model.addColumn("Cantidad");
    model.addColumn("Subtotal");
    this.tblFactura.setModel(model);

    txtCliente.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            super.keyTyped(e);
            updateCliente();
        }
    });
    txtCodigo.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            super.keyTyped(e);
            updateArticulo();
        }
    });
    btnLimpiar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            limpiar();
        }
    });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addArticulo();
            }
        });
    }
    // Load from file methods
    public void cargarFicheroC() {
        String name = "DatosClientes.dat";
        try {
            FileInputStream ficheroC = new FileInputStream(name);
            try (ObjectInputStream objetoC = new ObjectInputStream(ficheroC)) {
                Cliente c = (Cliente) objetoC.readObject();
                while (c != null) {
                    t1.registrarCliente(c);
                    c = (Cliente) objetoC.readObject();
                }
                objetoC.close();
            }
        } catch(FileNotFoundException e) {
            System.out.println("Fichero inexistente.");
        } catch(IOException e) {
            System.out.println(e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void cargarFicheroPC() {
        String name = "DatosPC.dat";
        try {
            FileInputStream ficheroPC = new FileInputStream(name);
            try (ObjectInputStream objetoPC = new ObjectInputStream(ficheroPC)) {
                PC pc = (PC) objetoPC.readObject();
                while (pc != null) {
                    t1.registrarPC(pc);
                    pc = (PC) objetoPC.readObject();
                }
                objetoPC.close();
            }
        } catch(FileNotFoundException e) {
            System.out.println("Fichero inexistente.");
        } catch(IOException e) {
            System.out.println(e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void cargarFicheroMon() {
        String name = "DatosMon.dat";
        try {
            FileInputStream ficheroMon = new FileInputStream(name);
            try (ObjectInputStream objetoMon = new ObjectInputStream(ficheroMon)) {
                Monitor mon = (Monitor) objetoMon.readObject();
                while (mon != null) {
                    t1.registrarMonitor(mon);
                    mon = (Monitor) objetoMon.readObject();
                }
                objetoMon.close();
            }
        } catch(FileNotFoundException e) {
            System.out.println("Fichero inexistente.");
        } catch(IOException e) {
            System.out.println(e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void cargarFicheroPer() {
        String name = "DatosPer.dat";
        try {
            FileInputStream ficheroPer = new FileInputStream(name);
            try (ObjectInputStream objetoPer = new ObjectInputStream(ficheroPer)) {
                Periferico per = (Periferico) objetoPer.readObject();
                while (per != null) {
                    t1.registrarPeriferico(per);
                    per = (Periferico) objetoPer.readObject();
                }
                objetoPer.close();
            }
        } catch(FileNotFoundException e) {
            System.out.println("Fichero inexistente.");
        } catch(IOException e) {
            System.out.println(e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateCliente() {
        try {
            txaCliente.setText("Cliente:\n" + t1.buscarCliente(txtCliente.getText()).getAtributos());
        } catch (Exception e) {
            txaCliente.setText("Cliente:");
        }
    }

    public void updateArticulo() {
        try {
            String cod = txtCodigo.getText();
            if (t1.buscarPC(cod) != null) {
                txaArticulo.setText("PC:\n" + t1.buscarPC(cod).getAtributos());
            } else if (t1.buscarMonitor(cod) != null) {
                txaArticulo.setText("Monitor:\n" + t1.buscarMonitor(cod).getAtributos());
            } else if (t1.buscarPeriferico(cod) != null) {
                txaArticulo.setText("Periférico:\n" + t1.buscarPeriferico(cod).getAtributos());
            } else {
                txaArticulo.setText("Artículo:");
            }
        } catch (Exception e) {
            txaArticulo.setText("Artículo:");
        }
    }

    public void limpiar() {
        txtNumero.setText("");
        txtFecha.setText("");
        txtCliente.setText("");
        txtCodigo.setText("");
        spnCantidad.setValue(0);
        txtSubtotal.setText("");
        txtDescuento.setText("");
        txtTotal.setText("");
        updateArticulo();
        updateCliente();
    }

    public void addArticulo() {
        String cod = txtCodigo.getText();
        String tipo = "";
        String desc = "";
        double precio = 0;
        int cantidad = (int) spnCantidad.getValue();
        if (t1.buscarPC(cod) != null) {
            PC pc = t1.buscarPC(cod);
            tipo = "PC/Laptop/AIO";
            desc = pc.getMarca() + ", " + pc.getTipoPC() + ", " + pc.getSpecs();
            precio = pc.getPrecio();
        } else if (t1.buscarMonitor(cod) != null) {
            Monitor mon = t1.buscarMonitor(cod);
            tipo = "Monitor";
            desc = mon.getMarca() + ", " + mon.getTecnologia() + ", " + mon.getResolucion() + ", " + mon.getTamano();
            precio = mon.getPrecio();
        } else if (t1.buscarPeriferico(cod) != null) {
            Periferico per = t1.buscarPeriferico(cod);
            tipo = "Periférico";
            desc = per.getMarca() + ", " + per.getTipoF();
            precio = per.getPrecio();
        }
        double subtotal = precio * cantidad;

        model.addRow(new Object[]{cod, tipo, desc, precio, cantidad, subtotal});

        txtCodigo.setText("");
        spnCantidad.setValue(0);
        updateArticulo();
    }
}
