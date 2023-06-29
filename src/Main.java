import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        /*GestionArticulos g = new GestionArticulos();
        g.setTitle("Artículos");
        g.setContentPane(g.pnlArticulos);
        g.setSize(1000, 600);
        g.setVisible(true);
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/

        JFrame ga = new JFrame("Artículos");
        ga.setContentPane(new GestionArticulos().pnlArticulos);
        ga.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ga.pack();
        ga.setResizable(true);
        ga.setVisible(true);

        JFrame gc = new JFrame("Clientes");
        gc.setContentPane(new GestionClientes().pnlClientes);
        gc.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        gc.setSize(600, 400);
        gc.setResizable(true);
        gc.setVisible(true);

        /*GestionClientes c = new GestionClientes();
        c.setTitle("Clientes");
        c.setContentPane(c.pnlClientes);
        c.setSize(600, 600);
        c.setVisible(true);
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
    }
}