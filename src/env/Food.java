package env;

import java.awt.*;

enum Quality_Of_Food{
    LOW,MEDIUM,HIGH


}
public class Food implements Board_object  {
    Quality_Of_Food quality;
    Food(Quality_Of_Food quality)
    {
        this.quality=quality;
    }
    @Override
    public void do_smth() {

    }

    @Override
    public int get_range() {
        return 0;
    }

    @Override
    public Point position() {
        return null;
    }
}
