 // utility
import java.util.ArrayList;
import java.util.Random;

// graphics
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

// events
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// swing
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SpaceInvaders extends JPanel implements ActionListener, KeyListener, Runnable {

    private final int canvasWidth;
    private final int canvasHeight;
    private final Color backgroundColor;

    private final int framesPerSecond = 25;
    private final int msPerFrame = 1000 / framesPerSecond;
    private Timer timer;
    private int frame = 0;

    StarFighter starfighter = new StarFighter(280.0, 510.0, 4.0, 0.0);
    double XleftLimit = 0.0;
    double XrightLimit = 560.0;
    double YupperLimit = 0.0;
    double YlowerLimit = 100.0;
    double alien1_x = XleftLimit + new Random().nextDouble() * (XrightLimit - XleftLimit);
    double alien1_y = YupperLimit + new Random().nextDouble()*(YlowerLimit - YupperLimit);
    double alien2_x = XleftLimit + new Random().nextDouble() * (XrightLimit - XleftLimit);
    double alien2_y = YupperLimit + new Random().nextDouble()*(YlowerLimit - YupperLimit);
    double alien3_x = XleftLimit + new Random().nextDouble() * (XrightLimit - XleftLimit);
    double alien3_y = YupperLimit + new Random().nextDouble()*(YlowerLimit - YupperLimit);
    double alien4_x = XleftLimit + new Random().nextDouble() * (XrightLimit - XleftLimit);
    double alien4_y = YupperLimit + new Random().nextDouble()*(YlowerLimit - YupperLimit);
    double alien5_x = XleftLimit + new Random().nextDouble() * (XrightLimit - XleftLimit);
    double alien5_y = YupperLimit + new Random().nextDouble()*(YlowerLimit - YupperLimit);
    Lose_Screen BIG_L = new Lose_Screen(0, 0);
    Win BIG_W = new Win(0,0);
    ArrayList<Explosion> Dead_Aliens;
    ArrayList<Explosion> XPLSION;
    ArrayList<Starfter_Laser> STR_LZR;
    ArrayList<Alien_Laser> ALN_LZR;
    ArrayList<Alien> Aliens;
    ArrayList<Alien> dead;
    /* Constructor for a Space Invaders game
     */
    public SpaceInvaders() {
        // fix the window size and background color
        this.canvasWidth = 560;
        this.canvasHeight = 560;
        this.backgroundColor = Color.BLACK;
        setPreferredSize(new Dimension(this.canvasWidth, this.canvasHeight));

        // set the drawing timer
        this.timer = new Timer(msPerFrame, this);
        this.STR_LZR= new ArrayList<>();
        this.ALN_LZR = new ArrayList<>();
        this.Aliens = new ArrayList<>();
        this.Dead_Aliens = new ArrayList<>();
        this.XPLSION = new ArrayList<>();
        this.dead = new ArrayList<>();
        Aliens.add(new Alien(alien1_x,alien1_y, -2.0, 0.3));
        Aliens.add(new Alien(alien2_x,alien2_y, 2.0, 0.5));
        Aliens.add(new Alien(alien3_x,alien3_y, -2.0, 0.7));
        Aliens.add(new Alien(alien4_x,alien4_y, 2.0, 0.9));
        Aliens.add(new Alien(alien5_x,alien5_y, -2.0, 1.1));

        // FIXME initialize your game objects
    }

    /* Start the game
     */
    @Override
    public void run() {
        // show this window
        display();

        // set a timer to redraw the screen regularly
        this.timer.start();
    }

    /* Create the window and display it
     */
    private void display() {
        JFrame jframe = new JFrame();
        jframe.addKeyListener(this);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setContentPane(this);
        jframe.pack();
        jframe.setVisible(true);
    }

    /* Run all timer-based events
     *
     * @param e  An object describing the timer
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // update the game objects
        this.update(frame);
        // draw every object (this calls paintComponent)
        repaint(0, 0, this.canvasWidth, this.canvasHeight);
        // increment the frame counter
        this.frame++;
    }

    /* Paint/Draw the canvas.
     *
     * This function overrides the paint function in JPanel. This function is
     * automatically called when the panel is made visible.
     *
     * @param g The Graphics for the JPanel
     */
    @Override
    protected void paintComponent(Graphics g) {
        // clear the canvas before painting
        clearCanvas(g);
        if (hasWonGame()) {
            paintWinScreen(g);
        } else if (hasLostGame()) {
            paintLoseScreen(g);
        } else {
            paintGameScreen(g);
        }
    }

    /* Clear the canvas
     *
     * @param g The Graphics representing the canvas
     */
    private void clearCanvas(Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(this.backgroundColor);
        g.fillRect(0, 0, this.canvasWidth, this.canvasHeight);
        g.setColor(oldColor);
    }

    /* Respond to key release events
     *
     * A key release is when you let go of a key
     *
     * @param e  An object describing what key was released
     */
    public void keyReleased(KeyEvent e) {
        // you can leave this function empty
    }

    /* Respond to key type events
     *
     * A key type is when you press then let go of a key
     *
     * @param e  An object describing what key was typed
     */
    public void keyTyped(KeyEvent e) {
        // you can leave this function empty
    }

    /* Respond to key press events
     *
     * A key type is when you press then let go of a key
     *
     * @param e  An object describing what key was typed
     */

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            starfighter.speed_x = -4.0;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            starfighter.speed_x = 4.0;
            // FIXME what happens when right arrow is pressed
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            STR_LZR.add(new Starfter_Laser(starfighter.x, starfighter.y, -10.0));
        }
            // FIXME what happens when space bar is pressed
    }

    /* Update the game objects
     */
    private void update(int frame) {
        this.starfighter.update(this.canvasWidth, this.canvasHeight, frame);
        for(Alien aln: this.Aliens) {
            aln.update(this.canvasWidth, this.canvasHeight, frame);
        }
        for (Alien_Laser lzr : this.ALN_LZR) {
                lzr.update(this.canvasWidth, this.canvasHeight, frame);
        }
        for(Starfter_Laser lsr: this.STR_LZR){
            lsr.update(this.canvasWidth, this.canvasHeight, frame);
        }
        for(Explosion exp: this.XPLSION){
            exp.update(this.canvasWidth, this.canvasHeight, frame);
        }
        for(Explosion exp: this.Dead_Aliens){
            exp.update(this.canvasWidth, this.canvasHeight, frame);
        }

        // FIXME update game objects here
    }


    /* Check if the player has lost the game
     *
     * @returns  true if the player has lost, false otherwise
     */
    private boolean hasLostGame() {
        for (Alien_Laser lazr : this.ALN_LZR) {
            if (lazr.x >= starfighter.x - 55 && lazr.x <= starfighter.x + 55 && lazr.y >= starfighter.y && lazr.y <= starfighter.y + 45) {
                    XPLSION.add(new Explosion(starfighter.x, starfighter.y));
            }
        }
        for(Alien aln: this.Aliens){
            if(aln.x >= starfighter.x - 55 && aln.x <= starfighter.x + 55 && aln.y + 50 >= starfighter.y && aln.y +50 <= starfighter.y +45){
                XPLSION.add(new Explosion(starfighter.x, starfighter.y));
            }else if(aln.y +50 >= 560){
                XPLSION.add(new Explosion(starfighter.x, starfighter.y));
            }
            for(int i = 0; i<= 560; i += 10){
                if (aln.x == i){
                    ALN_LZR.add(new Alien_Laser(aln.x +35, aln.y +50, 10.0));
                }
            }
        }
        if(XPLSION.size() >= 1) {
            return true;
        }
        else {
            return false;
        }
    }

    /* Check if the player has won the game
     *
     * @returns  true if the player has won, false otherwise
     */
    private boolean hasWonGame() {
        for(Starfter_Laser lsr: this.STR_LZR){
            for(Alien aln: this.Aliens){
                Explosion explsn = new Explosion(aln.x, aln.y);
                if(lsr.x >= aln.x && lsr.x <= aln.x +70 && lsr.y >= aln.y && lsr.y <= aln.y +50){
                    Dead_Aliens.add(explsn);
                    Aliens.remove(aln);
                    STR_LZR.remove(lsr);
                    dead.add(aln);
                }
                if(lsr.y <= 0){
                    STR_LZR.remove(lsr);
                }
            }
        }
        if(dead.size() >= 5){
            return true;
        }
        return false; // FIXME delete this when ready
    }

    /* Paint the screen during normal gameplay
     *
     * @param g The Graphics for the JPanel
     */
    private void paintGameScreen(Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(this.backgroundColor);
        g.fillRect(0, 0, this.canvasWidth, this.canvasHeight);
        g.setColor(oldColor);

        this.starfighter.draw(g);
        for(Alien aln: this.Aliens){
            aln.draw(g);
            if(aln.x >= 10 && aln.x <= 11 || aln.x >= 50 && aln.x <= 51|| aln.x >= 100 && aln.x <= 101 || aln.x>= 150 && aln.x <= 151 || aln.x >= 200 && aln.x <= 201 ||
                    aln.x >= 250 && aln.x <= 251 || aln.x >= 300 && aln.x <= 301 || aln.x >= 350 && aln.x <= 351 ||
                    aln.x >= 400 && aln.x <= 401 || aln.x >= 450 && aln.x <= 451|| aln.x >= 500 && aln.x <= 501 || aln.x >= 550 && aln.x <= 551){
                ALN_LZR.add(new Alien_Laser(aln.x +35, aln.y +50, 10.0));
            }
        }
        for(Alien_Laser lzr: this.ALN_LZR){
            lzr.draw(g);
        }
        for(Starfter_Laser lsr: this.STR_LZR){
            lsr.draw(g);
        }
        for(Explosion exp: this.Dead_Aliens) {
            exp.draw(g);
        }
        for(Explosion exp: this.XPLSION) {
            exp.draw(g);
        }

    }

    /* Paint the screen when the player has won
     *
     * @param g The Graphics for the JPanel
     */
    private void paintWinScreen(Graphics g) {
        BIG_W.draw(g);
        // FIXME draw the win screen here
    }

    /* Paint the screen when the player has lost
     *
     * @param g The Graphics for the JPanel
     */
    private void paintLoseScreen(Graphics g) {
        BIG_L.draw(g);
        // FIXME draw the game over screen here
    }

    public static void main(String[] args) {
        SpaceInvaders invaders = new SpaceInvaders();
        EventQueue.invokeLater(invaders);
    }
}
