import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {
    private JSpinner trainSpinner, stationSpinner;
    private JFrame frame;

    public SettingsPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Antal stationer (3-6):"));
        stationSpinner = new JSpinner(new SpinnerNumberModel(4, 3, 6, 1));
        add(stationSpinner);

        add(new JLabel("Antal tåg (1-16):"));
        trainSpinner = new JSpinner(new SpinnerNumberModel(4, 1, 16, 1));
        add(trainSpinner);

        JButton startButton = new JButton("Starta");
        startButton.addActionListener(e -> startSimulation());
        add(startButton);
    }

    private void startSimulation() {
        int numStations = (int) stationSpinner.getValue();
        int numTrains = (int) trainSpinner.getValue();
    
        // Skapa en ny TrainPanel med de valda inställningarna
        TrainPanel trainPanel = new TrainPanel(numStations, numTrains);
    
        // Ta bort startmenyn och lägg till spelpanelen
        frame.getContentPane().removeAll();
        frame.add(trainPanel);
        
        // Se till att ramen anpassar sig till den nya panelen
        frame.pack();
        
        // Uppdatera GUI:t
        frame.revalidate();
        frame.repaint();
    }
    
}
