## Weather App

### Overview
This is a sample android application which displays weather forecast to the user. 

### UI Mockups
UI Mockups for the application. Click on the link to view them:
https://goo.gl/photos/BtnLEjYeXM2da8JH8

### Features
- Single page user interface displaying current days' weather and forecast for the coming week.
- Weather is being retrieved from the OpenWeatherMap Api.
- User is provided with a Settings page where they can enter the location (zip code) they would like to see the weather forecast for.
- User is also provided with a "Show location on Map" option, which takes the user to the default map app to show the users selected location.
- User can also view the forecast of already seen locations when offline.

### Technical details
- Third party libraries: Picasso (for loading icons), Volley(for network calls to the weather Api), stetho(to inspect the database - debug purposes).
- SQLite database used to store weather data. Content providers implemeted to access weather data from the DB.

### Things to do
- Implement a sync adapter to fetch weather data.
- Add a detail screen to view more details about the weather.
- Add support for tablets, may be a master-detail layout.


