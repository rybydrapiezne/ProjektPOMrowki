package env;

import java.util.ArrayList;
import java.util.List;

public class Board {
    List<Board_object>[][] board;
    int size;
    Anthill anthill_red;
    Anthill anthill_blue;
    Board(int size)
    {
        this.size=size;
        //board=new ArrayList[size][size];
        anthill_blue=new Anthill();
        anthill_red=new Anthill();
    }
    void draw_board()
    {
        for(int i=0;i<size;i++)
        {
            board[i][i].add(anthill_red); //nie przejmuj sie tym cos sobie tu tylko sprawdzalem
        }
    }
    public void proceed()
    {

    }
}
