package env;

import agents.Ant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.math.*;
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
        for(int i=0;i<board.size();i++)//wyswietlanie tekstowe planszy
        {
            if(board.get(i)!=null) {
                for (int j = 0; j < board.get(i).size(); j++) {
                    if (board.get(i).get(j) != null) {
                        for (int k = 0; k < board.get(i).get(j).size(); k++) {
                            if (board.get(i).get(j).get(k) != null)
                                System.out.print(board.get(i).get(j).get(k).item_on());
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
        for(int i=0;i<board.size();i++)//wyswietlanie tekstowe planszy
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
                        ant_eats_food(board.get(i).get(j),sim);
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


    public void ant_eats_food(ArrayList<Board_object> list, Simulation sim){        //kolizja mrowki i jedzenia

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

            if( ant.anthill_id ==1){
                Ant ant_one = sim.anthill1.generate_ant();
                set_Board_object(ant_one,ant_one.position().x,ant_one.position().y);
            }
            if(ant.anthill_id ==2){
                Ant ant_one = sim.anthill2.generate_ant();
                set_Board_object(ant_one,ant_one.position().x,ant_one.position().y);
            }


        }
    }

}
