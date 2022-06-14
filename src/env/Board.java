package env;

import agents.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board extends JPanel {
    ArrayList<ArrayList<ArrayList<Board_object>>> board;
   final int size;

    Anthill anthill_red;
    Anthill anthill_blue;


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
        frame.setSize(size*21,size*22);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setLocation(dim.width/2, dim.height/2-frame.getSize().height/2);

        frame.setVisible(true);
        frame.add(this);

    }
    /*void draw_board()
    {
        //wyswietlanie tekstowe planszy
        System.out.println("Board");
        for (ArrayList<ArrayList<Board_object>> arrayLists : board) {
            if (arrayLists != null) {
                for (int j = 0; j < arrayLists.size(); j++) {
                    if (arrayLists.get(j) != null) {
                        for (int k = 0; k < arrayLists.get(j).size(); k++) {
                            if (arrayLists.get(j).get(k) != null)
                                if(arrayLists.get(j).get(k)  instanceof Agent) {
                                    System.out.print(arrayLists.get(j).get(k) + " " );

                                }
                          //  else
                          //      System.out.print(0);
                        }
                    }
                }
                System.out.println();
            }
        }
        System.out.println("ENDBOARD");
    }*/
    public void proceed(Simulation sim)
    {
        int x_temp=-1;
        int y_temp=-1;
        List<Board_object> list_temp;
        for(int i=0;i<board.size();i++)
        {
            if(board.get(i)!=null) {
                for (int j = 0; j < board.get(i).size(); j++) {

                    if (board.get(i).get(j) != null) {
                        for (int k = 0; k < board.get(i).get(j).size(); k++) {
                            if (board.get(i).get(j).get(k) != null) {
                               list_temp = chk_surr(board.get(i).get(j).get(k));
                                for (int o = 0; o < list_temp.size(); o++) {
                                    if (list_temp.get(o) instanceof Food) {
                                        x_temp = ((Food) list_temp.get(o)).x;
                                        y_temp = ((Food) list_temp.get(o)).y;
                                        break;
                                    }
                                }

                                board.get(i).get(j).get(k).do_smth(x_temp, y_temp);
                                x_temp = -1;
                                y_temp = -1;
                                release_queen(sim);
                                move_Board_object(board.get(i).get(j).get(k), new Point(i, j));
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


        anthill_red.history.add(anthill_red.ant_count());
        anthill_blue.history.add(anthill_blue.ant_count());




    }
     public List<Board_object> chk_surr(Board_object item)//metoda sprawdzajaca sasiadow i zwracajaca liste wszystkich sasiadow (musze sie jeszcze zastanowic czy jest dobrze)
    {
        ArrayList<Board_object> surroundings=new ArrayList<>();
        Point position=item.position();
               int range=item.get_range();
                   for(int j= Math.max(position.x-range,0);j< Math.min(position.x+range,size);j++)
                   {
                       for(int k= Math.max(position.y-range,0);k< Math.min(position.y+range,size);k++) {
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
            board.get(x).get(y).clear();
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
        Ant_Queen ant_queen1=null;
        Ant_Queen ant_queen2=null;
        int one;
        int second;
        Random rand = new Random();
        for (int i = 0; i < list.size(); i++) {

            if (list.get(i) != null) {
                if(ant_queen1==null)
                {
                    if(list.get(i) instanceof Ant_Queen)
                    {
                        if(((Ant_Queen) list.get(i)).anthill_id==1)
                        {
                           ant_queen1=(Ant_Queen) list.get(i);
                        }
                        if(((Ant_Queen) list.get(i)).anthill_id==2)
                        {
                            ant_queen2=(Ant_Queen) list.get(i);
                        }
                    }
                }
                if (ant1 == null) {
                    if (list.get(i) instanceof Ant) {
                        ant1 = (Ant) list.get(i);
                        continue;
                    }

                }
                if (ant2 == null) {
                    if (list.get(i) instanceof Ant) {
                        if (((Ant) list.get(i)).anthill_id != ant1.anthill_id) {
                            ant2 = (Ant) list.get(i);
                            continue;
                        }
                    }

                }
                if (list.get(i) instanceof Food) {
                    food = (Food) list.get(i);
                }
                if (list.get(i) instanceof Ant_eater) {
                    ant_eater = (Ant_eater) list.get(i);
                }
            }

        }
        if (ant1 != null && ant2 != null) {//walka mrówek
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


        if ((ant1 != null || ant2 != null||ant_queen1!=null||ant_queen2!=null)) //mrowka wchodzi na pole z jedzeniem
            {
            if (ant1 != null)
                ant_temp = ant1;
            else
                ant_temp = ant2;
            if (food != null) {
                if (ant_temp != null) {
                    id = ant_temp.anthill_id;
                    if (id == 1) {
                        if(sim.anthill1.is_main) {
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
                        }
                    if (id == 2) {
                        if(sim.anthill2.is_main) {
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


                    }

                    if(ant_queen1!=null)//w przypadku gdy mrowisko nie istnieje pojawia sie królowa
                    {
                        sim.anthill1.create_anthill(ant_queen1.x, ant_queen1.y);
                        set_Board_object(sim.anthill1,sim.anthill1.x,sim.anthill1.y);
                        board.get(ant_queen1.x).get(ant_queen1.y).remove(ant_queen1);
                        sim.anthill1.delete_ant(ant_queen1);
                        for(int i=0;i<sim.anthill1.ant_count();i++)
                        {
                            set_Board_object(sim.anthill1.get_ant(i),sim.anthill1.x,sim.anthill1.y);
                        }
                        ant_queen1=null;
                    }
                    if(ant_queen2!=null) {
                        sim.anthill2.create_anthill(ant_queen2.x, ant_queen2.y);
                        set_Board_object(sim.anthill2, sim.anthill2.x, sim.anthill2.y);
                        board.get(ant_queen2.x).get(ant_queen2.y).remove(ant_queen2);
                        sim.anthill2.delete_ant(ant_queen2);
                        for (int i = 0; i < sim.anthill2.ant_count(); i++) {
                            set_Board_object(sim.anthill2.get_ant(i), sim.anthill2.x, sim.anthill2.y);

                        }
                        ant_queen2=null;
                    }
                    board.get(food.position().x).get(food.position().y).remove(food);
                    Food food_one=new Food(Quality_Of_Food.HIGH,size);
                    set_Board_object(food_one,food_one.x,food_one.y);
                    get_rid(sim.anthill1);
                    get_rid(sim.anthill2);
                }


            }
        }
        if(ant_eater!=null)//mrówka walczy z mrówkojadem
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
                        sim.anthill1.delete_ant(ant_temp);
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
                            sim.anthill2.delete_ant(ant_temp);
                            board.get(ant_temp.x).get(ant_temp.y).remove(ant_temp);
                            sim.anthill2.delete_ant(ant1);
                            board.trimToSize();
                        }
                    }
                }
            }

        }
    }

    public void release_queen(Simulation sim)//metoda sluzaca do wypuszczenia królowej z mrowiska i usunięcia mrowiska z planszy
    {
        Anthill anthill_temp=null;
        if(sim.anthill1.ant_count()==0) {

            anthill_temp = sim.anthill1;
        }
        if(sim.anthill2.ant_count()==0) {

            anthill_temp = sim.anthill2;
        }
        if(anthill_temp==null)
            return;
        if(!anthill_temp.has_queen_appeared)
        {
            anthill_temp.generate_queen();
            set_Board_object(anthill_temp.get_ant(anthill_temp.ant_count() - 1), anthill_temp.x, anthill_temp.y);
            anthill_temp.is_main = false;
            board.get(anthill_temp.x).get(anthill_temp.y).remove(anthill_temp);
        }

    }


    private void get_rid(Anthill anthill)//metoda naprawiająca pewnego buga w kodzie
    {
        boolean flag=false;
        for(int i=0;i<anthill.ant_count();i++)
        {
            for(int j=0;j<board.size();j++)
            {
                if(board.get(j)!=null)
                for(int k=0;k<board.get(j).size();k++)
                {
                    if(board.get(j).get(k)!=null)
                    for(int p=0;p<board.get(j).get(k).size();p++)
                    {
                        if(board.get(j).get(k).get(p)==anthill.get_ant(i))
                        {
                            flag=true;
                        }

                    }
                }
            }
            if(flag==false)
                anthill.delete_ant((Ant) anthill.get_ant(i));
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
        Ant_Queen ant_queen;


        for (int i = 0; i < board.size(); i++) {
            if (board.get(i) != null) {
                for (int j = 0; j < board.get(i).size(); j++) {
                    if (board.get(i).get(j) != null) {
                        for (int k = 0; k < board.get(i).get(j).size(); k++) {
                            if (board.get(i).get(j).get(k) != null) {
                                if (board.get(i).get(j).get(k) instanceof Anthill) {
                                    anthill = (Anthill) board.get(i).get(j).get(k);
                                    anthill.paint_on_board(g);
                                    if(anthill.id_anthill==1)
                                        g.drawString(Integer.toString(anthill.ant_count()),300,300);
                                    if(anthill.id_anthill==2)
                                        g.drawString(Integer.toString(anthill.ant_count()),300,250);

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
                                if(board.get(i).get(j).get(k)instanceof Ant_Queen){
                                    ant_queen=(Ant_Queen)board.get(i).get(j).get(k);
                                    ant_queen.paint_on_board(g);
                                }
                            }

                        }
                    }
                }

            }
        }
    }
}
