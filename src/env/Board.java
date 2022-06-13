package env;

import agents.Ant;
import agents.Ant_eater;
import agents.Flying_ant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board extends JPanel {
    ArrayList<ArrayList<ArrayList<Board_object>>> board;
    int size;
    Anthill anthill_red;
    Anthill anthill_blue;

    ArrayList<Point> points;

    Board(int size)
    {
        this.size=size;
        board=new ArrayList<>();
        for(int i=0;i<size;i++)
        {
            board.add(new ArrayList<>());
            for(int j=0;j<size;j++) {
                board.get(i).add(new ArrayList<>());
                board.get(i).get(j).add(null);
            }
            }

        JFrame frame = new JFrame("Ants Simulation");
        frame.setSize(size*27,size*22);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setVisible(true);
        frame.add(this);

    }
    void draw_board()
    {
        //wyswietlanie tekstowe planszy
        for (ArrayList<ArrayList<Board_object>> arrayLists : board) {
            if (arrayLists != null) {
                for (int j = 0; j < arrayLists.size(); j++) {
                    if (arrayLists.get(j) != null) {
                        for (int k = 0; k < arrayLists.get(j).size(); k++) {
                            if (arrayLists.get(j).get(k) != null)
                                System.out.print(arrayLists.get(j).get(k).item_on());
                            else
                                System.out.print(0);
                        }
                    }
                }
                System.out.println();
            }
        }
        System.out.println();
    }
    public void proceed(Simulation sim)
    {
        for(int i=0;i<board.size();i++)
        {
            if(board.get(i)!=null) {
                for (int j = 0; j < board.get(i).size(); j++) {
                    if (board.get(i).get(j) != null) {
                        for (int k = 0; k < board.get(i).get(j).size(); k++) {
                            if (board.get(i).get(j).get(k) != null)
                            {
                                board.get(i).get(j).get(k).do_smth();
                                move_Board_object(board.get(i).get(j).get(k),new Point(i,j));
                            }

                        }
                    }
                }

            }
        }



        for(int i=0;i<board.size();i++)
        {
            if(board.get(i)!=null) {
                for (int j = 0; j < board.get(i).size(); j++) {
                    if (board.get(i).get(j) != null) {


                        ant_collision(board.get(i).get(j),sim);
                    }
                }

            }
        }

        System.out.println("1: " + anthill_red.ant_count() + " 2: " + anthill_blue.ant_count());
        anthill_red.history.add(anthill_red.ant_count());
        anthill_blue.history.add(anthill_blue.ant_count());



    }
     public List<Board_object> chk_surr(Board_object item)//metoda sprawdzajaca sasiadow i zwracajaca liste wszystkich sasiadow (musze sie jeszcze zastanowic czy jest dobrze)
    {
        ArrayList<Board_object> surroundings=new ArrayList<>();
        Point position=item.position();
               int range=item.get_range();
                   for(int j= Math.max(position.x-range,0);j<= Math.min(position.x+range,size);j++)
                   {
                       for(int k= Math.max(position.y-range,0);k<= Math.min(position.y+range,size);k++) {
                           if(j!= position.x&&k!=position.y) {
                               if (board.get(j).get(k)!=null) {

                                   surroundings.addAll(board.get(j).get(k));

                               }
                           }
                       }


               }
                   return surroundings;
    }
    public void set_Board_object(Board_object thing,int x,int y)//metoda sluzaca do wpisania obiektu do listy
    {
        if(board.get(x).get(y).contains(null))
        {
            board.get(x).get(y).clear();
            board.get(x).get(y).add(thing);
        }
        else
            board.get(x).get(y).add(thing);

    }
    public void move_Board_object(Board_object thing,Point old_position)
    {
        if(thing.position()!=null)
        {
            if(thing.position().x!=old_position.x||thing.position().y!=old_position.y)
            {
                board.get(old_position.x).get(old_position.y).remove(thing);
                board.get(old_position.x).get(old_position.y).trimToSize();
                board.get(thing.position().x).get(thing.position().y).add(thing);
            }
        }

    }

    public void ant_collision(ArrayList<Board_object>list, Simulation sim) {
        int id;
        Ant ant1 = null;
        Ant ant2 = null;
        Ant ant_temp = null;
        Food food = null;
        Ant_eater ant_eater = null;
        int one;
        int second;
        Random rand = new Random();
        for (int i = 0; i < list.size(); i++) {

            if (list.get(i) != null) {

                if (ant1 == null) {
                    if (list.get(i) instanceof Ant) {
                        ant1 = (Ant) list.get(i);
                        continue;
                    }
//                    if (list.get(i) instanceof Flying_ant) {
//                        ant1 = (Flying_ant) list.get(i);
//                        continue;
//                    }
                }
                if (ant2 == null) {
                    if (list.get(i) instanceof Ant) {
                        if (((Ant) list.get(i)).anthill_id != ant1.anthill_id) {
                            ant2 = (Ant) list.get(i);
                            continue;
                        }
                    }
//                    if (list.get(i) instanceof Flying_ant) {
//                        if (((Ant) list.get(i)).anthill_id != ant1.anthill_id) {
//                            ant2 = (Flying_ant) list.get(i);
//                        }
//                    }
                }
                if (list.get(i) instanceof Food) {
                    food = (Food) list.get(i);
                }
                if (list.get(i) instanceof Ant_eater) {
                    ant_eater = (Ant_eater) list.get(i);
                }
            }

        }
        if (ant1 != null && ant2 != null) {
            if (sim.anthill1.id_anthill == ant1.anthill_id) {
                do {
                    one = rand.nextInt(0, ant1.getHealth()*sim.anthill1.ant_count());
                    second = rand.nextInt(0, ant2.getHealth()*sim.anthill2.ant_count());
                } while (one == second);
                if (one > second) {
                    sim.anthill2.delete_ant(ant2);
                    board.get(ant2.x).get(ant2.y).remove(ant2);
                    board.trimToSize();
                    ant2 = null;
                } else {
                    sim.anthill1.delete_ant(ant1);
                    board.get(ant1.x).get(ant1.y).remove(ant1);
                    board.trimToSize();
                    ant1 = null;
                }

            } else {
                do {
                    one = rand.nextInt(0, ant1.getHealth()*sim.anthill2.ant_count());
                    second = rand.nextInt(0, ant2.getHealth()*sim.anthill1.ant_count());
                } while (one == second);
                if (one > second) {
                    sim.anthill1.delete_ant(ant2);
                    board.get(ant2.x).get(ant2.y).remove(ant2);
                    board.trimToSize();
                    ant2 = null;
                } else {
                    sim.anthill2.delete_ant(ant1);
                    board.get(ant1.x).get(ant1.y).remove(ant1);
                    board.trimToSize();
                    ant1 = null;
                }
            }
        }
        if ((ant1 != null || ant2 != null)) {
            if (ant1 != null)
                ant_temp = ant1;
            else
                ant_temp = ant2;
            if (food != null) {
                if (ant_temp != null) {
                    id = ant_temp.anthill_id;
                    if (id == 1) {
                        switch (food.quality) {
                            case LOW:
                                sim.anthill1.generate_ant();
                                set_Board_object(sim.anthill1.get_ant(sim.anthill1.ant_count() - 1), sim.anthill1.x, sim.anthill1.y);
                                break;
                            case MEDIUM: {
                                for (int i = 0; i < 2; i++) {
                                    sim.anthill1.generate_ant();
                                    set_Board_object(sim.anthill1.get_ant(sim.anthill1.ant_count() - 1), sim.anthill1.x, sim.anthill1.y);

                                }
                                break;
                            }
                            case HIGH: {
                                sim.anthill1.generate_f_ant();
                                set_Board_object(sim.anthill1.get_ant(sim.anthill1.ant_count() - 1), sim.anthill1.x, sim.anthill1.y);
                                break;
                            }

                        }
                    }
                    if (id == 2) {
                        switch (food.quality) {
                            case LOW:
                                sim.anthill2.generate_ant();
                                set_Board_object(sim.anthill2.get_ant(sim.anthill2.ant_count() - 1), sim.anthill2.x, sim.anthill2.y);
                                break;
                            case MEDIUM: {
                                for (int i = 0; i < 2; i++) {
                                    sim.anthill2.generate_ant();
                                    set_Board_object(sim.anthill2.get_ant(sim.anthill2.ant_count() - 1), sim.anthill2.x, sim.anthill2.y);

                                }
                                break;
                            }
                            case HIGH: {
                                sim.anthill2.generate_f_ant();
                                set_Board_object(sim.anthill2.get_ant(sim.anthill2.ant_count() - 1), sim.anthill2.x, sim.anthill2.y);
                                break;
                            }
                        }

                    }
                    board.get(food.position().x).get(food.position().y).remove(food);
                    Food food_one=new Food(Quality_Of_Food.HIGH,size);
                    set_Board_object(food_one,food_one.x,food_one.y);
                }
                //System.out.println("Kolizja mrowek");

            }
        }
        if(ant_eater!=null)
        {
            if(ant1!=null||ant2!=null)
            {
                if(ant1!=null)
                {
                    ant_temp=ant1;

                }
                if(ant2!=null)
                {
                    ant_temp=ant2;
                }
                if(ant_temp.anthill_id==1) {
                    do {
                        one = rand.nextInt(0, ant_temp.getHealth()*sim.anthill1.ant_count());
                        second = rand.nextInt(0, sim.base_ant_count * 5);

                    }
                    while (one == second);
                    if(one>second)
                    {
                        board.get(ant_eater.x).get(ant_eater.y).remove(ant_eater);
                        board.trimToSize();
                        for(int i=0;i<3;i++)
                        {
                            sim.anthill1.generate_ant();
                            set_Board_object(sim.anthill1.get_ant(sim.anthill1.ant_count()-1),sim.anthill1.x,sim.anthill1.y);
                        }
                    }
                    else
                    {
                        board.get(ant_temp.x).get(ant_temp.y).remove(ant_temp);
                        sim.anthill1.delete_ant(ant1);
                        board.trimToSize();
                    }
                }
                if(ant_temp.anthill_id==2)
                {
                    {
                        do {
                            one = rand.nextInt(0, ant_temp.getHealth()*sim.anthill2.ant_count());
                            second = rand.nextInt(0, sim.base_ant_count * 5);

                        }
                        while (one == second);
                        if(one>second)
                        {
                            board.get(ant_eater.x).get(ant_eater.y).remove(ant_eater);
                            board.trimToSize();
                            for(int i=0;i<3;i++)
                            {
                                sim.anthill2.generate_ant();
                                set_Board_object(sim.anthill2.get_ant(sim.anthill2.ant_count()-1),sim.anthill1.x,sim.anthill1.y);
                            }
                        }
                        else
                        {
                            board.get(ant_temp.x).get(ant_temp.y).remove(ant_temp);
                            sim.anthill2.delete_ant(ant1);
                            board.trimToSize();
                        }
                    }
                }
            }

        }
    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Anthill anthill;
        Ant ant;
        Food food;
        Flying_ant f_ant;
        Ant_eater ant_eater;


        for (int i = 0; i < board.size(); i++) {
            if (board.get(i) != null) {
                for (int j = 0; j < board.get(i).size(); j++) {
                    if (board.get(i).get(j) != null) {
                        for (int k = 0; k < board.get(i).get(j).size(); k++) {
                            if (board.get(i).get(j).get(k) != null) {
                                if (board.get(i).get(j).get(k) instanceof Anthill) {
                                    anthill = (Anthill) board.get(i).get(j).get(k);
                                    anthill.paint_on_board(g);
                                }
                                else if (board.get(i).get(j).get(k) instanceof Flying_ant) {
                                    f_ant = (Flying_ant) board.get(i).get(j).get(k);
                                    f_ant.paint_on_board(g);
                                }
                                else if (board.get(i).get(j).get(k) instanceof Ant) {
                                    ant = (Ant) board.get(i).get(j).get(k);
                                    ant.paint_on_board(g);
                                }
                                else if (board.get(i).get(j).get(k) instanceof Food) {
                                    food = (Food) board.get(i).get(j).get(k);
                                    food.paint_on_board(g);
                                }
                                else if (board.get(i).get(j).get(k) instanceof Ant_eater) {
                                    ant_eater = (Ant_eater) board.get(i).get(j).get(k);
                                    ant_eater.paint_on_board(g);
                                }
                            }

                        }
                    }
                }

            }
        }
    }
}
