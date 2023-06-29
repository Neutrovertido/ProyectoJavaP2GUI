import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
    private Tienda t1 = new Tienda("tty8", "Tienda Hardware & Mas");

    public GestionArticulos() {
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

        setElements();
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
        double precio = Double.parseDouble(txtPrecio.getText());
        int disponible = (int) spnDisponible.getValue();
        boolean estado = rbtNuevo.isSelected(); // True = Nuevo / False = Usado
        String marca = txtMarca.getText();

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
            boolean inalambrico = rbtInalambrico.isSelected();

            Periferico pef = new Periferico(codigo, precio, disponible, estado, marca, tipoF, inalambrico);
            t1.registrarPeriferico(pef);
            guardarFicheroPer();
            t1.imprimirPerifericos();
        }
        setElements();
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


// Misc methods
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
