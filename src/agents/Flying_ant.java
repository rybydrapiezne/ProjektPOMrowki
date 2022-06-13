package agents;

import java.awt.*;

public class Flying_ant extends Ant{
    public Flying_ant(int x,int y,int range,int health,int x_move_range,int y_move_range, byte anthill_id)
    {
        super(x,y,range,health+1,x_move_range,y_move_range, anthill_id );

    }
    @Override
    public void do_smth(int x,int y) {
        super.do_smth(x,y);
    }

    @Override
    public void paint_on_board(Graphics p) {
        switch(anthill_id) {
            case 1 -> p.setColor(new Color(150,30,254));
            case 2 -> p.setColor(new Color(212,110,84));
        }

        p.fillOval(x*20,y*20,20,20);
    }
}
