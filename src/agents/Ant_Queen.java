package agents;


import java.awt.*;

public class Ant_Queen extends Ant {
    public Ant_Queen(int x, int y, int range, int health, int x_move_range, int y_move_range, byte anthill_id)
    {
    super(x,y,range+1,health+4,x_move_range,y_move_range, anthill_id);
    }
    @Override
    public void do_smth(int x,int y) {
        super.do_smth(x,y);
    }



    @Override
    public void paint_on_board(Graphics p) {
        switch(anthill_id) {
            case 1 -> p.setColor(Color.GREEN);
            case 2 -> p.setColor(Color.GRAY);
        }

        p.fillOval(x*20,y*20,25,25);
    }
}
