package env;

import java.awt.*;
import java.util.Random;

enum Quality_Of_Food  {
    LOW,MEDIUM,HIGH


}
/**
 * Klasa odpowiadajaca za jedzenie
 */
public class Food implements Board_object  {
    /**
     * Jakosc jedzenia
     */
    Quality_Of_Food quality;
    /**
     * sluzy do postarzania jedzenia
     */
    private int iteration;
    /**
     * pozycja jedzenia
     */
    int x, y, board_size;


    Food(Quality_Of_Food quality, int board_size)
    {
        this.quality=quality;
        this.board_size = board_size;
        Random rand=new Random();
            x=rand.nextInt(0,board_size-1);
            y=rand.nextInt(0,board_size-1);
        iteration=0;
    }

    /**
     * Metoda sluzaca do starzenia jedzenia
     * @param x
     * @param y
     */
    @Override
    public void do_smth(int x,int y) {
        iteration++;
        switch (iteration) {
            case (20) -> quality = Quality_Of_Food.MEDIUM;
            case (40) -> quality = Quality_Of_Food.LOW;
        }
    }

    @Override
    public int get_range() {
        return 0;
    }

    @Override
    public Point position() {
        return new Point(x,y);
    }

}
