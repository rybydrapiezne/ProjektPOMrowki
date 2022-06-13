package env;

import java.awt.*;

public interface Board_object {


   void do_smth(int x,int y);//metoda pozwalająca na wykonanie np ruchu dla mrówki czy zestarzenia się jedzenia

   int get_range();
   Point position();



   void paint_on_board(Graphics p);
}
