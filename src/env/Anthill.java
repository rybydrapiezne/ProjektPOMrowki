package env;

import agents.Ant;
import agents.Ant_Queen;
import agents.Flying_ant;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Anthill implements Board_object {
    /**
     * Lista mrowek znajdujacych sie w danym mrowisku, tzn. bedacych na planszy
     */
   private List<Ant> ants;
    /**
     * wstepnie miala odpowiadac za stwierdzanie czy mrowisko jest mrowiskiem glownym, na te chwile odpowiada za stwierdzenie czy mrowisko istnieje czy nie
     */
    boolean is_main;
    /**
     * id mrowiska
     */
    final byte id_anthill;
    /**
     * sluzy do stwierdzenia czy krolowa zostala juz postawiona w poprzednich iteracjach na planszy
     */
    boolean has_queen_appeared;
    /**
     * bazowa ilosc mrowek, tzn. ilosc mrowek jaka powstanie po stworzeniu mrowiska
     */
    final int ant_number;
    /**
     *  pozycja mrowiska oraz maksymalna wielkosc planszy
     */
    int x, y, x_max, y_max;
    /**
     * Ilosc mrowek w kazdej petli
     */
    protected ArrayList<Integer> history = new ArrayList<>();
    public Anthill(boolean is_main, byte id_anthill, int ant_number, int x, int y,int x_max,int y_max) {
        this.is_main = is_main;
        this.id_anthill = id_anthill;
        this.x = x;
        this.y = y;
        this.x_max = x_max;
        this.y_max = y_max;
        this.ant_number=ant_number;
        has_queen_appeared=false;
        ants = new ArrayList<>();
        for (int i = 0; i < ant_number; i++) {
            ants.add(new Ant(x, y, 1, 1,x_max,y_max, id_anthill));
        }


    }

    @Override
    public void do_smth(int x,int y) {

    }

    /**
     * Metoda tworzaca obiekt typu Ant i dodajaca go do listy mrowek mrowiska
     */
    public void generate_ant(){
        Ant ant = new Ant(x,y, 1, 1, x_max,y_max, id_anthill);
        ants.add(ant);

    }

    /**
     * Metoda tworzca obiekt typu Flying_ant i dodajaca go do listy mrowek mrowiska
     */
    public void generate_f_ant(){
        Flying_ant f_ant = new Flying_ant(x, y, 1, 1, x_max, y_max, id_anthill);
        ants.add(f_ant);
    }
    public void generate_queen(){
        if(!has_queen_appeared) {
            Ant_Queen ant_queen = new Ant_Queen(x, y, 1, 1, x_max, y_max, id_anthill);
            ants.add(ant_queen);
        }
    }

    /**
     * metoda sprawdzajaca czy pojawila sie juz krolowa i jezeli nie tworzy nowe mrowisko razem z nowymi mrowkami
     * @param x nowa pozycja x mrowiska
     * @param y nowa pozycja y mrowiska
     */
    public void create_anthill(int x,int y)
    {
        if(!has_queen_appeared) {
            this.x = x;
            this.y = y;
            is_main = true;
            has_queen_appeared = true;
            for (int i = 0; i < ant_number; i++) {
                ants.add(new Ant(this.x, this.y, 1, 1, x_max, y_max, id_anthill));
            }
        }

    }

    /**
     * usuwanie mrowki z listy mrowek mrowiska
     * @param ant przekazwyany jest parametr, ktora mrowka ma zostac usunieta
     */
    public void delete_ant(Ant ant)
    {
        ants.remove(ant);
    }

    /**
     * Metoda zwracajaca ilosc mrowek danego mrowiska na planszy
     * @return zwraca rozmiar listy mrowek dla mrowiska
     */
    public int ant_count() {


        return ants.size();

    }

    /**
     * Metoda, ktora zwraca konkretna mrowke z mrowiska
     * @param s pozycja mrowki w liscie
     * @return zwraca mrowke znajdujaca sie na pozycji s
     */
    public Board_object get_ant(int s)
    {
        return ants.get(s);
    }

    @Override
    public int get_range() {
        return 0;
    }

    /**
     * Metoda, ktora zwraca pozycje mrowiska
     * @return zwraca punkt, w ktorym znajduje sie mrowka
     */
    @Override
    public Point position() {
        return new Point(x,y);
    }
}

