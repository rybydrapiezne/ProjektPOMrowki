package agents;


import java.awt.*;

public class Ant_eater extends Agent {
   public Ant_eater(int x, int y, int range, int health,int x_move_range,int y_move_range)
    {
        super(x,y,range,health,x_move_range,y_move_range);
    }

    @Override
    public void do_smth(int x,int y) {
        super.do_smth(x,y);
    }

    public void paint_on_board( Graphics p){
         p.setColor(Color.MAGENTA);
        p.fillOval(x*20,y*20,20,20);
    }
}
