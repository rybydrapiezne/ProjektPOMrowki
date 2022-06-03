package env;

import java.awt.*;

enum Quality_Of_Food  {
    LOW,MEDIUM,HIGH


}
public class Food implements Board_object  {
    Quality_Of_Food quality;
    int iteration;
    int height =15;
    int width = 15;
    int x, y, board_size;


    Food(Quality_Of_Food quality, int board_size)
    {
        this.quality=quality;
        this.board_size = board_size;
        x = (int)((Math.random()*((board_size-1) - 0)) +0);//czemu -0??
        y =(int)((Math.random()*((board_size-1) - 0)) +0);
        iteration=0;
    }
    @Override
    public void do_smth() {
        iteration++;
        switch (iteration)
        {
            case(1):
                quality=Quality_Of_Food.MEDIUM;
                break;
            case(2):
                quality=Quality_Of_Food.LOW;
                break;
            case(3):
                break;
        }
    }

    @Override
    public int get_range() {
        return 0;
    }

    @Override
    public Point position() {
        return new Point(x,y);
    }

    @Override
    public String item_on() {
        return "Food"+quality;
    }

    @Override
    public void paint_on_board(Graphics p) {
        switch(quality){
            case LOW -> p.setColor(Color.GRAY);
            case MEDIUM -> p.setColor(Color.DARK_GRAY);
            case HIGH -> p.setColor(Color.BLACK);
        }
        p.fillOval(x*20,y*20,width, height );

    }

}
