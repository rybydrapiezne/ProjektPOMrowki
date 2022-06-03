package env;

import agents.Ant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Simulation extends JPanel implements ActionListener {
    private Board simulation_board;
    Anthill anthill1;
    Anthill anthill2;
    Timer animationtimer;
    Simulation()
    {
        simulation_board=new Board(25);

        anthill1=new Anthill(true,(byte)1,10, 0, 0,simulation_board.size-1,simulation_board.size-1);
        anthill2=new Anthill(true,(byte)2,10, simulation_board.size-1, simulation_board.size-1,simulation_board.size-1,simulation_board.size-1);
        simulation_board.set_Board_object(anthill1, anthill1.x, anthill1.y); //ustawienie obu mrowisk na planszy
        simulation_board.set_Board_object(anthill2, anthill2.x, anthill2.y);
        for(int i=0;i<anthill1.ant_count();i++)
        {
            simulation_board.set_Board_object(anthill1.get_ant(i),anthill1.get_ant(i).position().x,anthill1.get_ant(i).position().y);
           simulation_board.set_Board_object(anthill2.get_ant(i),anthill2.get_ant(i).position().x,anthill2.get_ant(i).position().y);
        }

        for(int i = 0; i< (simulation_board.size/2); i++){
            Food food_object = new Food(Quality_Of_Food.HIGH, simulation_board.size);
            simulation_board.set_Board_object(food_object, food_object.x, food_object.y);
        }

        animationtimer = new Timer(100, this);
        animationtimer.start();
    }

    public static void main(String [] args)
    {
        Simulation sup_sim=new Simulation();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //simulation_board.draw_board();
        simulation_board.proceed(this);
        simulation_board.repaint();
//        System.out.println(anthill1.ant_count());
//        System.out.println(anthill2.ant_count());
    }
}
