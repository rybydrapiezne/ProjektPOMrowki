package env;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa sluzaca do stworzenia wykresu.
 */
public class JPanelStatistics extends JPanel implements ActionListener{
    /**
     * Obiekt klasy Simulation
     */
    final Simulation sim;

    /**
     * Tworzy nowy panel do rysowania wykresu
     * @param sim Obiekt klasy Simulation
     */
    JPanelStatistics(Simulation sim){
        this.sim = sim;
        this.setBackground(Color.gray);
        JFrame graph = new JFrame("Graph");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        graph.setSize(dim.width/2,dim.height-50);
        graph.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        graph.setVisible(true);
        graph.add(this);

        Timer timer = new Timer(1000, this);
        timer.start();
    }

    /**
     * Metoda sluzaca do rysowania wykresu na panelu.
     * @param G  the <code>Graphics</code> context in which to paint
     */
    public void paint(Graphics G) {
        super.paint(G);
        Graphics2D G2D = (Graphics2D) G;
        if( sim.anthill1.history.size() > 0) {
            int first_value = sim.anthill1.history.get(0);

            for (int i = 1; i < sim.anthill1.history.size(); i++) {
                int second_value = sim.anthill1.history.get(i);
                G2D.setColor(Color.BLUE);
                G2D.drawLine(i - 1, getHeight() - 10 - first_value, i, getHeight() - 10 - second_value);
                first_value = second_value;
            }
        }
        if( sim.anthill2.history.size() > 0) {
            int first_value = sim.anthill2.history.get(0);

            for (int i = 1; i < sim.anthill2.history.size(); i++) {

                int second_value = sim.anthill2.history.get(i);

                G2D.setColor(Color.RED);
                G2D.drawLine(i - 1, getHeight() - 10 - first_value, i, getHeight() - 10 - second_value);
                first_value = second_value;
            }
        }

        FontMetrics metrics = G.getFontMetrics();
        G.setFont(new Font("TimesRoman",Font.PLAIN,30));

        String string1 = Integer.toString(sim.anthill1.ant_count());
        G.setColor(Color.BLUE);
        G.drawString(string1,(getWidth()- metrics.stringWidth(string1))/2 - 50, getHeight()/2);

        String string2 = Integer.toString(sim.anthill2.ant_count());
        G.setColor(Color.red);
        G.drawString(string2,(getWidth()- metrics.stringWidth(string2))/2 + 50, getHeight()/2);


    }

    /**
     * Metoda sluzaca do ponownego wywolania metody paint(Graphics g)
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
