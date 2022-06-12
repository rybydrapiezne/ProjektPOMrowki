package env;

import agents.Ant;
import agents.Ant_Queen;
import agents.Flying_ant;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Anthill implements Board_object {
    List<Ant> ants;
    Ant_Queen queen;
    boolean is_main;
    byte id_anthill;
    int width = 25;
    int height = 25;

    int x, y, x_max, y_max;

    public Anthill(boolean is_main, byte id_anthill, int ant_number, int x, int y,int x_max,int y_max) {
        this.is_main = is_main;
        this.id_anthill = id_anthill;
        this.x = x;
        this.y = y;
        this.x_max = x_max;
        this.y_max = y_max;
        ants = new ArrayList<>();
        for (int i = 0; i < ant_number; i++) {
            ants.add(new Ant(x, y, 1, 1,x_max,y_max, id_anthill));
        }


    }

    @Override
    public void do_smth() {

    }

    public void generate_ant(){
        Ant ant = new Ant(x,y, 1, 1, x_max,y_max, id_anthill);
        ants.add(ant);

    }

    public void generate_f_ant(){
        Flying_ant f_ant = new Flying_ant(x, y, 1, 1, x_max, y_max, id_anthill);
        ants.add(f_ant);
    }
    public void delete_ant(Ant ant)
    {
        ants.remove(ant);

    }
    public int ant_count() {

        return ants.size();
    }
    public Board_object get_ant(int s)
    {
        return ants.get(s);
    }

    @Override
    public int get_range() {
        return 0;
    }

    @Override
    public Point position() {
        return new Point(x,y);
    }

    @Override
    public String item_on() {
        return "Anthill" + id_anthill;
    }


    @Override
    public void paint_on_board(Graphics p) {

        if (is_main) {
            switch (id_anthill) {
                case 1 -> p.setColor(Color.BLUE);
                case 2 -> p.setColor(Color.red);
            }

        } else {
            p.setColor(Color.BLACK);
        }
        p.fillOval(x*20, y*20, width, height);
    }
}

