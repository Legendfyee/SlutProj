import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Train {
    private String name;
    private List<Station> route;
    private int currentStationIndex;
    private double x, y;
    private double targetX, targetY;
    private double speed = 2.0; // Speed per update
    private boolean waiting; // Whether the train is waiting at a station
    private long waitStartTime; // When the waiting started
    private int waitDuration = 2000; // Wait duration in milliseconds (2 seconds)

    public Train(String name, List<Station> route, Station startStation) {
        this.name = name;
        this.route = route;
        this.currentStationIndex = 1;

        // Start position at the initial station
        this.x = startStation.getX();
        this.y = startStation.getY();
        updateTarget();

        this.waiting = false;
    }

    public String getName() {
        return name;
    }

    public Station getCurrentStation() {
        return route.get(currentStationIndex);
    }

    public boolean isAtStation() {
        Station currentStation = route.get(currentStationIndex);
        double dx = currentStation.getX();
        double dy = currentStation.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance < speed; // Check if the train is close enough to the station
    }

    public boolean isWaiting() {
        if (waiting && System.currentTimeMillis() - waitStartTime >= waitDuration) {
            waiting = false; // Stop waiting after the duration
        }
        return waiting;
    }

    public void startWaiting(int duration) {
        this.waiting = true;
        this.waitStartTime = System.currentTimeMillis();
        this.waitDuration = duration;
    }

    public void update() {
        if (waiting) {
            return; // Skip movement while waiting
        }

        // Move the train toward the target
        double dx = targetX - x;
        double dy = targetY - y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance < speed) {
            // Train has reached the station
            x = targetX;
            y = targetY;
            waiting = true; // Start waiting
            waitStartTime = System.currentTimeMillis(); // Record the wait start time

            System.out.println("Train " + name + " reached station " + route.get(currentStationIndex).getName());

            // Increment the current station index to point to the next station
            currentStationIndex = (currentStationIndex + 1) % route.size(); // Loop back to the first station if needed
            System.out.println("Train " + name + " updated currentStationIndex to " + currentStationIndex);
            // Update the target to the next station
            updateTarget();
        } else {
            // Move the train closer to the target
            x += (dx / distance) * speed;
            y += (dy / distance) * speed;
        }
    }

    private void updateTarget() {
        Station nextStation = route.get(currentStationIndex);
        targetX = nextStation.getX();
        targetY = nextStation.getY();
        System.out.println("Train " + name + " targeting station " + nextStation.getName() + " at (" + targetX + ", " + targetY + ")");
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval((int) x - 5, (int) y - 5, 10, 10);
        g.setColor(Color.BLACK);
        g.drawString(name, (int) x + 10, (int) y - 10); // Draw train name
    }

    public void setRoute(Station newStation) {
        if (!route.contains(newStation)) {
            route.add(newStation);
        }
    }

    public void shuffleRoute(List<Station> stations) {
        List<Station> route = new ArrayList<>(stations);
        Collections.shuffle(route); // Shuffle the stations to create a random route
    }
}
