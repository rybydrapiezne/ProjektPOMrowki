package env;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.math.*;
public class Board {
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

    }
    public void proceed()
    {

    }
     public List<Board_object> chk_surr(Board_object item)
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
    public void set_Board_object(Board_object thing,int x,int y)
    {
        if(board.get(x).get(y)==null)
        {
            board.get(x).get(y).add(thing);
        }
    }
    public static void main(String [] args)
    {
        Board x=new Board(10);
    }
}
