import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.*;

public class GestionArticulos extends JFrame{
    private JLabel lblTitulo;
    private JTextField txtCodigo;
    private JLabel lblCodigo;
    private JLabel lblPrecio;
    private JTextField txtPrecio;
    private JSpinner spnDisponible;
    private JRadioButton rbtNuevo;
    private JRadioButton rbtUsado;
    private JLabel txtDisponible;
    private JTextField txtMarca;
    private JLabel lblMarca;
    private JRadioButton rbtPC;
    private JRadioButton rbtMonitor;
    private JRadioButton rbtPeriferico;
    private JLabel lblTipo;
    private JComboBox cmbTipoPC;
    private JLabel lblTipoPC;
    private JLabel lblSpecs;
    private JTextField txtSpecs;
    private JLabel lblTecnologia;
    private JComboBox cmbTecnologia;
    private JComboBox cmbResolucion;
    private JLabel lblResolucion;
    private JTable tblArticulos;
    private JRadioButton rbtAlambrico;
    private JRadioButton rbtInalambrico;
    private JLabel lblInalambrico;
    private JLabel lblTipoF;
    private JComboBox cmbTipoF;
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JButton btnBuscar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnMenuP;
    public JPanel pnlArticulos;
    private JLabel lblTamano;
    private JComboBox cmbTamano;
    private JPanel pnlData;
    private JPanel pnlData2;
    private JPanel pnlButtons;
    private Tienda t1;

