package env;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa sluzaca do wyswietlenia panelu koncowego
 */
public class JPanelEnding extends JPanel{
    /**
     * Tablica, na ktorej wykonaywana jest symulacja
     */
    final Board board;

    /**
     * Tworzy panel koncowy
     * @param board tablica symulacji
     */
    JPanelEnding( Board board){
        this.board = board;

        JFrame endframe = new JFrame("The End");
        endframe.setSize(525, 550);
        endframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        endframe.setLocation(dim.width/2-endframe.getSize().width/2, dim.height/2-endframe.getSize().height/2);
        this.setBackground(Color.gray);

        endframe.setVisible(true);
        endframe.add(this);
    }

    /**
     * Metoda sluzaca do rysowania na panelu.
     * @param g  the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        FontMetrics metrics  = g.getFontMetrics();

        String string = "KONIEC SYMULACJI";
        g.drawString(string, (getWidth()- metrics.stringWidth(string))/2, 150);
        g.drawString("PRZETRWALO MROWISKO: ",(getWidth()- metrics.stringWidth("PRZETRWALO MROWISKO: "))/2, 250 );
        if (board.anthill_blue.ant_count() != 0){
            g.setColor(Color.BLUE);
            g.drawString("NIEBIESKIE", (getWidth()- metrics.stringWidth("NIEBIESKIE: "))/2,300);
        }

        else {
            g.setColor(Color.red);
            g.drawString("CZERWONE",(getWidth()- metrics.stringWidth("CZERWONE: "))/2,300 );
        }

        String string2 = "Ilosc mrowek z ogniska czerwonego: " + board.anthill_red.ant_count();
        g.setColor(Color.red);
        g.drawString(string2, (getWidth()- metrics.stringWidth(string2))/2,350 );

        String string3 = "Ilosc mrowek z ogniska niebieskiego: " + board.anthill_blue.ant_count();
        g.setColor(Color.blue);
        g.drawString(string3, (getWidth()- metrics.stringWidth(string3))/2,400 );

    }
}
