import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("RTN");
        this.tblClientes.setModel(model);
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
}
