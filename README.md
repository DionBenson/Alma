# Alma

Description- 

This app, once finished, will be a movie recommendation app that allows you to choose and add movies you have seen to keep in a film log that is attached to a personal profile
 and using that film log a personalized list of recommended movies will be supplied to you. 
 
 Technologies Used-
 
 This app was created in Android Studio with Koltin and XML. This app uses Firebase Firestore and Firebase Authentication to store data along with user emails and passwords.
 
 How To Run-
 
Make sure you have downdload a virtual device with Google Play compatability in Android Studio's AVD Manager. Download the files provided in Task List provided. Once downloaded,
open as a project and run the Alma app on your virtual device.

How To Use-

Create an account by using the "Sign Up" option upon start-up. There currently is no requirement or authentication for for email and password, although emails can only be used once.
I would suggest using your name followed by @gmail.com and a simple password for sign up. For example: Username: name@gmail.com Password: name1234. Once you have signed up you will
be logged in where you have the ability to choose a "Log Movie" button. If you press that button, you will then be able to scroll through a list of movies and press on an individual movie
to see more information on the movie and add them to your personal list of added movies by selecting "Add To Watched." Once you have added a movie to your collection you can press the back arrow until you reach the starting screen once again. From
there you can choose to see the movies you've added to your collection, by pressing the button label "Film Log." In your film log you can once again scroll through the movies you
have added to your log and click on them to see more information. YGoing back to the starting screen once again, if you press the "Recommendations" button you will see where 
the recommendation feature will eventually be added. In the top left you can either log out, which will take you back to the log in or sign up screen, or if you press the 
"Developer Options" button you can see a mockup design of a search feature for movies, which will eventually be implemented to the "Log Film" option. 

Future Implementations-

- The most pressing feature that will need implementation is the recommendation feature. A list of recommended movies will be supplied to the user based on the attributes of the
films they have added to their list, such as genre, release year, rating and number of votes(popularity). 

- A personal rating system to seperate movies you have seen and movies you enjoyed as to better your recommendations

- A search feature for movies you can add and movies you have added that are in your film log.

- A more populated list of movies in Firestore

- UI improvements

- Settings and customization options

- Refactoring some activities into fragements
