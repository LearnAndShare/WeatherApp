# WeatherApp
weather data app

This is Spring MVC application to retreive Weather data for a valid zip code which can run on Apache Tomcat or other web containers.

To Deploy this application to existing tomcat follw below steps  
1. mvn install  
2. Copy .war file in the target directly to Apache Tomcat webapps



In order to run tomcat in emebedded mode please us below mave target

1. mvn clean install tomcat7:run

2. Access below webapp url in your browser

http://localhost:9080/weather/
