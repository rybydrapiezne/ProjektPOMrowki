package agents;

import env.Board_object;

import java.awt.*;
import java.util.Random;

public abstract class Agent  implements Board_object{
    public int x;
    static Random rand=new Random();
    public int y;
    int vector;
    final int range;
    private
    int health;
    int x_move_range;
    int y_move_range;
    Agent(int x,int y,int range,int health,int x_move_range,int y_move_range)
    {
        this.x=x;
        this.y=y;
        this.range=range;
        this.health=health;
        this.x_move_range=x_move_range;
        this.y_move_range=y_move_range;
    }
    @Override
    public void do_smth(int x,int y)
    {
        int x_temp;
        int y_temp;
        do {
            x_temp=rand.nextInt(-range,range+1);
            y_temp=rand.nextInt(-range,range+1);
        }while((this.x+x_temp>x_move_range||this.x+x_temp<0||this.y+y_temp>y_move_range||this.y+y_temp<0)||(x_temp==0&&y_temp==0));
        this.x+=x_temp;
        this.y+=y_temp;
        //System.out.println(x);
        //System.out.println(y);
    }

    public void PrintAgent()
    {
        System.out.println("x="+x+" y="+y);
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

    public int getHealth(){ return health;}

    @Override
    public void paint_on_board(Graphics p){}
}
