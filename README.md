# MovieLibrary

Based on the provided Java files, here is an analysis of what your "MovieLibrary" project does:

### 1. **Movie.java**
This class represents a movie entity with attributes such as title, year, country, genre, cost, keywords, and rating. It likely includes constructors, getters, and setters to manage these attributes.

### 2. **DatabaseActivity.java**
This class manages the database interactions for the MovieLibrary application. Key functions include:

- **Adding a Movie**: Provides methods to add a new movie to the database using user inputs.
- **Listing All Movies**: Displays all movies stored in the database.
- **Deleting Movies**: Includes functionality to clear the database and delete movies based on certain criteria (e.g., older than 2010 or costing more than a certain amount).

### 3. **SMSReceiver.java**
This class handles SMS reception and processes incoming messages to update the MovieLibrary. Key functionalities include:

- **BroadcastReceiver Implementation**: Listens for incoming SMS messages.
- **Message Parsing**: Uses a `StringTokenizer` to parse the message and extract movie details.
- **UI Update**: Updates the user interface with the parsed movie details.

### 4. **LifecycleActivity.java**
This class manages the activity lifecycle and handles actions when the activity is created, resumed, paused, etc. It includes methods for saving and restoring instance state, ensuring that movie details are retained during configuration changes.

### 5. **RecyclerAdapter.java**
This class manages the display of movie items in a RecyclerView. Key functionalities include:

- **ViewHolder Pattern**: Efficiently binds movie data to RecyclerView items.
- **Data Management**: Handles the dataset representing the movies and updates the RecyclerView when the dataset changes.

### 6. **RecyclerActivity.java**
This class manages the RecyclerView activity that displays the list of movies. Key functionalities include:

- **Setting Up RecyclerView**: Configures the RecyclerView with a layout manager and adapter.
- **Data Display**: Displays the list of movies passed from the database or other activities.

### 7. **MainActivity.java**
This class serves as the main entry point of the application. Key functionalities include:

- **UI Initialization**: Sets up the main user interface elements, such as input fields and buttons.
- **Handling User Actions**: Manages user interactions, such as adding a new movie or triggering database actions.
- **Menu Options**: Provides options to clear all movies, convert movie titles to lowercase, and delete movies based on certain criteria.

### Summary
The MovieLibrary project is an Android application designed to manage a collection of movies. It includes functionalities for adding, displaying, and deleting movies from a database, as well as updating movie details via SMS messages. The application uses a RecyclerView to display the list of movies and manages activity lifecycle events to ensure data persistence and a seamless user experience.
