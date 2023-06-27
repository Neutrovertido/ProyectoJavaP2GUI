import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        GestionArticulos g = new GestionArticulos();
        g.setTitle("Artículos");
        g.setContentPane(g.pnlArticulos);
        g.setSize(1000, 600);
        g.setVisible(true);
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}