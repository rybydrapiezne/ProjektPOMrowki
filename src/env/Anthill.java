package env;

import agents.Ant;
import agents.Ant_Queen;

import java.util.List;

public class Anthill implements Board_object {
    List<Ant> ants;
    Ant_Queen queen;
    boolean is_main;
    byte id_anthill;

    @Override
    public void do_smth() {

    }
    public int ant_count()
    {

        return ants.size();
    }
}
