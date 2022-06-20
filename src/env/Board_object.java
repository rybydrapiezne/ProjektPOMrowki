package env;

import java.awt.*;

public interface Board_object {

   /**
    * metoda pozwalajaca na wykonanie np ruchu dla mrowki czy zestarzenia sie jedzenia
    * @param x
    * @param y
    */
   void do_smth(int x,int y);

   /**
    * Metoda sluzaca do uzyskania zasiegu na jaki dany agent moze sie poruszac i/lub widziec
    * @return
    */
   int get_range();

   /**
    * Metoda zwracajaca pozycje obiektu na planszy
    * @return
    */
   Point position();
}
