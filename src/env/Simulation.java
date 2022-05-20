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
        simulation_board=new Board(100);
     /*   anthill1=new Anthill(true,(byte)1,10);
        anthill2=new Anthill(true,(byte)2,10);
        simulation_board.set_Board_object(anthill1,0,0);//ustawienie obu mrowisk na planszy
        simulation_board.set_Board_object(anthill2,9,9);*/


        JFrame frame = new JFrame("Ants Simulation");
        frame.setSize(simulation_board.size,simulation_board.size);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setVisible(true);
        frame.add(this);

        anthill1=new Anthill(true,(byte)1,10, 0, 0);
        anthill2=new Anthill(true,(byte)2,10, simulation_board.size-1, simulation_board.size-1);
        simulation_board.set_Board_object(anthill1, anthill1.x, anthill1.y); //ustawienie obu mrowisk na planszy
        simulation_board.set_Board_object(anthill2, anthill2.x, anthill2.y);
        anthills.add(anthill1);
        anthills.add(anthill2);

        for(int i = 0; i< (simulation_board.size/3); i++){
            Food food_object = new Food(Quality_Of_Food.HIGH, simulation_board.size);
            simulation_board.set_Board_object(food_object, food_object.x, food_object.y);

            food.add(food_object);
        }

    }

    public static void main(String [] args)
    {

        Simulation sup_sim=new Simulation();
        for(int i=0;i<sup_sim.simulation_board.board.size();i++)//wyswietlanie tekstowe planszy
        {
            if(sup_sim.simulation_board.board.get(i)!=null) {
                for (int j = 0; j < sup_sim.simulation_board.board.get(i).size(); j++) {
                    if (sup_sim.simulation_board.board.get(i).get(j) != null) {
                        for (int k = 0; k < sup_sim.simulation_board.board.get(i).get(j).size(); k++) {
                            if (sup_sim.simulation_board.board.get(i).get(j).get(k) != null)
                                System.out.print(sup_sim.simulation_board.board.get(i).get(j).get(k).item_on());
                            else
                                System.out.print(0);
                        }
                    }
                }
                System.out.println();
            }
        }

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
