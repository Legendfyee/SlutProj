import javax.swing.*;
import java.awt.*;

public class SimulationPanel extends JPanel {
    public SimulationPanel(TrainPanel trainPanel, TrainControlPanel trainControlPanel) {
        setLayout(new BorderLayout());

        // Add the TrainPanel (simulation) to the center
        add(trainPanel, BorderLayout.CENTER);

        // Add the TrainControlPanel (controls) to the right
        add(trainControlPanel, BorderLayout.EAST);
    }
}