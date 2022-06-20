package env;

import agents.Ant_eater;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Klasa odpowiadajaca za nadzor calej symulacji
 */
public class Simulation implements ActionListener {
    private Board simulation_board;
    /**
     * Panel, na ktorym rysowane sa obiekty
     */
    final JPanelSimulation animsim;
    /**
     * Niebieskie mrowisko
     */
    Anthill anthill1;
    /**
     * Czerwone mrowisko
     */
    Anthill anthill2;
    /**
     * Rozmiar tablicy (planszy)
     */
    int size;
    /**
     * bazowa ilosc mrowkojadow
     */
    final int number_of_ant_eaters;
    /**
     * bazowa ilosc mrowek pojawiajacych sie na poczatku
     */
    int base_ant_count;
    /**
     * bazowa ilosc jedzenia na planszyq
     */
    final int base_food_cout;
    /**
     * Czas miedzy wykonaniem kolejnych petli
     */
    Timer animationtimer;
    /**
     * pobieranie z pliku
     */
    private File file=new File("scenario_1.txt");
    /**
     * pobieranie z pliku
     */
    private Scanner scanner;

    Simulation() throws FileNotFoundException {

        Ant_eater ant_eater;
        int anteater_x;
        int anteater_y;
        scanner=new Scanner(file);
        size=scanner.nextInt();
        simulation_board = new Board(size);
        base_ant_count = scanner.nextInt();
        base_food_cout=scanner.nextInt();
        number_of_ant_eaters = scanner.nextInt();
        anthill1 = new Anthill(true, (byte) 1, base_ant_count, 0, 0, simulation_board.size - 1, simulation_board.size - 1);
        anthill2 = new Anthill(true, (byte) 2, base_ant_count, simulation_board.size - 1, simulation_board.size - 1, simulation_board.size - 1, simulation_board.size - 1);
        simulation_board.set_Board_object(anthill1, anthill1.x, anthill1.y); //ustawienie obu mrowisk na planszy
        simulation_board.set_Board_object(anthill2, anthill2.x, anthill2.y);


        simulation_board.anthill_blue = anthill1;
        simulation_board.anthill_red = anthill2;
        Random rand = new Random();
        for (int i = 0; i < number_of_ant_eaters; i++) {
            do {
                anteater_x = rand.nextInt(0, simulation_board.size - 1);
                anteater_y = rand.nextInt(0, simulation_board.size - 1);
            }
            while (anteater_x == anthill1.x && anteater_x == anthill2.x && anteater_y == anthill1.x && anteater_y == anthill2.y);
            ant_eater = new Ant_eater(anteater_x, anteater_y, 1, 10, simulation_board.size - 1, simulation_board.size - 1);
            simulation_board.set_Board_object(ant_eater, anteater_x, anteater_y);
        }
        for (int i = 0; i < anthill1.ant_count(); i++) {
            simulation_board.set_Board_object(anthill1.get_ant(i), anthill1.get_ant(i).position().x, anthill1.get_ant(i).position().y);
            simulation_board.set_Board_object(anthill2.get_ant(i), anthill2.get_ant(i).position().x, anthill2.get_ant(i).position().y);

        }


        for (int i = 0; i < base_food_cout; i++) {
            Food food_object = new Food(Quality_Of_Food.HIGH, simulation_board.size);
            simulation_board.set_Board_object(food_object, food_object.x, food_object.y);
        }

        new JPanelStatistics(this);
        animsim = new JPanelSimulation(simulation_board);
        animationtimer = new Timer(100, this);
        animationtimer.start();
    }

    public static void main(String [] args) throws FileNotFoundException {
        new Simulation();
    }

    /**
     * Metoda sluzaca do wykonywania kolejnych petli symulacji
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if((anthill1.has_queen_appeared&&anthill1.ant_count()==0)||(anthill2.has_queen_appeared&&anthill2.ant_count()==0))
        {
            System.out.println("Koniec!");
            animationtimer.stop();
            new JPanelEnding( simulation_board);
            file=new File("Results.txt");
            try {
                PrintWriter printWriter=new PrintWriter(file);
                printWriter.println("Board size= "+size);
                printWriter.println("Number of ants at the beginning= "+number_of_ant_eaters);
                printWriter.println("Number of food at board= "+base_food_cout);
                printWriter.println("Number of ant eaters= "+number_of_ant_eaters);
                printWriter.println();
                if(anthill1.ant_number>0)
                printWriter.println("Number of ants in winning anthill1= "+anthill1.ant_count());
                else
                    printWriter.println("Number of ants in winning anthill2= "+anthill2.ant_count());
                printWriter.close();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        }
        else {
            simulation_board.proceed(this);
            animsim.repaint();
        }
    }

}
