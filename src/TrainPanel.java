import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TrainPanel extends JPanel {
    public List<Station> stations;
    public List<Train> trains;
    private Timer timer;

   public TrainPanel(int numStations, int numTrains) {
    setPreferredSize(new Dimension(800, 600));

    // Skapa stationer på slumpade positioner
    stations = new ArrayList<>();
    Random rand = new Random();
    for (int i = 0; i < numStations; i++) {
        int x = 100 + rand.nextInt(700);
        int y = 100 + rand.nextInt(500);
        stations.add(new Station("S" + (i + 1), x, y));
    }

    // Skapa tåg med unika rutter
    trains = new ArrayList<>();
    for (int i = 0; i < numTrains; i++) {
        List<Station> route = new ArrayList<>(stations);
        Collections.shuffle(route); // Slumpa ordningen på stationerna

        Station startStation = route.get(0); // Varje tåg startar på sin första station
        trains.add(new Train("Tåg " + (i + 1), route, startStation));
    }

    // Timer för att uppdatera animationen
    timer = new Timer(50, e -> updateSimulation());
    timer.start();
}
    public List<Train> getTrains(){
    return trains;
    }
    public List<Station> getStations(){
        return stations;
    }
    public void updateTrainRoute(int trainIndex, int stationIndex) {
    if (trainIndex >= 0 && trainIndex < trains.size() && stationIndex >= 0 && stationIndex < stations.size()) {
        Train train = trains.get(trainIndex);
        Station newStation = stations.get(stationIndex);

        // Uppdatera tågets rutt så att den nya stationen blir nästa mål
        train.updateRoute(newStation);
        repaint();
    }
}


    private void updateSimulation() {
        for (Train train : trains) {
            train.update();
        }
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Rita spår baserat på tågens rutter
    g.setColor(Color.GRAY);
    for (Train train : trains) {
        List<Station> route = train.getRoute();
        for (int i = 0; i < route.size(); i++) {
            Station current = route.get(i);
            Station next = route.get((i + 1) % route.size()); // Cirkulär rutt
            g.drawLine(current.getX(), current.getY(), next.getX(), next.getY());
        }
    }

    // Rita stationer med nummer
    g.setColor(Color.BLACK);
    for (int i = 0; i < stations.size(); i++) {
        Station station = stations.get(i);
        station.draw(g);
        g.drawString("S" + (i + 1), (int) station.getX() - 10, (int) station.getY() - 10); // Sätt nummer
    }

    // Rita tåg med nummer
    g.setColor(Color.RED);
    for (int i = 0; i < trains.size(); i++) {
        Train train = trains.get(i);
        train.draw(g);
        g.drawString("T" + (i + 1), (int) train.getX() + 5, (int) train.getY() - 5); // Sätt nummer
    }
}

}