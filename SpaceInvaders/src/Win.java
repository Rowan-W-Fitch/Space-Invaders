import java.awt.Color;
import java.awt.Graphics;

public class Win extends GraphicsObject {
    int x;
    int y;

    public Win(int x, int y){
        super(x, y);
    }

    public void draw(Graphics g){
        g.setColor(new Color(255, 12, 5));
        g.fillRect(x,y,560, 560);
        g.setColor(new Color(255, 246, 12));
        g.drawString("Boo Jordan", 280, 280);
    }
}
