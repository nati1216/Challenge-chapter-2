import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class BouncingTextApplet extends Applet implements Runnable {
    private Thread thread;
    private int x = 0;               // X-coordinate of the text
    private int y = 100;             // Y-coordinate of the text
    private String text = "Mekuannt Zelalem"; // Text to display
    private boolean running = false; // Thread running flag

    // 1. init(): Set up applet size and background color
    public void init() {
        setSize(400, 200);            // Applet size
        setBackground(Color.BLACK);   // Background color
        setForeground(Color.WHITE);   // Text color
    }

    // 2. start(): Start the animation thread
    public void start() {
        if (thread == null) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    // 2. stop(): Stop the animation thread
    public void stop() {
        running = false;
        thread = null;
    }

    // 3. run(): Main animation loop
    public void run() {
        while (running) {
            x += 10; // Move text to the right

            // If text hits the right edge, reset position
            if (x > getWidth()) {
                x = -getFontMetrics(getFont()).stringWidth(text);
            }

            // Repaint the applet
            repaint();

            // Pause for 100 milliseconds
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // Handle interruption
            }
        }
    }

    // 4. paint(): Draw the text
    public void paint(Graphics g) {
        g.drawString(text, x, y);
    }
}
