import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tåg Simulation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(true);

            // CardLayout for switching between panels
            CardLayout cardLayout = new CardLayout();
            JPanel mainPanel = new JPanel(cardLayout);

            // Create the SettingsPanel
            SettingsPanel settingsPanel = new SettingsPanel(frame, cardLayout, mainPanel);

            // Add the SettingsPanel to the main panel
            mainPanel.add(settingsPanel, "Settings");

            // Show the SettingsPanel first
            cardLayout.show(mainPanel, "Settings");

            frame.add(mainPanel);
            frame.setSize(1000, 600); // Set a larger size for the simulation
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
