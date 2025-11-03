import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class WeatherService {
    private static final String STRING_API_KEY = loadApiKey();
    private static final String STRING_API_URL = "https://api.openweathermap.org/data/2.5/weather";
    private final HttpClient httpClient;

    private static String loadApiKey(){
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")){
            prop.load(input);
            String key = prop.getProperty("API_KEY");
            if (key == null || key.trim().isEmpty()) throw new RuntimeException("API_KEY not found in config.properties");
            return key;
        } catch (IOException e){
            throw new RuntimeException("Could not load config.properties", e);
        }
    }

    public WeatherService(){
        this.httpClient = HttpClient.newHttpClient();
    }

    // Calls the OpenWeatherMap API and returns the parsed weather data
    public WeatherData getWeather(String city) throws Exception {
        // Build the full URL
        String urlString = String.format("%s?q=%s&appid=%s&units=imperial",
                STRING_API_URL,
                city.replace(" ", "%20"), // Handle spaces in city names
                STRING_API_KEY);

        // Create HTTP request object
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(urlString))
                .GET()
                .build();

        // Send the request and get the response (as a string of JSON text)
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Check if the request was successful
        if (response.statusCode() == HttpURLConnection.HTTP_NOT_FOUND){
            throw new Exception("City not found: " + city);
        }
        if (response.statusCode() != HttpURLConnection.HTTP_OK){
            throw new Exception("API Request failed with status: " + response.statusCode());
        }
        // Parse the JSON text and return the result
        return parseJson(response.body());
    }

    private WeatherData parseJson(String jsonResponse){
        JSONObject root = new JSONObject(jsonResponse);

        // Navigate the JSON structure to find the data we need
        String location = root.getString("name");

        JSONObject main = root.getJSONObject("main");
        double temp = main.getDouble("temp");
        double feelsLike = main.getDouble("feels_like");
        int humidity = main.getInt("humidity")
;
        JSONArray weatherArray = root.getJSONArray("weather");
        JSONObject weather = weatherArray.getJSONObject(0);
        String description = weather.getString("description");
        description = description.substring(0,1).toUpperCase() + description.substring(1);

        double wind = root.getJSONObject("wind").getDouble("speed");

        return new WeatherData(location, temp, description, feelsLike, wind, humidity);
    }
}
