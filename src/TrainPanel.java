import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TrainPanel extends JPanel {
    private List<Station> stations;
    private List<Train> trains;
    private Timer timer;

    public TrainPanel(int numStations, int numTrains) {
        setPreferredSize(new Dimension(800, 600));

        // Create stations at random positions
        stations = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < numStations; i++) {
            int x = 100 + rand.nextInt(700);
            int y = 100 + rand.nextInt(500);
            stations.add(new Station("S" + (i + 1), x, y));
        }

        // Create trains with unique routes
        trains = new ArrayList<>();
        for (int i = 0; i < numTrains; i++) {
            List<Station> route = new ArrayList<>(stations); // Copy the list of stations
            Collections.shuffle(route); // Shuffle the order of stations
            Station startStation = route.get(0); // Each train starts at its first station
            trains.add(new Train("Tåg " + (i + 1), route, startStation));
        }

        // Timer to update the simulation
        timer = new Timer(50, e -> updateSimulation()); // Call updateSimulation() every 50ms
        timer.start();
    }

    public List<Train> getTrains() {
        return trains;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void updateTrainRoute(int trainIndex, int stationIndex) {
        if (trainIndex >= 0 && trainIndex < trains.size() && stationIndex >= 0 && stationIndex < stations.size()) {
            Train train = trains.get(trainIndex);
            Station newStation = stations.get(stationIndex);
            train.setRoute(newStation); // Update the train's route
        }
    }

    private void updateSimulation() {
        for (Train train : trains) {
            if (train.isWaiting()) {
                // Train is waiting, skip updating its position
                continue;
            }

            if (train.isAtStation()) {
                // Train has reached a station, start waiting
                System.out.println("Train " + train.getName() + " reached station " + train.getCurrentStation().getName());
                train.startWaiting(5000); // Wait for 5 seconds (5000 ms)
            } else {
                // Train is moving, update its position
                train.update();
            }
        }
        repaint(); // Repaint the panel to reflect changes
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw tracks (lines between stations)
        g.setColor(Color.GRAY);
        for (int i = 0; i < stations.size(); i++) {
            for (int j = i + 1; j < stations.size(); j++) {
                Station s1 = stations.get(i);
                Station s2 = stations.get(j);
                g.drawLine((int)s1.getX(),(int) s1.getY(),(int) s2.getX(),(int) s2.getY());
            }
        }

        // Draw stations-
        for (Station station : stations) {
            station.draw(g);
        }

        // Draw trains
        for (Train train : trains) {
            train.draw(g);
        }
    }
}