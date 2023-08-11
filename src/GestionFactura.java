import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

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
    private JLabel lblImpuesto;
    private JTextField txtImpuesto;
    private JPanel pnlData;
    private JPanel pnlData2;
    private Tienda t1;
    private ArrayList<Articulo> arti;
    private ArrayList<Integer> cant;
    private DefaultTableModel model;

    public GestionFactura(Tienda t) {
        t1 = t;
        arti = new ArrayList<Articulo>();
        cant = new ArrayList<Integer>();

        autoValues();

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
                limpiarTabla();
            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addArticulo();
            }
        });
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                guardarFactura();
            }
        });
        txtDescuento.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                try {
                    totalizar();
                } catch (Exception ex) {
                    txtDescuento.setText("0");
                    totalizar();
                }
            }
        });
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarFactura();
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

    public void cargarFicheroFact() {
        String name = "DatosFact.dat";
        try {
            FileInputStream ficheroFact = new FileInputStream(name);
            try (ObjectInputStream objetoFact = new ObjectInputStream(ficheroFact)) {
                Factura fact = (Factura) objetoFact.readObject();
                while (fact != null) {
                    t1.registrarFactura(fact);
                    fact = (Factura) objetoFact.readObject();
                }
                objetoFact.close();
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
        txtImpuesto.setText("");
        txtTotal.setText("");
        updateArticulo();
        updateCliente();
        autoValues();
    }

    public void addArticulo() {
        String cod = txtCodigo.getText();
        boolean found = false;
        String tipo = "";
        String desc = "";
        double precio = 0;
        int cantidad = (int) spnCantidad.getValue();
        if (cantidad > 0) {
            if (t1.buscarPC(cod) != null) {
                PC pc = t1.buscarPC(cod);
                tipo = "PC/Laptop/AIO";
                desc = pc.getMarca() + ", " + pc.getTipoPC() + ", " + pc.getSpecs();
                precio = pc.getPrecio();
                arti.add(t1.buscarPC(cod));
                found = true;
            } else if (t1.buscarMonitor(cod) != null) {
                Monitor mon = t1.buscarMonitor(cod);
                tipo = "Monitor";
                desc = mon.getMarca() + ", " + mon.getTecnologia() + ", " + mon.getResolucion() + ", " + mon.getTamano();
                precio = mon.getPrecio();
                arti.add(t1.buscarMonitor(cod));
                found = true;
            } else if (t1.buscarPeriferico(cod) != null) {
                Periferico per = t1.buscarPeriferico(cod);
                tipo = "Periférico";
                desc = per.getMarca() + ", " + per.getTipoF();
                precio = per.getPrecio();
                arti.add(t1.buscarPeriferico(cod));
                found = true;
            }

            if (found) {
                double subtotal = precio * cantidad;
                cant.add(cantidad);

                model.addRow(new Object[]{cod, tipo, desc, precio, cantidad, subtotal});

                txtCodigo.setText("");
                spnCantidad.setValue(0);
                updateArticulo();
                totalizar();
            } else {
                JOptionPane.showMessageDialog(null, "No existe el articulo especificado", "No añadido", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No puede agregar artículos con cantidad 0 o menor", "No añadido", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void guardarFactura() {
        String cod = txtNumero.getText();

        if (t1.buscarFactura(cod) == null) {
            String fecha = txtFecha.getText();
            if (fecha.matches("[0-9]+/[0-9]+/[0-9]+")) {
                if (t1.buscarCliente(txtCliente.getText()) != null) {
                    Cliente c = t1.buscarCliente(txtCliente.getText());
                    double d = 0.0;
                    try {
                        d = Double.parseDouble(txtDescuento.getText());
                    } catch (Exception e) {
                        System.out.println("Descuento entendido como 0%");
                    }

                    Factura f = new Factura(arti, cant, cod, fecha, c, d);
                    t1.registrarFactura(f);
                    guardarFicheroFactura();
                    String msg = "La factura ha sido ingresada correctamente.\n\n" + f.getAtributos();
                    JOptionPane.showMessageDialog(null, msg, "Factura ingresada", JOptionPane.INFORMATION_MESSAGE);
                    limpiar();
                    limpiarTabla();
                    autoValues();

                    System.out.println(t1.getAtributos());
                } else {
                    JOptionPane.showMessageDialog(null, "El cliente especificado no existe", "Factura no ingresada", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "La fecha debe seguir el formato:\nDD/MM/YYYY", "Factura no ingresada", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ya existe una factura con ese número", "Factura no ingresada", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void guardarFicheroFactura() {
        String name = "DatosFact.dat";
        try {
            FileOutputStream ficheroF = new FileOutputStream(name);

            try (ObjectOutputStream objetoF = new ObjectOutputStream(ficheroF)) {
                for (Factura i : t1.getFacturas()) {
                    objetoF.writeObject(i);
                }
                objetoF.close();
            }
        } catch(FileNotFoundException e) {
            System.out.println("Fichero inexistente.");
        } catch(IOException e) {
            System.out.println(e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarFactura() {
        String cod = txtNumero.getText();
        Factura f = t1.buscarFactura(cod);

        if (f != null) {
            limpiarTabla();

            txtFecha.setText(f.getFecha());
            txtCliente.setText(f.getCliente().getId());
            txtDescuento.setText(String.valueOf(f.getDescuento()));
            updateCliente();


            for (int i = 0; i < f.getArticulo().size(); i++) {
                Articulo a = f.getArticulo().get(i);
                String artcod = a.getCodigo();
                String tipo = "";
                String desc = "";
                double precio = 0;
                int cantidad = f.getCantidad().get(i);
                cant.add(cantidad);
                if (a.getClass() == PC.class) {
                    PC pc = t1.buscarPC(a.getCodigo());
                    tipo = "PC/Laptop/AIO";
                    desc = pc.getMarca() + ", " + pc.getTipoPC() + ", " + pc.getSpecs();
                    precio = pc.getPrecio();
                    arti.add(pc);
                } else if (a.getClass() == Monitor.class) {
                    Monitor mon = t1.buscarMonitor(a.getCodigo());
                    tipo = "Monitor";
                    desc = mon.getMarca() + ", " + mon.getTecnologia() + ", " + mon.getResolucion() + ", " + mon.getTamano();
                    precio = mon.getPrecio();
                    arti.add(mon);
                } else if (a.getClass() == Periferico.class) {
                    Periferico per = t1.buscarPeriferico(a.getCodigo());
                    tipo = "Periférico";
                    desc = per.getMarca() + ", " + per.getTipoF();
                    precio = per.getPrecio();
                    arti.add(per);
                }
                double subtotal = precio * cantidad;
                model.addRow(new Object[]{artcod, tipo, desc, precio, cantidad, subtotal});
            }
            updateArticulo();
            totalizar();
        } else {
            JOptionPane.showMessageDialog(null, "La factura con ese número no existe", "Factura no encontrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Misc methods
    public void limpiarTabla() {
        arti = new ArrayList<Articulo>();
        cant = new ArrayList<Integer>();

        model = new DefaultTableModel();
        model.addColumn("Código");
        model.addColumn("Tipo");
        model.addColumn("Descripción");
        model.addColumn("Precio");
        model.addColumn("Cantidad");
        model.addColumn("Subtotal");
        this.tblFactura.setModel(model);
    }

    public void totalizar() {
        double subt = 0.0;
        int count = tblFactura.getModel().getRowCount();
        double desc = 0.0;
        String tdesc = txtDescuento.getText();

        for (int i = 0; i < count; i++) {
            double valor = (double) tblFactura.getModel().getValueAt(i, 3);
            int cantidad = (int) tblFactura.getModel().getValueAt(i, 4);
            subt += valor * cantidad;
        }
        txtSubtotal.setText(String.valueOf(subt));

        if (tdesc.length() != 0) {
            desc = subt * Double.parseDouble(tdesc) / 100;
        }

        double imp = subt * 0.15;
        txtImpuesto.setText(String.valueOf(imp));

        double total = subt - desc + imp;
        txtTotal.setText(String.valueOf(total));
    }

    public void autoValues() {
        txtNumero.setText(Integer.toString(t1.getFacturas().size() + 1));
        txtFecha.setText(Factura.fechaActual());
    }
}
