import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {
    private JSpinner trainSpinner, stationSpinner;
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public SettingsPanel(JFrame frame, CardLayout cardLayout, JPanel mainPanel) {
        this.frame = frame;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Amount of Stations (3-6):"));
        stationSpinner = new JSpinner(new SpinnerNumberModel(4, 3, 6, 1));
        add(stationSpinner);

        add(new JLabel("Amount of Trains(1-16):"));
        trainSpinner = new JSpinner(new SpinnerNumberModel(4, 1, 16, 1));
        add(trainSpinner);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> startSimulation());
        add(startButton);
    }

    private void startSimulation() {
        int numStations = (int) stationSpinner.getValue();
        int numTrains = (int) trainSpinner.getValue();

        // Create a new TrainPanel with the selected settings
        TrainPanel trainPanel = new TrainPanel(numStations, numTrains);

        // Create a TrainControlPanel and pass the TrainPanel to it
        TrainControlPanel trainControlPanel = new TrainControlPanel(trainPanel);

        // Create a SimulationPanel that combines TrainPanel and TrainControlPanel
        SimulationPanel simulationPanel = new SimulationPanel(trainPanel, trainControlPanel);

        // Remove any existing "Simulation" panel to avoid duplicates
        mainPanel.remove(simulationPanel);

        // Add the SimulationPanel to the main panel
        mainPanel.add(simulationPanel, "Simulation");

        // Switch to the SimulationPanel
        cardLayout.show(mainPanel, "Simulation");

        // Repaint the main panel to ensure the new panel is displayed
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
