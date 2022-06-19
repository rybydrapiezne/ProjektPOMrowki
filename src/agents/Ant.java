package agents;

public class Ant extends Agent  {
    final public byte anthill_id;
    public Ant(int x, int y, int range, int health,int x_move_range,int y_move_range, byte anthill_id)
    {
        super(x,y,range,health,x_move_range,y_move_range);
        this.anthill_id = anthill_id;
    }

    @Override
    public void do_smth(int x,int y) {
        if(x==-1&&y==-1)//sprawdzanie czy w poblizu jest jedzenie
        super.do_smth(x,y);
        else
        {
            if(x<x_move_range||y<x_move_range) {
                this.x = x;
                this.y = y;
            }
        }
    }
}
