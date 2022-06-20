package agents;

public class Ant_Queen extends Ant {
    public Ant_Queen(int x, int y, int range, int health, int x_move_range, int y_move_range, byte anthill_id)
    {
    super(x,y,range+1,health+4,x_move_range,y_move_range, anthill_id);
    }
    /**
     * zadnych zmian w porownaniu z klasa nadrzedna
     * @param x pozycja x
     * @param y pozycja y
     */
    @Override
    public void do_smth(int x,int y) {
        super.do_smth(x,y);
    }
}
