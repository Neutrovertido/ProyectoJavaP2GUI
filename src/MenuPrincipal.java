import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MenuPrincipal {
    private JLabel lblTitulo;
    private JButton btnClientes;
    private JButton btnArticulos;
    private JButton btnFacturas;
    private JButton btnSalir;
    public JPanel pnlMenu;
    private JPanel pnlButtons;
    private JButton btnConsultas;
    private Tienda t1;

    public MenuPrincipal(Tienda t) {
        t1 = t;
        cargarFicheroC();
        cargarFicheroPC();
        cargarFicheroMon();
        cargarFicheroPer();
        cargarFicheroFact();
        btnClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame gc = new JFrame("Clientes");
                gc.setContentPane(new GestionClientes(t1).pnlClientes);
                gc.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                gc.setSize(600, 400);
                gc.setResizable(true);
                gc.setVisible(true);
            }
        });
        btnArticulos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ga = new JFrame("Artículos");
                ga.setContentPane(new GestionArticulos(t1).pnlArticulos);
                ga.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                ga.pack();
                ga.setResizable(true);
                ga.setVisible(true);
            }
        });
        btnFacturas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame gf = new JFrame("Factura");
                gf.setContentPane(new GestionFactura(t1).pnlFactura);
                gf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                gf.setSize(800, 800);
                gf.setResizable(true);
                gf.setVisible(true);
            }
        });
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnConsultas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame gcon = new JFrame("Consultas");
                gcon.setContentPane(new MenuConsultas(t1).pnlConsultas);
                gcon.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                gcon.setSize(800, 600);
                gcon.setResizable(true);
                gcon.setVisible(true);
            }
        });
        lblTitulo.addComponentListener(new ComponentAdapter() {
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
}
