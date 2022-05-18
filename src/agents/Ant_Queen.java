package agents;


public class Ant_Queen extends Ant {
    Ant_Queen(int x,int y,int direction,int range,int health)
    {
        super(x,y,direction,range+1,health+4);
    }
    @Override
    public void do_smth() {
        super.do_smth();
    }

    @Override
    public void chk_surr() {
        super.chk_surr();
    }
    public void create_anthill()
    {

    }
}
