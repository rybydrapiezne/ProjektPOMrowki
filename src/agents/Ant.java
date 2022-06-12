package agents;


import java.awt.*;

public class Ant extends Agent  {
    public byte anthill_id;
    public Ant(int x, int y, int range, int health,int x_move_range,int y_move_range, byte anthill_id)
    {
        super(x,y,range,health,x_move_range,y_move_range);
        this.anthill_id = anthill_id;
    }

    @Override
    public void do_smth() {
        super.do_smth();
    }


    @Override
    public String item_on() {//jak juz pisalem wczesniej tylko robocza metoda do mozliwosci wyswietlenia planszy w trybie tekstowym
        String k;
        k="Ant ";
        k+=anthill_id;
        return k;
    }

    //---GUI---
    @Override
    public void paint_on_board( Graphics p){
        switch(anthill_id) {
            case 1 -> p.setColor(Color.CYAN);
            case 2 -> p.setColor(Color.ORANGE);
        }

        p.fillOval(x*20,y*20,20,20);
    }


}
