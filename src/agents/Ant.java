package agents;

public class Ant extends Agent  {
    /**
     * id mrowiska do ktorego dana mrowka nalezy
     */
    final public byte anthill_id;

    public Ant(int x, int y, int range, int health,int x_move_range,int y_move_range, byte anthill_id)
    {
        super(x,y,range,health,x_move_range,y_move_range);
        this.anthill_id = anthill_id;
    }

    /**
     * rozszerzenie metody z klasy Agent z interfejsu Board_object. Metda na wejsciu dostaje ewentualna pozycje jedzenia w zasiegu jej widoku (pole "range), jezeli przyjela wartosci -1,-1 oznacza to, ze nie ma w poblizu jedzenia
     * @param x ewemtualna pozycja x jedzenia
     * @param y ewentualna pozycja y jedzenia
     */
    @Override
    public void do_smth(int x,int y) {
        if(x==-1&&y==-1)
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
