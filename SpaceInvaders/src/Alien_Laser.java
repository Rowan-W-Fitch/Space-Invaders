import java.awt.Color;
import java.awt.Graphics;

public class Alien_Laser extends GraphicsObject {
    double speed_x;
    double speed_y;

    public Alien_Laser(double x, double y,double speed_y){
        super(x,y);
        this.speed_y = speed_y;
    }

    public void draw(Graphics g){
        g.setColor(new Color(255, 10, 9));
        g.fillRect((int)x, (int)y, 10, 20);
        g.fillPolygon(new int[]{(int)x - 10, (int)x + 5, (int)x + 20}, new int[]{(int)y + 20, (int)y+30, (int)y +20}, 3);
        g.setColor(new Color(35, 255, 9));
        g.fillRect((int)x +2,(int)y +4, 6, 18 );
        g.fillPolygon(new int[]{(int)x -4, (int)x +5, (int)x + 14}, new int[]{(int)y + 22, (int)y +28, (int)y +22}, 3);
    }

    public void update(int pic_width, int pic_height, int frame) {
        this.y += this.speed_y;
    }
}
