package env;

import agents.Ant;
import agents.Ant_Queen;
import agents.Flying_ant;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Anthill implements Board_object {
   private List<Ant> ants;
    boolean is_main;
    byte id_anthill;
    int width = 25;
    int height = 25;
    boolean has_queen_appeared;
    int ant_number;

    int x, y, x_max, y_max;

    ArrayList<Integer> history = new ArrayList<Integer>();
    public Anthill(boolean is_main, byte id_anthill, int ant_number, int x, int y,int x_max,int y_max) {
        this.is_main = is_main;
        this.id_anthill = id_anthill;
        this.x = x;
        this.y = y;
        this.x_max = x_max;
        this.y_max = y_max;
        this.ant_number=ant_number;
        has_queen_appeared=false;
        ants = new ArrayList<>();
        for (int i = 0; i < ant_number; i++) {
            ants.add(new Ant(x, y, 1, 1,x_max,y_max, id_anthill));
        }


    }

    @Override
    public void do_smth(int x,int y) {

    }

    public void generate_ant(){
        Ant ant = new Ant(x,y, 1, 1, x_max,y_max, id_anthill);
        ants.add(ant);

    }
    public void printAnts()
    {
        for(int i=0;i<ant_count();i++)
        {
            System.out.print(ants.get(i));
            ants.get(i).PrintAgent();
            System.out.println();
        }
    }
    public void generate_f_ant(){
        Flying_ant f_ant = new Flying_ant(x, y, 1, 1, x_max, y_max, id_anthill);
        ants.add(f_ant);
    }
    public void generate_queen(){
        if(!has_queen_appeared) {
            Ant_Queen ant_queen = new Ant_Queen(x, y, 1, 1, x_max, y_max, id_anthill);
            ants.add(ant_queen);
        }
    }
    public void create_anthill(int x,int y)
    {
        if(!has_queen_appeared) {
            this.x = x;
            this.y = y;
            is_main = true;
            has_queen_appeared = true;
            for (int i = 0; i < ant_number; i++) {
                ants.add(new Ant(this.x, this.y, 1, 1, x_max, y_max, id_anthill));
            }
        }

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

