## Java Swing Weather App

A simple desktop weather application built with Java Swing. It fetches and displays live, read-time weather data from the public OpenWeatherMap API.

This project's main goal was to learn and implement two critical skills for modern development: consuming a REST API and parsing JSON data.


## Key Features and Technologies

This project demonstrates a clean separation of concerns, with a `WeatherService` class (the "engine") handling all backend logic and a `Main` class handling the GUI.

* **Core Java:** Built in modern Java (17+).
* **GUI:** **Java Swing** for the complete graphical user interface (`Jframe`, `JPanel`, `JLabel`, `JTextField`).
* **Networking: Java `HttpClient`** (Java 11+) to send live HTTP GET requests to the API.
* **Data Parsing: `org.json`** library to parse the nested JSON response from the server.
* **Data Security:** API keys are secured using a `config.properties` file. which is excluded from the repository via `.gitignore`.
* **OOP Design:** A "service-based" architecture (`WeatherService`) is cleanly decoupled from the UI (`Main`).

## How to Run

The easiest way to run the app is to download the pre-compiled version.
1. Go to the **[Releases Page](https://github.com/rafaelSandovalR/java-weather-app/releases)**
2. Download the `.zip` file for the latest release.
3. Unzip the folder and follow the instructions in the`README.TXT` file (which includes creating your own `config.properties` file for your API key).