package env;

import agents.Ant;
import agents.Ant_Queen;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Anthill implements Board_object {
    List<Ant> ants;
    Ant_Queen queen;
    boolean is_main;
    byte id_anthill;
    int width = 20;
    int height = 20;

    int x, y;

    public Anthill(boolean is_main, byte id_anthill, int ant_number, int x, int y) {
        this.is_main = is_main;
        this.id_anthill = id_anthill;
        this.x = x;
        this.y = y;
        ants = new ArrayList<>();
        for (int i = 0; i < ant_number; i++) {
            ants.add(new Ant(-1, -1, -1, 1, 1));
        }


    }

    @Override
    public void do_smth() {

    }

    public int ant_count() {

        return ants.size();
    }

    @Override
    public int get_range() {
        return 0;
    }

    @Override
    public Point position() {
        return null;
    }

    @Override
    public String item_on() {
        return "Anthill" + id_anthill;
    }


    @Override
    public void paint_on_board(Graphics p) {

        if (is_main) {
            switch (id_anthill) {
                case 1:
                    p.setColor(Color.BLUE);
                    break;
                case 2:
                    p.setColor(Color.red);
                    break;
            }

        } else {
            p.setColor(Color.BLACK);
        }
        p.fillOval(x, y, width, height);
    }
}

