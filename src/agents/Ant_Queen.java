package agents;


public class Ant_Queen extends Ant {
    Ant_Queen(int x,int y,int direction,int range,int health,int x_move_range,int y_move_range)
    {
        super(x,y,direction,range+1,health+4,x_move_range,y_move_range);
    }
    @Override
    public void do_smth() {
        super.do_smth();
    }


    public void create_anthill()
    {

    }
}
