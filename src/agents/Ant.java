package agents;


public class Ant extends Agent  {
    byte anthill_id;
    public Ant(int x, int y, int direction, int range, int health)
    {
        super(x,y,direction,range,health);
    }

    @Override
    public void do_smth() {
        super.do_smth();
    }


    @Override
    public String item_on() {//jak juz pisalem wczesniej tylko robocza metoda do mozliwosci wyswietlenia planszy w trybie tekstowym
        String k;
        k="Ant ";
        k+=anthill_id;
        return k;
    }
}
