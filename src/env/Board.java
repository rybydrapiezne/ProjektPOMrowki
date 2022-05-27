package env;

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

        //------GUI---
        /*JFrame frame = new JFrame("Ants Simulation");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setVisible(true);
        frame.add(this);*/

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
    public void proceed()
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


}
