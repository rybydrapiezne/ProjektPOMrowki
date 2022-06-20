package agents;

public class Ant_eater extends Agent {
   public Ant_eater(int x, int y, int range, int health,int x_move_range,int y_move_range)
    {
        super(x,y,range,health,x_move_range,y_move_range);
    }

    /**
     * zadnych zmian w porównaniu z klasa nadrzedna
     * @param x pozycja x
     * @param y pozycja y
     */
    @Override
    public void do_smth(int x,int y) {
        super.do_smth(x,y);
    }
}
