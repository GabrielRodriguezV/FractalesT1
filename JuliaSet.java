/**
 *
 * @author nenen
 */
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JuliaSet extends JPanel {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final double MIN_X = -2.0;
    private static final double MAX_X = 2.0;
    private static final double MIN_Y = -2.0;
    private static final double MAX_Y = 2.0;
    private static final int MAX_ITERATIONS = 300;
    private static final double C_REAL = -0.7; // Cambia estos valores para diferentes conjuntos de Julia
    private static final double C_IMAGINARY = 0.27015;

    private BufferedImage image;

    public JuliaSet() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        calculateJuliaSet();
    }

    private void calculateJuliaSet() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                double zx = (x * (MAX_X - MIN_X) / (WIDTH - 1)) + MIN_X;
                double zy = (y * (MAX_Y - MIN_Y) / (HEIGHT - 1)) + MIN_Y;
                int iteration = 0;
                while (iteration < MAX_ITERATIONS) {
                    double xTemp = zx * zx - zy * zy + C_REAL;
                    zy = 2.0 * zx * zy + C_IMAGINARY;
                    zx = xTemp;
                    if (zx * zx + zy * zy > 4.0) {
                        break;
                    }
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
        JFrame frame = new JFrame("Julia Set");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.add(new JuliaSet());
        frame.setVisible(true);
    }
}
