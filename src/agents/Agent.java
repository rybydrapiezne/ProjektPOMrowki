package agents;

import env.Board_object;

import java.awt.*;
import java.util.Random;

abstract class Agent  implements Board_object{
    public int x;

    public int y;
    int vector;
    private int range;
    private
    int health;
    Random rand;
    int x_move_range;
    int y_move_range;
    Agent(int x,int y,int range,int health,int x_move_range,int y_move_range)
    {
        this.x=x;
        this.y=y;
        this.range=range;
        this.health=health;
        rand=new Random();
        this.x_move_range=x_move_range;
        this.y_move_range=y_move_range;
    }
    public void do_smth()
    {
        int x_temp;
        int y_temp;
        do {
            x_temp=rand.nextInt(-range,range+1);
            y_temp=rand.nextInt(-range,range+1);
        }while((x+x_temp>x_move_range||x+x_temp<0||y+y_temp>y_move_range||y+y_temp<0)||(x_temp==0||y_temp==0));
        x+=x_temp;
        y+=y_temp;
        //System.out.println(x);
        //System.out.println(y);
    }


    @Override
    public int get_range() {
        return range;
    }

    @Override
    public Point position() {//metoda sluzaca do zwrocenia puntku w ktorym znajduje sie obiekt

        return new Point(x,y);
    }

    @Override
    public String item_on() {
        return null;
    }

    @Override
    public void paint_on_board(Graphics p){}
}
