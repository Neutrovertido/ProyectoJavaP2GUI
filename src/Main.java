import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        Tienda t1 = new Tienda("tty8", "Tienda Hardware & Mas");

        JFrame ga = new JFrame("Artículos");
        ga.setContentPane(new GestionArticulos(t1).pnlArticulos);
        ga.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ga.pack();
        ga.setResizable(true);
        ga.setVisible(true);

        JFrame gc = new JFrame("Clientes");
        gc.setContentPane(new GestionClientes(t1).pnlClientes);
        gc.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        gc.setSize(600, 400);
        gc.setResizable(true);
        gc.setVisible(true);

        JFrame gf = new JFrame("Factura");
        gf.setContentPane(new GestionFactura(t1).pnlFactura);
        gf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        gf.setSize(800, 800);
        gf.setResizable(true);
        gf.setVisible(true);

        // Legacy
        /*GestionClientes c = new GestionClientes();
        c.setTitle("Clientes");
        c.setContentPane(c.pnlClientes);
        c.setSize(600, 600);
        c.setVisible(true);
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
    }
}