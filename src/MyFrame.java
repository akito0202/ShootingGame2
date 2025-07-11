import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MyFrame extends Frame implements Runnable {
    private BufferedImage im;
    private Color col = Color.BLACK;
    private Thread t;
    public Color bgColor = new Color(255, 255, 255);

    public MyFrame() {
        super();
        setSize(400, 400);
        im = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                System.exit(1);
            }
        });
    }

    public synchronized void saveImage(File dst) throws IOException {
        ImageIO.write(im, "png", dst);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(im, 0, 0, null);
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    public synchronized void fillRect(double x, double y, double w, double h) {
        fillRect((int) x, (int) y, (int) w, (int) h);
    }

    public synchronized void fillRect(int x, int y, int w, int h) {
        Graphics g = getImageGraphics();
        if (g != null) {
            g.setColor(col);
            g.fillRect(x, y, w, h);
        }
    }

    public synchronized void clear() {
        Color s = col;
        col = bgColor;
        fillRect(0, 0, getWidth(), getHeight());
        col = s;
    }

    public synchronized void fillOval(int x, int y, int w, int h) {
        Graphics g = getImageGraphics();
        if (g != null) {
            g.setColor(col);
            g.fillOval(x, y, w, h);
        }
    }

    public synchronized void fillOval(double x, double y, double w, double h) {
        fillOval((int) x, (int) y, (int) w, (int) h);
    }

    private Graphics getImageGraphics() {
        return im.getGraphics();
    }

    public void setColor(int red, int green, int blue) {
        red = Math.max(0, Math.min(255, red));
        green = Math.max(0, Math.min(255, green));
        blue = Math.max(0, Math.min(255, blue));
        col = new Color(red, green, blue);
    }

    public void sleep(double time) {
        try {
            Thread.sleep((int) (time * 1000));
            repaint();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        // override in subclass
    }

    public synchronized void drawString(String str, int x, int y, int size) {
        Graphics g = getImageGraphics();
        if (g != null) {
            g.setColor(col);
            g.setFont(new Font("Monospaced", 0, size));
            g.drawString(str, x, y);
        }
    }
}
