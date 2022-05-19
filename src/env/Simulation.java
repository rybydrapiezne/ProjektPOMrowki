package env;

public class Simulation {
    Board simulation_board;
    Anthill anthill1;
    Anthill anthill2;
    Simulation()
    {
        simulation_board=new Board(100);
        anthill1=new Anthill(true,(byte)1,10);
        anthill1=new Anthill(true,(byte)2,10);
        simulation_board.set_Board_object(anthill1,0,0);
        simulation_board.set_Board_object(anthill2,99,99);
    }
}