    public GestionArticulos(Tienda t) {
        t1 = t;
        setElements();

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                limpiar();
            }
        });
        rbtPC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                visibilidad();
            }
        });
        rbtMonitor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                visibilidad();
            }
        });
        rbtPeriferico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                visibilidad();
            }
        });
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                guardarArticulo();
            }
        });
        btnBuscar.addComponentListener(new ComponentAdapter() {
        });
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buscar();
            }
        });
        tblArticulos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                clickTable();
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                eliminar();
            }
        });
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                modificar();
            }
        });
        txtCodigo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                String cod = txtCodigo.getText();
                if (t1.buscarPC(cod) != null || t1.buscarMonitor(cod) != null || t1.buscarPeriferico(cod) != null) {
                    JOptionPane.showMessageDialog(null, "El artículo con ese código ya existe", "Valor incorrecto", JOptionPane.ERROR_MESSAGE);
                    txtCodigo.setText("");
                    txtCodigo.requestFocus();
                }
            }
        });
    }

    public void setElements() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Código");
        model.addColumn("Precio");
        model.addColumn("Cantidad Disponible");
        model.addColumn("Estado");
        model.addColumn("Marca");

        if (rbtPC.isSelected()) {
            model.addColumn("Tipo PC");
            model.addColumn("Especificaciones");

            for (PC i : t1.getPCs()) {
                model.addRow(new Object[]{i.getCodigo(), i.getPrecio(), i.getDisponible(), i.getEstado(), i.getMarca(), i.getTipoPC(), i.getSpecs()});
            }
        } else if (rbtMonitor.isSelected()) {
            model.addColumn("Tecnología");
            model.addColumn("Resolución");
            model.addColumn("Tamaño");

            for (Monitor i : t1.getMonitores()) {
                model.addRow(new Object[]{i.getCodigo(), i.getPrecio(), i.getDisponible(), i.getEstado(), i.getMarca(), i.getTecnologia(), i.getResolucion(), i.getTamano()});
            }
        } else if (rbtPeriferico.isSelected()) {
            model.addColumn("Periférico");
            model.addColumn("Conexión");

            for (Periferico i : t1.getPerifericos()) {
                model.addRow(new Object[]{i.getCodigo(), i.getPrecio(), i.getDisponible(), i.getEstado(), i.getMarca(), i.getTipoF(), i.getInalambrico()});
            }
        }

        this.tblArticulos.setModel(model);
    }

    public void guardarArticulo() {
        String codigo = txtCodigo.getText();
        String marca = txtMarca.getText();
        if (codigo.length() != 0 && txtPrecio.getText().length() != 0 && marca.length() != 0) {
            double precio = Double.parseDouble(txtPrecio.getText());
            int disponible = (int) spnDisponible.getValue();
            boolean estado = rbtNuevo.isSelected(); // True = Nuevo / False = Usado

            if (t1.buscarPC(codigo) == null && t1.buscarMonitor(codigo) == null && t1.buscarPeriferico(codigo) == null) {
                if (rbtPC.isSelected()) {
                    String tipoPC = cmbTipoPC.getSelectedItem().toString();
                    String specs = txtSpecs.getText();

                    PC pc = new PC(codigo, precio, disponible, estado, marca, tipoPC, specs);
                    t1.registrarPC(pc);
                    guardarFicheroPC();
                    t1.imprimirPCs();
                } else if (rbtMonitor.isSelected()) {
                    String tecnologia = cmbTecnologia.getSelectedItem().toString();
                    String tamano = cmbTamano.getSelectedItem().toString();
                    String resolucion = cmbResolucion.getSelectedItem().toString();

                    Monitor mon = new Monitor(codigo, precio, disponible, estado, marca, tecnologia, tamano, resolucion);
                    t1.registrarMonitor(mon);
                    guardarFicheroMon();
                    t1.imprimirMonitores();
                } else if (rbtPeriferico.isSelected()) {
                    String tipoF = cmbTipoF.getSelectedItem().toString();
                    boolean inalambrico = rbtInalambrico.isSelected(); // True = Inalámbrico / False = Alámbrico

                    Periferico pef = new Periferico(codigo, precio, disponible, estado, marca, tipoF, inalambrico);
                    t1.registrarPeriferico(pef);
                    guardarFicheroPer();
                    t1.imprimirPerifericos();
                }
            } else {
                JOptionPane.showMessageDialog(null, "El artículo con ese código ya existe", "No ingresado", JOptionPane.ERROR_MESSAGE);
            }
            setElements();
        } else {
            JOptionPane.showMessageDialog(null, "Los siguientes campos no pueden ir vacíos:\nCódigo, Precio y Marca\nIntente nuevamente tras completarlos", "No ingresado", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Save to file methods
    public void guardarFicheroPC() {
        String name = "DatosPC.dat";
        try {
            FileOutputStream ficheroPC = new FileOutputStream(name);

            try (ObjectOutputStream objetoPC = new ObjectOutputStream(ficheroPC)) {
                for (PC i : t1.getPCs()) {
                    objetoPC.writeObject(i);
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

    public void guardarFicheroMon() {
        String name = "DatosMon.dat";
        try {
            FileOutputStream ficheroMon = new FileOutputStream(name);

            try (ObjectOutputStream objetoMon = new ObjectOutputStream(ficheroMon)) {
                for (Monitor i : t1.getMonitores()) {
                    objetoMon.writeObject(i);
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

    public void guardarFicheroPer() {
        String name = "DatosPer.dat";
        try {
            FileOutputStream ficheroPer = new FileOutputStream(name);

            try (ObjectOutputStream objetoPer = new ObjectOutputStream(ficheroPer)) {
                for (Periferico i : t1.getPerifericos()) {
                    objetoPer.writeObject(i);
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

    // Read from file methods
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

    // Misc methods
    public void buscar() {
        String cod = txtCodigo.getText();
        if (t1.buscarPC(cod) != null) {
            PC pc = t1.buscarPC(cod);

            txtPrecio.setText(String.valueOf(pc.getPrecio()));
            spnDisponible.setValue(pc.getDisponible());
            if (pc.getEstado()) {
                rbtNuevo.setSelected(true);
            } else {
                rbtUsado.setSelected(true);
            }
            txtMarca.setText(pc.getMarca());

            rbtPC.setSelected(true);
            cmbTipoPC.setSelectedItem(pc.getTipoPC());
            txtSpecs.setText(pc.getSpecs());

            visibilidad();
        } else if (t1.buscarMonitor(cod) != null) {
            Monitor mon = t1.buscarMonitor(cod);

            txtPrecio.setText(String.valueOf(mon.getPrecio()));
            spnDisponible.setValue(mon.getDisponible());
            if (mon.getEstado()) {
                rbtNuevo.setSelected(true);
            } else {
                rbtUsado.setSelected(true);
            }
            txtMarca.setText(mon.getMarca());

            rbtMonitor.setSelected(true);
            cmbTecnologia.setSelectedItem(mon.getTecnologia());
            cmbResolucion.setSelectedItem(mon.getResolucion());
            cmbTamano.setSelectedItem(mon.getTamano());

            visibilidad();
        } else if (t1.buscarPeriferico(cod) != null) {
            Periferico per = t1.buscarPeriferico(cod);

            txtPrecio.setText(String.valueOf(per.getPrecio()));
            spnDisponible.setValue(per.getDisponible());
            if (per.getEstado()) {
                rbtNuevo.setSelected(true);
            } else {
                rbtUsado.setSelected(true);
            }
            txtMarca.setText(per.getMarca());

            rbtPeriferico.setSelected(true);
            cmbTipoF.setSelectedItem(per.getTipoF());
            if (per.getInalambrico()) {
                rbtInalambrico.setSelected(true);
            } else {
                rbtAlambrico.setSelected(true);
            }

            visibilidad();
        } else {
            JOptionPane.showMessageDialog(null, "El artículo con ese código no existe", "Artículo no encontrado", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void clickTable() {
        int x = tblArticulos.getSelectedRow();
        String v = tblArticulos.getValueAt(x, 0).toString();

        if (rbtPC.isSelected()) {
            PC pc = t1.buscarPC(v);

            txtCodigo.setText(pc.getCodigo());
            txtPrecio.setText(String.valueOf(pc.getPrecio()));
            spnDisponible.setValue(pc.getDisponible());
            if (pc.getEstado()) {
                rbtNuevo.setSelected(true);
            } else {
                rbtUsado.setSelected(true);
            }
            txtMarca.setText(pc.getMarca());

            rbtPC.setSelected(true);
            cmbTipoPC.setSelectedItem(pc.getTipoPC());
            txtSpecs.setText(pc.getSpecs());
        } else if (rbtMonitor.isSelected()) {
            Monitor mon = t1.buscarMonitor(v);

            txtCodigo.setText(mon.getCodigo());
            txtPrecio.setText(String.valueOf(mon.getPrecio()));
            spnDisponible.setValue(mon.getDisponible());
            if (mon.getEstado()) {
                rbtNuevo.setSelected(true);
            } else {
                rbtUsado.setSelected(true);
            }
            txtMarca.setText(mon.getMarca());

            rbtMonitor.setSelected(true);
            cmbTecnologia.setSelectedItem(mon.getTecnologia());
            cmbResolucion.setSelectedItem(mon.getResolucion());
            cmbTamano.setSelectedItem(mon.getTamano());
        } else if (rbtPeriferico.isSelected()) {
            Periferico per = t1.buscarPeriferico(v);

            txtCodigo.setText(per.getCodigo());
            txtPrecio.setText(String.valueOf(per.getPrecio()));
            spnDisponible.setValue(per.getDisponible());
            if (per.getEstado()) {
                rbtNuevo.setSelected(true);
            } else {
                rbtUsado.setSelected(true);
            }
            txtMarca.setText(per.getMarca());

            rbtPeriferico.setSelected(true);
            cmbTipoF.setSelectedItem(per.getTipoF());
            if (per.getInalambrico()) {
                rbtInalambrico.setSelected(true);
            } else {
                rbtAlambrico.setSelected(true);
            }
        }
    }

    public void limpiar() {
        txtCodigo.setText("");
        txtPrecio.setText("");
        txtMarca.setText("");
        spnDisponible.setValue(0);
        rbtNuevo.setSelected(true);
        rbtUsado.setSelected(false);
        rbtPC.setSelected(true);
        rbtMonitor.setSelected(false);
        rbtPeriferico.setSelected(false);
        rbtAlambrico.setSelected(true);
        rbtInalambrico.setSelected(false);
        txtSpecs.setText("");
        visibilidad();
    }

    public void eliminar() {
        String cod = txtCodigo.getText();
        if (rbtPC.isSelected()) {
            if (t1.buscarPC(cod) != null) {
                t1.eliminarPC(cod);
                guardarFicheroPC();
                setElements();
            } else {
                JOptionPane.showMessageDialog(null, "La PC especificada no existe", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (rbtMonitor.isSelected()) {
            if (t1.buscarMonitor(cod) != null) {
                t1.eliminarMonitor(cod);
                guardarFicheroMon();
                setElements();
            } else {
                JOptionPane.showMessageDialog(null, "El monitor especificado no existe", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (rbtPeriferico.isSelected()) {
            if (t1.buscarPeriferico(cod) != null) {
                t1.eliminarPeriferico(cod);
                guardarFicheroPer();
                setElements();
            } else {
                JOptionPane.showMessageDialog(null, "El periférico especificado no existe", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void modificar() {
        String cod = txtCodigo.getText();
        if (rbtPC.isSelected()) {
            if (t1.buscarPC(cod) != null) {
                eliminar();
                guardarArticulo();
            } else {
                JOptionPane.showMessageDialog(null, "La PC especificada no existe", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (rbtMonitor.isSelected()) {
            if (t1.buscarMonitor(cod) != null) {
                eliminar();
                guardarArticulo();
            } else {
                JOptionPane.showMessageDialog(null, "El monitor especificado no existe", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (rbtPeriferico.isSelected()) {
            if (t1.buscarPeriferico(cod) != null) {
                eliminar();
                guardarArticulo();
            } else {
                JOptionPane.showMessageDialog(null, "El periférico especificado no existe", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void visibilidad() {
        if (rbtPC.isSelected()) {
            cmbTipoPC.setEnabled(true);
            txtSpecs.setEnabled(true);
            cmbTecnologia.setEnabled(false);
            cmbResolucion.setEnabled(false);
            cmbTamano.setEnabled(false);
            cmbTipoF.setEnabled(false);
            rbtInalambrico.setEnabled(false);
            rbtAlambrico.setEnabled(false);
        } else if (rbtMonitor.isSelected()) {
            cmbTipoPC.setEnabled(false);
            txtSpecs.setEnabled(false);
            cmbTecnologia.setEnabled(true);
            cmbResolucion.setEnabled(true);
            cmbTamano.setEnabled(true);
            cmbTipoF.setEnabled(false);
            rbtInalambrico.setEnabled(false);
            rbtAlambrico.setEnabled(false);
        } else if (rbtPeriferico.isSelected()) {
            cmbTipoPC.setEnabled(false);
            txtSpecs.setEnabled(false);
            cmbTecnologia.setEnabled(false);
            cmbResolucion.setEnabled(false);
            cmbTamano.setEnabled(false);
            cmbTipoF.setEnabled(true);
            rbtInalambrico.setEnabled(true);
            rbtAlambrico.setEnabled(true);
        }
        setElements();
    }
}
