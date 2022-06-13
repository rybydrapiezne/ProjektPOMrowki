package env;

import agents.Ant;
import agents.Ant_eater;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import  javax.swing.SwingWorker;
public class Simulation extends JPanel implements ActionListener {
    private Board simulation_board;
    public JPanelStatistics stats;
    Anthill anthill1;
    int number_of_ant_eaters;
    int base_ant_count;
    Anthill anthill2;
    Ant_eater ant_eater;
    int base_food_cout;
    Timer animationtimer;

    Simulation() {


        int anteater_x;
        int anteater_y;
        simulation_board = new Board(25);
        number_of_ant_eaters = 5;
        base_ant_count = 10;
        base_food_cout=7;
        anthill1 = new Anthill(true, (byte) 1, base_ant_count, 0, 0, simulation_board.size - 1, simulation_board.size - 1);
        anthill2 = new Anthill(true, (byte) 2, base_ant_count, simulation_board.size - 1, simulation_board.size - 1, simulation_board.size - 1, simulation_board.size - 1);
        simulation_board.set_Board_object(anthill1, anthill1.x, anthill1.y); //ustawienie obu mrowisk na planszy
        simulation_board.set_Board_object(anthill2, anthill2.x, anthill2.y);

        simulation_board.anthill_red = anthill1;
        simulation_board.anthill_blue = anthill2;
        Random rand = new Random();
        for (int i = 0; i < number_of_ant_eaters; i++) {
            do {
                anteater_x = rand.nextInt(0, simulation_board.size - 1);
                anteater_y = rand.nextInt(0, simulation_board.size - 1);
            }
            while (anteater_x == anthill1.x && anteater_x == anthill2.x && anteater_y == anthill1.x && anteater_y == anthill2.y);
            ant_eater = new Ant_eater(anteater_x, anteater_y, 1, 10, simulation_board.size - 1, simulation_board.size - 1);
            simulation_board.set_Board_object(ant_eater, anteater_x, anteater_y);
        }
        for (int i = 0; i < anthill1.ant_count(); i++) {
            simulation_board.set_Board_object(anthill1.get_ant(i), anthill1.get_ant(i).position().x, anthill1.get_ant(i).position().y);
            simulation_board.set_Board_object(anthill2.get_ant(i), anthill2.get_ant(i).position().x, anthill2.get_ant(i).position().y);

        }
        System.out.println("k1 "+anthill1.ant_count());
        System.out.println("k2 "+anthill2.ant_count());
        System.out.println("k3 "+simulation_board.count_ants_on_board());

        for (int i = 0; i < base_food_cout; i++) {
            Food food_object = new Food(Quality_Of_Food.HIGH, simulation_board.size);
            simulation_board.set_Board_object(food_object, food_object.x, food_object.y);
        }

        stats = new JPanelStatistics(this);
        animationtimer = new Timer(100, this);
        animationtimer.start();
    }

    public static void main(String [] args)
    {


        Simulation sup_sim=new Simulation();
        JFrame graph = new JFrame("Graph");
        graph.setSize(300,400);
        graph.setVisible(true);
        graph.add(sup_sim.stats);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //simulation_board.draw_board();

        if((anthill1.has_queen_appeared&&anthill1.ant_count()==0)||(anthill2.has_queen_appeared&&anthill2.ant_count()==0))
        {
            System.out.println("Koniec!");
            return;
        }
        else {

            simulation_board.proceed(this);
            simulation_board.repaint();
        }

//        System.out.println(anthill1.ant_count());
//        System.out.println(anthill2.ant_count());
    }
}
