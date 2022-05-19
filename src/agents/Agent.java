package agents;

import env.Board_object;

import java.awt.*;

abstract class Agent  implements Board_object{
    int x;
    int y;
    int vector;
    int range;
    int health;
    Agent(int x,int y,int direction,int range,int health)
    {
        this.x=x;
        this.y=y;
        vector=direction;
        this.range=range;
        this.health=health;
    }
    public void do_smth()
    {

    }
    public void chk_surr()
    {

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
}
