import java.awt.*;

public class Station {
    private String name;
    private int x, y;

    public Station(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x - 10, y - 10, 20, 20);
        
    }
}
