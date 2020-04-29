import java.awt.Color;
import java.awt.Graphics;

public class Explosion extends GraphicsObject {

    public Explosion(double x, double y) {
        super(x, y);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(255, 141, 4));
        g.fillPolygon(new int[]{(int) x - 20, (int) x - 10, (int) x, (int) x + 45, (int) x, (int) x - 20}, new
                int[]{(int) y - 25, (int) y, (int) y - 15, (int) y + 20, (int) y + 25, (int) y}, 6);
        g.setColor(new Color(255, 249, 13));
        g.fillPolygon(new int[]{(int) x + 25, (int) x + 10, (int) x - 12, (int) x, (int) x - 22}, new int[]{
                (int) y + 22, (int) y, (int) y - 15, (int) y + 5, (int) y + 18}, 5);
        g.setColor(new Color(255, 12, 12));
        g.fillPolygon(new int[]{(int) x - 22, (int) x, (int) x + 12, (int) x, (int) x + 25}, new int[]
                {(int) y - 18, (int) y, (int) y + 12, (int) y - 8, (int) y + 3}, 5);
    }

}


