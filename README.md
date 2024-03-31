This Weather Application is built using Java and Spring Boot to fetch weather forecast details by providing a location. It is integrated with a third-party application called VisualProcessing for fetching weather forecast data


Features-
Location-based Weather Forecast: It will fetch the weather forecast details for the given location. It can also fetch hourly weather forecase.
Integration with VisualProcessing: Utilizes VisualProcessing API for accurate weather data.
Java and Spring Boot: Built using Java programming language with the Spring Boot framework.


Requirements-
Java Development Kit (JDK) 8 or higher
Maven for building and managing dependencies
VisualProcessing API credentials (https://www.visualcrossing.com/resources/documentation/weather-api/timeline-weather-api/)


SetUp-

Clone the repository:

Copy code
git clone https://github.com/varsha14082000/weatherApplication.git

Navigate to the project directory:
cd weatherApplication

Set up VisualProcessing API credentials:
Obtain  VisualProcessing API credentials from VisualProcessing website.
Add these credentials to the application properties file (src/main/resources/application.properties).

Build the project using Maven:
mvn clean install
Run the application:

mvn spring-boot:run
Access the application in your web browser at http://localhost:8080.


API Endpoints
forecast-summary/{location}: Fetch weather forecast details for the specified location for 15 days from current date
forecast-summary/hourly{location}: Fetch weather forecast details for the specified location for the current date on hourly basis


