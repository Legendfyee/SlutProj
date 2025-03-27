import java.awt.*;

public class Station {
    private double x, y;
    private String name;

    public Station(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public void draw(Graphics g) {
        // Draw the station as a black square
        g.setColor(Color.BLACK);
        g.fillRect((int)x - 10, (int)y - 10, 20, 20);

        // Draw the station name next to the square
        g.setColor(Color.BLACK);
        g.drawString(name, (int)x + 15, (int)y - 10);
    }
}
