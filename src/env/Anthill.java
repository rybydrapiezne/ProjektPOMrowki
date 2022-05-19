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

    public Anthill(boolean is_main,byte id_anthill,int ant_number)
    {
        this.is_main=is_main;
        this.id_anthill=id_anthill;
        ants=new ArrayList<>();
        for(int i=0;i<ant_number;i++)
        {
            ants.add(new Ant(-1,-1,-1,1,1));
        }
    }

    @Override
    public void do_smth() {

    }
    public int ant_count()
    {

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
}
