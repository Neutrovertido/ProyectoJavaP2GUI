import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        Tienda t1 = new Tienda("tty8", "Tienda Hardware & Mas");

        JFrame gm = new JFrame("Menú Principal");
        gm.setContentPane(new MenuPrincipal(t1).pnlMenu);
        gm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gm.pack();
        gm.setVisible(true);

        System.out.println(t1.getAtributos());

        // Legacy
        /*GestionClientes c = new GestionClientes();
        c.setTitle("Clientes");
        c.setContentPane(c.pnlClientes);
        c.setSize(600, 600);
        c.setVisible(true);
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
    }
}