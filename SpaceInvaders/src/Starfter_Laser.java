import java.awt.Color;
import java.awt.Graphics;

public class Starfter_Laser extends GraphicsObject {
    double speed_y;

    public Starfter_Laser(double x, double y,double speed_y){
        super(x,y);
        this.speed_y = speed_y;
    }

    public void draw(Graphics g){
        g.setColor(new Color(255, 41, 22));
        g.fillRect((int)x, (int)y, 10, 20);
        g.fillPolygon(new int[]{(int)x - 10, (int)x + 5, (int)x + 20}, new int[]{(int)y, (int)y -10, (int)y}, 3);
        g.setColor(new Color(255, 237, 18));
        g.fillRect((int)x +2,(int)y -2, 6, 18 );
        g.fillPolygon(new int[]{(int)x -4, (int)x +5, (int)x + 14}, new int[]{(int)y - 2, (int)y -8, (int)y -2}, 3);
    }

    public void update(int pic_width, int pic_height, int frame) {
        this.y += this.speed_y;
    }
}
