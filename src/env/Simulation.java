package env;

public class Simulation {
    Board simulation_board;
    Anthill anthill1;
    Anthill anthill2;
    Simulation()
    {
        simulation_board=new Board(10);
        anthill1=new Anthill(true,(byte)1,10);
        anthill2=new Anthill(true,(byte)2,10);
        simulation_board.set_Board_object(anthill1,0,0);//ustawienie obu mrowisk na planszy
        simulation_board.set_Board_object(anthill2,9,9);
    }
    public static void main(String [] args)
    {

        Simulation sup_sim=new Simulation();
        for(int i=0;i<sup_sim.simulation_board.board.size();i++)//wyswietlanie tekstowe planszy
        {
            if(sup_sim.simulation_board.board.get(i)!=null) {
                for (int j = 0; j < sup_sim.simulation_board.board.get(i).size(); j++) {
                    if (sup_sim.simulation_board.board.get(i).get(j) != null) {
                        for (int k = 0; k < sup_sim.simulation_board.board.get(i).get(j).size(); k++) {
                            if (sup_sim.simulation_board.board.get(i).get(j).get(k) != null)
                                System.out.print(sup_sim.simulation_board.board.get(i).get(j).get(k).item_on());
                            else
                                System.out.print(0);
                        }
                    }
                }
                System.out.println();
            }
        }

    }
}
