import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GestionClientes extends JFrame{
    private JLabel lblTitulo;
    private JTextField txtId;
    private JLabel lblId;
    private JLabel lblNombre;
    private JTextField txtNombre;
    private JLabel lblRtn;
    private JTextField txtRtn;
    public JPanel pnlClientes;
    private JButton btnGuardar;
    private JPanel panBtn;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JButton btnModificar;
    private JButton btnMenuP;
    private JTable tblClientes;
    private Tienda t1 = new Tienda("tty8", "Tienda Hardware & Mas");

    public GestionClientes() {
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                limpiar();
            }
        });
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                guardarCliente();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buscar();
            }
        });

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("RTN");
        this.tblClientes.setModel(model);

        cargar();
    }

    public void limpiar() {
        txtId.setText("");
        txtNombre.setText("");
        txtRtn.setText("");
    }

    public void guardarCliente() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        String rtn = txtRtn.getText();

        if (t1.buscarCliente(txtId.getText()) == null) {
            Cliente cli = new Cliente(id, nombre, rtn);
            t1.registrarCliente(cli);

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Nombre");
            model.addColumn("RTN");

            for (Cliente c : t1.getClientes()) {
                model.addRow(new Object[]{c.id, c.nombre, c.rtn});
            }

            this.tblClientes.setModel(model);

            guardarFicheroC();

            t1.imprimirClientes();
        } else {
            JOptionPane.showMessageDialog(null, "El cliente con ese ID ya existe", "Cliente no ingresado", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void guardarFicheroC() {
        String name = "DatosClientes.dat";
        try {
            FileOutputStream ficheroC = new FileOutputStream(name);

            try (ObjectOutputStream objetoC = new ObjectOutputStream(ficheroC)) {
                for (Cliente i : t1.getClientes()) {
                    objetoC.writeObject(i);
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

    public void cargar() {
        cargarFicheroC();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("RTN");

        for (Cliente c : t1.getClientes()) {
            model.addRow(new Object[]{c.id, c.nombre, c.rtn});
        }

        this.tblClientes.setModel(model);
    }

    public void buscar() {
        Cliente c = t1.buscarCliente(txtId.getText());

        if (c != null) {
           txtNombre.setText(c.getNombre());
           txtRtn.setText(c.getRtn());
        } else {
            JOptionPane.showMessageDialog(null, "El cliente con esa ID no existe", "Cliente no encontrado", JOptionPane.ERROR_MESSAGE);
        }
    }
}
