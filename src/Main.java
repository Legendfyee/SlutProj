import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tåg Simulation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            // Skapa startmenyn
            SettingsPanel settingsPanel = new SettingsPanel(frame);
            frame.add(settingsPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
        });
    }
}
