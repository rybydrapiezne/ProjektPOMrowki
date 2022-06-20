package agents;

import env.Board_object;

import java.awt.*;
import java.util.Random;

public abstract class Agent  implements Board_object{
    /**
     * Pozycja agenta x
     */
    public int x;
    /**
     * zmienna sluzaca do losowania koordynatow na ktore przesunie sie agent
     */
    static Random rand=new Random();
    /**
     * Pozycja agenta y
     */
    public int y;
    /**
     * zasieg poruszania sie i widzenia agentow
     */
    final int range;
    /**
     * "zdrowie" agenta (zwieksza szanse przezycia po wejsciu na pole z wrogim agentem)
     */
    final int health;
    /**
     * zasieg mozliwego poruszania (rozmiar planszy) w osi x
     */
    final int x_move_range;
    /**
     * zasieg mozliwego poruszania (rozmiar planszy) w osi y
     */
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

    /**
     * Metoda z interfejsu, ktora w przypadku klasy Agent i klas dziedziczacych po niej odpowiada za poruszanie sie po planszy (jedynie za zmiane parametrow x oraz y faktyczne poruszanie odbywa sie w klasie Board)
     * @param x pozycja x ewentualnego jedzenia uzywana tylko w klasie Ant i dziedziczacych po niej klasach
     * @param y pozycja y ewentualnego jedzenia uzywana tylko w klasie Ant i dziedziczacych po niej klasach
     */
    @Override
    public void do_smth(int x,int y)//poprzez zmiane swoich koordynatow pozwala na poruszanie sie po planszy
    {
        /**
         * sluzy do przypisania wylosowanej wartosci dla x a nastepnie sprawdzenia czy po przesunieciu agent nie wyjdzie poza zakres planszy
         */
        int x_temp;
        /**
         *  sluzy do przypisania wylosowanej wartosci dla y a nastepnie sprawdzenia czy po przesunieciu agent nie wyjdzie poza zakres planszy
         */
        int y_temp;
        do {
            x_temp=rand.nextInt(-range,range+1);
            y_temp=rand.nextInt(-range,range+1);
        }while((this.x+x_temp>x_move_range||this.x+x_temp<0||this.y+y_temp>y_move_range||this.y+y_temp<0)||(x_temp==0&&y_temp==0));
        this.x+=x_temp;
        this.y+=y_temp;

    }

    /**
     * Metoda zwraca zasieg agenta
     * @return wartosc pola range
     */
    @Override
    public int get_range() {
        return range;
    }

    /**
     * Metoda zwraca pozycje agenta w postaci Point
     * @return zwraca typ Point wskazujacy na pozycje agenta na plnaszy
     */
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
