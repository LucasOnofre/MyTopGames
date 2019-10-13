<h1 align="center">MyTopGames!</h1>
<p align="left">
A simple kotlin app using the MVP pattern to discover your top games
</p>

### API
This app makes use of the API from Twitch to fetch the results.

for more information refer to: https://https://dev.twitch.tv/docs/v5/reference/games/


### Pagination
The "Top Games" screen makes use of EndlessScroll to fetch more results according to the given page number and filters.

### Persistence
This app uses SharedPreferences to save the first game results, so the user can see him a next time even if there is no internet connection.

### Error Handling
The performed requests handle successful results, failure results and no internet connection, so we can give the right feedback to the user.

### ToDo
The app was built using MVP architecture for easy <b>testing</b>.
