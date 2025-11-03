import javax.swing.*;
import java.awt.*;

public class Main {
    private final JFrame frame;
    private final JTextField cityTextField;
    private final JButton searchButton;

    private final JLabel locationLabel;
    private final JLabel temperatureLabel;
    private final JLabel descriptionLabel;
    private final JLabel feelsLikeLabel;

    private final JLabel windLabel;
    private final JLabel humidityLabel;

    private final WeatherService weatherService;

    public Main(){
        this.weatherService = new WeatherService();

        Font FONT_MEDIUM = new Font("Arial", Font.PLAIN, 16);
        Font FONT_LARGE = new Font("Arial", Font.BOLD, 32);
        Font FONT_HEADER = new Font("Arial", Font.BOLD, 22);
        Font FONT_SMALL = new Font("Arial", Font.PLAIN, 14);
        Font FONT_TINY = new Font("Arial", Font.ITALIC, 12);

        // Top Input Panel
        JPanel inputPanel = new JPanel(new BorderLayout(5,5));
        cityTextField = new JTextField();
        cityTextField.setFont(FONT_MEDIUM);

        searchButton = new JButton("Get Weather");

        cityTextField.addActionListener(event -> fetchWeather());
        searchButton.addActionListener(event -> fetchWeather());

        inputPanel.add(cityTextField, BorderLayout.CENTER);
        inputPanel.add(searchButton, BorderLayout.EAST);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // Details Panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(0,2));

        windLabel = new JLabel();
        windLabel.setFont(FONT_SMALL);
        windLabel.setHorizontalAlignment(SwingConstants.CENTER);

        humidityLabel = new JLabel();
        humidityLabel.setFont(FONT_SMALL);
        humidityLabel.setHorizontalAlignment(SwingConstants.CENTER);

        detailsPanel.add(windLabel);
        detailsPanel.add(humidityLabel);


        // Central Results Panel
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new GridLayout(5,1));

        locationLabel = new JLabel("Enter a city");
        locationLabel.setFont(FONT_HEADER);
        locationLabel.setHorizontalAlignment(SwingConstants.CENTER);

        temperatureLabel = new JLabel("--°");
        temperatureLabel.setFont(FONT_LARGE);
        temperatureLabel.setHorizontalAlignment(SwingConstants.CENTER);

        feelsLikeLabel = new JLabel("---");
        feelsLikeLabel.setFont(FONT_TINY);
        feelsLikeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        descriptionLabel = new JLabel("---");
        descriptionLabel.setFont(FONT_MEDIUM);
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        resultsPanel.add(descriptionLabel);
        resultsPanel.add(locationLabel);
        resultsPanel.add(temperatureLabel);
        resultsPanel.add(feelsLikeLabel);
        resultsPanel.add(detailsPanel);


        frame = new JFrame("Weather App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,350);
        frame.setLayout(new BorderLayout(10,10));
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(resultsPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
    }

    private void fetchWeather(){
        String city = cityTextField.getText();
        resetDisplay();

        if (city == null || city.trim().isEmpty()){
            return;
        }

        try {
            WeatherData data = weatherService.getWeather(city);
            locationLabel.setText(data.location());
            temperatureLabel.setText(String.format("%.0f°F", data.temperature()));
            feelsLikeLabel.setText(String.format("Feels like %.0f°F", data.feelsLike()));
            descriptionLabel.setText(data.description());
            windLabel.setText(String.format("Wind Speed: %.1f mph", data.wind()));
            humidityLabel.setText(String.format("Humidity Level: %d%%", data.humidity()));

        } catch (Exception e){
            locationLabel.setText(e.getMessage());
            e.printStackTrace();
        }
    }

    private void resetDisplay(){
        locationLabel.setText("Please enter a city");
        temperatureLabel.setText("--°");
        feelsLikeLabel.setText("---");
        descriptionLabel.setText("---");
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->{
            Main app = new Main();
            app.frame.setVisible(true);
            app.cityTextField.requestFocusInWindow(); // Focus the text field on launch
        });
    }
}
