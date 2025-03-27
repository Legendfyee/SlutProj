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
        setLayout(new FlowLayout());

        // Dropdown för att välja tåg
        trainSelector = new JComboBox<>();
        for (int i = 0; i < trainPanel.getTrains().size(); i++) {
            trainSelector.addItem("Tåg " + (i + 1));
        }

        // Dropdown för att välja station
        stationSelector = new JComboBox<>();
        for (int i = 0; i < trainPanel.getStations().size(); i++) {
            stationSelector.addItem("Station " + (i + 1));
        }

        // Knapp för att uppdatera rutt
        updateRouteButton = new JButton("Uppdatera rutt");
        updateRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int trainIndex = trainSelector.getSelectedIndex();
                int stationIndex = stationSelector.getSelectedIndex();
                trainPanel.updateTrainRoute(trainIndex, stationIndex);
            }
        });
        
        // Lägg till komponenter i panelen
        add(new JLabel("Välj tåg:"));
        add(trainSelector);
        add(new JLabel("Välj station:"));
        add(stationSelector);
        add(updateRouteButton);
    }
}
