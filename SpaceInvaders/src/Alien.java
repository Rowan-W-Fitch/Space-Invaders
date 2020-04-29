import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Alien extends GraphicsObject {
    double speed_x;
    double speed_y;

    public Alien(double x, double y, double speed_x, double speed_y){
        super(x,y);
        this.speed_x = speed_x;
        this.speed_y = speed_y;
    }

    public void draw(Graphics g){
        // body of alien
        g.setColor(new Color(27, 255, 14));
        g.fillOval((int) x, (int) y, 70, 50);
        // eyes
        g.setColor(new Color(255, 28, 19));
        g.fillRect((int) x + 5, (int) y + 20, 5, 10);
        g.fillRect((int) x + 60 , (int) y + 20, 5, 10);
        // mouth
        g.setColor(new Color(255, 28, 19));
        g.fillRect((int)x + 30, (int) y + 20, 10, 10);
    }

    public void update(int pic_width, int pic_height, int frame) {
        if(this.x <= 0 && this.speed_x == -2.0){
            this.x += 1;
            this.speed_x = 2;
        }else if(this.x >= 490 && speed_x == 2.0){
            this.x -= 1;
            this.speed_x = -2;
        }
        else{
            this.x += this.speed_x;
            this.y += this.speed_y;
        }
    }
}
