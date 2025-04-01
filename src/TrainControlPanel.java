import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrainControlPanel extends JPanel {
    private JComboBox<String> trainSelector;
    private JComboBox<String> stationSelector;
    private JButton updateRouteButton;
    private TrainPanel trainPanel;

    public TrainControlPanel(TrainPanel trainPanel) {
        this.trainPanel = trainPanel;
        setLayout(new GridLayout(3, 1, 10, 10));

        // Dropdown for selecting trains
        trainSelector = new JComboBox<>();
        for (Train train : trainPanel.getTrains()) {
            trainSelector.addItem(train.getName());
        }
        add(trainSelector);

        // Dropdown for selecting stations
        stationSelector = new JComboBox<>();
        for (Station station : trainPanel.getStations()) {
            stationSelector.addItem(station.getName());
        }
        add(stationSelector);

        // Button to update the route
        updateRouteButton = new JButton("Update route");
        updateRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int trainIndex = trainSelector.getSelectedIndex();
                int stationIndex = stationSelector.getSelectedIndex();

                if (trainIndex >= 0 && stationIndex >= 0) {
                    trainPanel.updateTrainRoute(trainIndex, stationIndex);
                }
            }
        });
        add(updateRouteButton);
    }
}
