/**
 *
 * @author nenen
 */
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SierpinskiCarpet extends JPanel {

    private final int depth;

    public SierpinskiCarpet(int depth) {
        this.depth = depth;
    }

    /**
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSierpinski(g, 0, 0, getWidth(), getHeight(), depth);
    }

    private void drawSierpinski(Graphics g, int x, int y, int width, int height, int depth) {
        if (depth == 0) {
            g.fillRect(x, y, width, height);
        } else {
            int newWidth = width / 3;
            int newHeight = height / 3;

            // Fill the center rectangle
            g.fillRect(x + newWidth, y + newHeight, newWidth, newHeight);

            // Recursively draw smaller Sierpinski carpets in the eight surrounding rectangles
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!(i == 1 && j == 1)) {
                        drawSierpinski(g, x + i * newWidth, y + j * newHeight, newWidth, newHeight, depth - 1);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int depth = 5; // Cambia esto para ajustar la profundidad del fractal
        JFrame frame = new JFrame("Sierpinski Carpet");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.add(new SierpinskiCarpet(depth));
        frame.setVisible(true);
    }
}

