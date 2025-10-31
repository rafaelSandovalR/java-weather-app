import javax.swing.*;
import java.awt.*;

public class Main {
    private final JFrame frame;
    private final JTextField cityTextField;
    private final JButton searchButton;

    private final JLabel locationLabel;
    private final JLabel temperatureLabel;
    private final JLabel descriptionLabel;

    public Main(){
        Font FONT_MEDIUM = new Font("Arial", Font.PLAIN, 16);
        Font FONT_LARGE = new Font("Arial", Font.BOLD, 32);
        Font FONT_HEADER = new Font("Arial", Font.BOLD, 22);

        // Top Input Panel
        JPanel inputPanel = new JPanel(new BorderLayout(5,5));
        cityTextField = new JTextField();
        cityTextField.setFont(FONT_MEDIUM);

        searchButton = new JButton("Get Weather");

        inputPanel.add(cityTextField, BorderLayout.CENTER);
        inputPanel.add(searchButton, BorderLayout.EAST);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // Central Results Panel
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new GridLayout(3,1));

        locationLabel = new JLabel("Enter a city");
        locationLabel.setFont(FONT_HEADER);
        locationLabel.setHorizontalAlignment(SwingConstants.CENTER);

        temperatureLabel = new JLabel("--°");
        temperatureLabel.setFont(FONT_LARGE);
        temperatureLabel.setHorizontalAlignment(SwingConstants.CENTER);

        descriptionLabel = new JLabel("---");
        descriptionLabel.setFont(FONT_MEDIUM);
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        resultsPanel.add(locationLabel);
        resultsPanel.add(temperatureLabel);
        resultsPanel.add(descriptionLabel);


        frame = new JFrame("Weather App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,300);
        frame.setLayout(new BorderLayout(10,10));
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(resultsPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->{
            Main app = new Main();
            app.frame.setVisible(true);
            app.cityTextField.requestFocusInWindow(); // Focus the text field on launch
        });
    }
}
