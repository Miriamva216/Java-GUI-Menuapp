import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GreenHueMenuUI extends JFrame {

    private JTextArea textArea;
    private Color generatedGreen;

    public GreenHueMenuUI() {
        setTitle("User Interface - Green Hue Edition");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");

        JMenuItem dateTimeItem = new JMenuItem("Print Date and Time");
        JMenuItem saveToFileItem = new JMenuItem("Save to log.txt");
        JMenuItem changeColorItem = new JMenuItem("Change Background (Green Hue)");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Add action listeners
        dateTimeItem.addActionListener(_ -> printDateTime());
        saveToFileItem.addActionListener(_ -> saveTextToFile());
        changeColorItem.addActionListener(_ -> changeBackgroundColor());
        exitItem.addActionListener(_ -> System.exit(0));

        // Add menu items to menu
        menu.add(dateTimeItem);
        menu.add(saveToFileItem);
        menu.add(changeColorItem);
        menu.add(exitItem);

        // Add menu to menu bar
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void printDateTime() {
        LocalDateTime now = LocalDateTime.now();
        String formatted = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        textArea.append("Current Date and Time: " + formatted + "\n");
    }

    private void saveTextToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            writer.write(textArea.getText());
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Text saved to log.txt");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving to file: " + ex.getMessage());
        }
    }

    private void changeBackgroundColor() {
    if (generatedGreen == null) {
        Random rand = new Random();
        int green = 100 + rand.nextInt(156); // 100–255
        int red = rand.nextInt(80);          // 0–79
        int blue = rand.nextInt(80);         // 0–79
        generatedGreen = new Color(red, green, blue);
    }

    getContentPane().setBackground(generatedGreen);
    textArea.setBackground(generatedGreen); // 👈 important
    textArea.setForeground(Color.WHITE);    // 👈 optional

    textArea.append("Background changed to RGB: " +
        generatedGreen.getRed() + ", " +
        generatedGreen.getGreen() + ", " +
        generatedGreen.getBlue() + "\n");
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GreenHueMenuUI().setVisible(true);
        });
    }
}
