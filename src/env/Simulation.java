package env;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Simulation extends JPanel {
    Board simulation_board;
    Anthill anthill1;
    Anthill anthill2;

    ArrayList<Food> food = new ArrayList<>();
    ArrayList<Anthill> anthills = new ArrayList<>();
    Simulation()
    {
        simulation_board=new Board(10);
     /*   anthill1=new Anthill(true,(byte)1,10);
        anthill2=new Anthill(true,(byte)2,10);
        simulation_board.set_Board_object(anthill1,0,0);//ustawienie obu mrowisk na planszy
        simulation_board.set_Board_object(anthill2,9,9);*/


        JFrame frame = new JFrame("Ants Simulation");
        frame.setSize(simulation_board.size,simulation_board.size);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setVisible(true);
        frame.add(this);

        anthill1=new Anthill(true,(byte)1,10, 0, 0,10,10);
        anthill2=new Anthill(true,(byte)2,10, simulation_board.size-1, simulation_board.size-1,10,10);
        simulation_board.set_Board_object(anthill1, anthill1.x, anthill1.y); //ustawienie obu mrowisk na planszy
        simulation_board.set_Board_object(anthill2, anthill2.x, anthill2.y);
        for(int i=0;i<anthill1.ant_count();i++)
        {
            simulation_board.set_Board_object(anthill1.get_ant(i),anthill1.get_ant(i).position().x,anthill1.get_ant(i).position().y);
        }
        //anthills.add(anthill1);??
        //anthills.add(anthill2);??

        for(int i = 0; i< (simulation_board.size/3); i++){
            Food food_object = new Food(Quality_Of_Food.HIGH, simulation_board.size);
            simulation_board.set_Board_object(food_object, food_object.x, food_object.y);

            //food.add(food_object); ?? po co to
        }

    }

    public static void main(String [] args)
    {

        Simulation sup_sim=new Simulation();
      sup_sim.simulation_board.draw_board();
      sup_sim.simulation_board.proceed();
      sup_sim.simulation_board.draw_board();
      sup_sim.simulation_board.proceed();
      sup_sim.simulation_board.draw_board();

    }

    public void paint(Graphics p){
        for (Food f: food){
            f.paint_on_board(p);
        }

        for (Anthill ah: anthills) {
            ah.paint_on_board(p);
        }
    }

}
