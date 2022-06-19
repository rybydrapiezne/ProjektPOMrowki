package env;

import agents.Ant;
import agents.Ant_Queen;
import agents.Ant_eater;
import agents.Flying_ant;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa sluzaca do przedstawienia graficznego symulacji
 * @see javax.swing.JPanel
 */
public class JPanelSimulation extends JPanel {
    /**
     * Tablica, na ktorej wykonywana jest symulacja
     */
    final Board board;

    /**
     * Tworzy nowy panel do rysowania obiektow
     * @param board Obiekt klasy Board
     */
    JPanelSimulation(Board board){
        this.board = board;
        JFrame frame = new JFrame("Ants Simulation");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2, 0);
        frame.setSize(dim.width/2,dim.height-50);

        frame.setVisible(true);
        frame.add(this);
    }

    /**
     * Metoda rysujaca obiekty na panelu
     * @param g  the <code>Graphics</code> context in which to paint
     */

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Anthill anthill;
        Ant ant;
        Food food;
        Flying_ant f_ant;
        Ant_eater ant_eater;
        Ant_Queen ant_queen;
        int width = this.getWidth()/board.size;
        int height = this.getHeight()/board.size;
        for (int i = 0; i < board.board.size(); i++) {
            if (board.board.get(i) != null) {
                for (int j = 0; j < board.board.get(i).size(); j++) {
                    if (board.board.get(i).get(j) != null) {
                        for (int k = 0; k < board.board.get(i).get(j).size(); k++) {
                            if (board.board.get(i).get(j).get(k) != null) {
                                if (board.board.get(i).get(j).get(k) instanceof Anthill) {
                                    anthill = (Anthill) board.board.get(i).get(j).get(k);

                                    if (anthill.is_main) {
                                        switch (anthill.id_anthill) {
                                            case 1 -> g.setColor(Color.BLUE);
                                            case 2 -> g.setColor(Color.red);
                                        }

                                    } else {
                                        g.setColor(Color.BLACK);
                                    }
                                    g.fillOval(anthill.x*width, anthill.y*height,  width+(width/3), height+(height/3));

                                }
                                else if (board.board.get(i).get(j).get(k) instanceof Flying_ant) {
                                    f_ant = (Flying_ant) board.board.get(i).get(j).get(k);
                                    switch(f_ant.anthill_id) {
                                        case 1 -> g.setColor(new Color(150,30,254));
                                        case 2 -> g.setColor(new Color(212,110,84));
                                    }

                                    g.fillOval(f_ant.x*width,f_ant.y*height,width,height);
                                }
                                else if(board.board.get(i).get(j).get(k)instanceof Ant_Queen){
                                    ant_queen=(Ant_Queen)board.board.get(i).get(j).get(k);
                                    switch(ant_queen.anthill_id) {
                                        case 1 -> g.setColor(Color.GREEN);
                                        case 2 -> g.setColor(new Color(255,160,0));
                                    }

                                    g.fillOval(ant_queen.x*width,ant_queen.y*height,width,height);
                                }
                                else if (board.board.get(i).get(j).get(k) instanceof Ant) {
                                    ant = (Ant) board.board.get(i).get(j).get(k);
                                    switch(ant.anthill_id) {
                                        case 1 -> g.setColor(Color.CYAN);
                                        case 2 -> g.setColor(Color.yellow);
                                    }

                                    g.fillOval(ant.x*width,ant.y*height,width,height);
                                }
                                else if (board.board.get(i).get(j).get(k) instanceof Food) {
                                    food = (Food) board.board.get(i).get(j).get(k);
                                    switch(food.quality){
                                        case LOW -> g.setColor(Color.GRAY);
                                        case MEDIUM -> g.setColor(Color.DARK_GRAY);
                                        case HIGH -> g.setColor(Color.BLACK);
                                    }
                                    g.fillOval(food.x*width,food.y*height,width-(width/3), height-(height/3) );
                                }
                                else if (board.board.get(i).get(j).get(k) instanceof Ant_eater) {
                                    ant_eater = (Ant_eater) board.board.get(i).get(j).get(k);
                                    g.setColor(Color.MAGENTA);
                                    g.fillOval(ant_eater.x*width,ant_eater.y*height,width,height);
                                }
                            }

                        }
                    }
                }

            }
        }
    }
}
