package env;

import agents.Ant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.math.*;
import java.util.Random;

public class Board extends JPanel {
    ArrayList<ArrayList<ArrayList<Board_object>>> board;
    int size;
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


        int id;
        for(int i=0;i<board.size();i++)
        {
            if(board.get(i)!=null) {
                for (int j = 0; j < board.get(i).size(); j++) {
                    if (board.get(i).get(j) != null) {
                        id=ant_eats_food(board.get(i).get(j),sim);
                        if( id ==1){
                            sim.anthill1.generate_ant();
                            set_Board_object(sim.anthill1.get_ant(sim.anthill1.ant_count()-1),sim.anthill1.x,sim.anthill1.y);
                        }
                        if(id ==2){
                            sim.anthill2.generate_ant();
                            set_Board_object(sim.anthill2.get_ant(sim.anthill2.ant_count()-1),sim.anthill2.x,sim.anthill2.y);
                        }
                        ant_collision(board.get(i).get(j),sim);
                    }
                }

            }
        }



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

//    public void delete_Board_object(Board_object thing){             - to sie chyba nie przyda
//        board.get(thing.position().x).get(thing.position().y).remove(thing);
//        board.get(thing.position().x).get(thing.position().y).trimToSize();
//    }
    public void ant_collision(ArrayList<Board_object>list, Simulation sim)
    {
        Ant ant1=null;
        Ant ant2=null;
        int one;
        int second;
        Random rand=new Random();
        for(int i=0;i<list.size();i++)
        {

            if(list.get(i)!=null) {

                if (ant1 == null) {
                    if(list.get(i) instanceof Ant)
                    {
                        ant1=(Ant)list.get(i);
                        continue;
                    }
                }
                if(ant2==null)
                {
                    if(list.get(i) instanceof  Ant)
                    {
                        if(((Ant) list.get(i)).anthill_id!=ant1.anthill_id)
                        {
                            ant2=(Ant)list.get(i);
                        }
                    }
                }
            }

        }
        if(ant1==null||ant2==null)
            return;
        if(sim.anthill1.id_anthill==ant1.anthill_id)
        {
            do {
                one = rand.nextInt(0, sim.anthill1.ant_count());
                second = rand.nextInt(0, sim.anthill2.ant_count());
            }while(one==second);
            if(one>second)
            {
                sim.anthill2.delete_ant(ant2);
                board.get(ant2.x).get(ant2.y).remove(ant2);
                board.trimToSize();
            }
            else
            {
                sim.anthill1.delete_ant(ant1);
                board.get(ant1.x).get(ant1.y).remove(ant1);
                board.trimToSize();
            }

        }
        else
        {
            do {
                one = rand.nextInt(0, sim.anthill2.ant_count());
                second = rand.nextInt(0, sim.anthill1.ant_count());
            }while(one==second);
            if(one>second)
            {
                sim.anthill1.delete_ant(ant2);
                board.get(ant2.x).get(ant2.y).remove(ant2);
                board.trimToSize();
            }
            else
            {
                sim.anthill2.delete_ant(ant1);
                board.get(ant1.x).get(ant1.y).remove(ant1);
                board.trimToSize();
            }
        }
        System.out.println("Kolizja mrowek");

    }

    public int ant_eats_food(ArrayList<Board_object> list, Simulation sim){        //kolizja mrowki i jedzenia

        Ant ant = null;
        Food food = null;
        for(Board_object object: list){

            if(object != null) {
                if(object instanceof Ant){
                    ant = (Ant)object;
                }
                if(object instanceof Food){
                    food = (Food)object;
                }

            }
        }

        if(ant!=null && food!=null){
            board.get(food.position().x).get(food.position().y).remove(food);
            Food food_one = new Food(Quality_Of_Food.HIGH, size);
            set_Board_object(food_one, food_one.x, food_one.y);


            System.out.println("\n Ant eats food, anthillid: "+ ant.anthill_id+ "\n");      //sprawdzanie czy dziala, mozna potem usunac



            return ant.anthill_id;
        }
        return -1;
    }

}
