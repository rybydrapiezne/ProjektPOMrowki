package agents;

import env.Board_object;

abstract class Agent  implements Board_object{
    int x;
    int y;
    int vector;
    int range;
    int health;
    Agent(int x,int y,int direction,int range,int health)
    {
        this.x=x;
        this.y=y;
        vector=direction;
        this.range=range;
        this.health=health;
    }
    public void do_smth()
    {

    }
    public void chk_surr()
    {

    }
}
