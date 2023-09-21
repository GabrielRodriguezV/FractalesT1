/**
 *
 * @author nenen
 */
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Mandelbrot extends JPanel {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final double MIN_X = -2.0;
    private static final double MAX_X = 1.0;
    private static final double MIN_Y = -1.5;
    private static final double MAX_Y = 1.5;
    private static final int MAX_ITERATIONS = 300;

    private BufferedImage image;

    public Mandelbrot() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        calculateMandelbrot();
    }

    private void calculateMandelbrot() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                double zx = 0;
                double zy = 0;
                int iteration = 0;

                double cX = (x * (MAX_X - MIN_X) / (WIDTH - 1)) + MIN_X;
                double cY = (y * (MAX_Y - MIN_Y) / (HEIGHT - 1)) + MIN_Y;

                while (zx * zx + zy * zy < 4 && iteration < MAX_ITERATIONS) {
                    double xTemp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = xTemp;
                    iteration++;
                }

                int color = Color.HSBtoRGB((float) (iteration % 256) / 255f, 1, iteration > MAX_ITERATIONS ? 0 : 1);
                image.setRGB(x, y, color);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mandelbrot Set");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.add(new Mandelbrot());
        frame.setVisible(true);
    }
}
