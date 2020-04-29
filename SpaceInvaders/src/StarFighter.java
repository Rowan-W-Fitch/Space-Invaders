import java.awt.Color;
import java.awt.Graphics;

public class StarFighter extends GraphicsObject {
    double speed_x;
    double speed_y;
    Graphics Starfter_Laser;
    Graphics Explosion;

    public StarFighter(double x, double y, double speed_x, double speed_y) {
        super(x, y);
        this.speed_x = speed_x;
        this.speed_y = speed_y;
    }

    public void draw(Graphics g){
        //draws body of Starfighter, 7pts
        g.setColor(new Color(255, 12, 12));
        g.fillPolygon(new int[]{(int)x - 55, (int)x - 40, (int)x - 10, (int)x, (int)x +10, (int)x +40, (int)x +55}, new int[]{
                (int)y +45,(int)y +20, (int)y +10,(int)y, (int)y +10, (int)y +20, (int)y +45
        }, 7);
        // draws yellow tips of wings, 2 triangles
        g.setColor(new Color(255, 248, 15));
        g.fillPolygon(new int[]{(int)x -55, (int)x -40, (int)x -40}, new int[]{(int)y +45, (int)y +20, (int)y +45}, 3);
        g.fillPolygon(new int[]{(int)x +55, (int)x +40, (int)x +40}, new int[]{(int)y +45, (int)y +20, (int)y +45}, 3);
        // draws cockpit, oval
        g.setColor(new Color(174, 203, 240));
        g.drawOval((int)x - 5, (int)y + 20, 10, 20);
        g.fillOval((int)x -5, (int)y + 20, 10, 20);
        // draws thrusters at back of body, 2x
        g.setColor(new Color(12, 182, 255));
        g.fillRect((int)x -20, (int)y +45, 10, 10);
        g.fillRect((int)x +10, (int)y +45, 10, 10);
    }

    public void update(int pic_width, int pic_height, int frame) {
        if(this.x <= 55 && this.speed_x == -4.0){
            this.x -= 0;
        }
        else if(this.x >= 505 && this.speed_x == 4.0) {
            this.x += 0;
        }
        else{
            this.x += this.speed_x;
            this.y += this.speed_y;
        }
    }
}
