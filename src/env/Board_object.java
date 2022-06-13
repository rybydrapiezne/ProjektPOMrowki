package env;

import java.awt.*;

public interface Board_object {


   void do_smth(int x,int y);

   int get_range();
   Point position();
   String item_on();//metoda sluzaca do wyswietlania w pozniejszych etapach jej nie bedzie


   void paint_on_board(Graphics p);
}
