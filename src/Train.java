import java.awt.*;
import java.util.List;
import java.util.Random;

public class Train {
    private String name;
    private List<Station> route;
    private int currentStationIndex;
    private double x,y;
    private double targetX, targetY;
    private double speed = 2.0; // Hastighet per uppdatering
    private int delay; // Startfördröjning
    private int counter = 0;

    public Train(String name, List<Station> route, Station startStation) {
        this.name = name;
        this.route = route;
        this.currentStationIndex = 0;
    
        // Startposition vid unik station
        this.x = startStation.getX();
        this.y = startStation.getY();
        updateTarget();
    
        // Slumpmässig startfördröjning för att tågen inte startar samtidigt
        this.delay = new Random().nextInt(50);
    }
    public List<Station> getRoute() {
        return route;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public void updateRoute(Station newStation) {
        if (!route.contains(newStation)) {
            route.add(newStation);
        }
    }
        
    private void updateTarget() {
        Station nextStation = route.get((currentStationIndex + 1) % route.size());
        targetX = nextStation.getX();
        targetY = nextStation.getY();
    }

    public void update() {
        if (counter < delay) {
            counter++;
            return;
        }

        double dx = targetX - x;
        double dy = targetY - y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance < speed) {
            currentStationIndex = (currentStationIndex + 1) % route.size();
            updateTarget();
        } else {
            x += (dx / distance) * speed;
            y += (dy / distance) * speed;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval((int) x - 5, (int) y - 5, 10, 10);
    }
}
