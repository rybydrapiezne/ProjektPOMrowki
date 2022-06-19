package agents;

import env.Board_object;

import java.awt.*;
import java.util.Random;

public abstract class Agent  implements Board_object{
    public int x;//pozycja agenta x
    static Random rand=new Random();
    public int y;//pozycja agenta y
    final int range;//zasieg poruszania sie i widzenia agentow
    final int health;
    final int x_move_range;//zasieg mozliwego poruszania (rozmiar planszy)
    final int y_move_range;
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
    public void do_smth(int x,int y)//poprzez zmiane swoich koordynatow pozwala na poruszanie sie po planszy
    {
        int x_temp;
        int y_temp;
        do {
            x_temp=rand.nextInt(-range,range+1);//losowanie w zależności od zasięgu miejsca do poruszania
            y_temp=rand.nextInt(-range,range+1);
        }while((this.x+x_temp>x_move_range||this.x+x_temp<0||this.y+y_temp>y_move_range||this.y+y_temp<0)||(x_temp==0&&y_temp==0));
        this.x+=x_temp;
        this.y+=y_temp;

    }


    @Override
    public int get_range() {
        return range;
    }

    @Override
    public Point position() {//metoda sluzaca do zwrocenia puntku w ktorym znajduje sie obiekt

        return new Point(x,y);
    }

    /**
     * Metoda zwracajaca wartosc pola health
     * @return wartosc pola health
     */
    public int getHealth(){ return health;}
}
