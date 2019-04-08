# AndroidNyTimes

Open the project using Android Studio. To do so click open -> existing project.

Wait untill the project is synchronized.

Click Run to run the project.

Code Structure
# Design Pattern -> MVP
# Language Used -> Kotlin

# UI
 
The design for the whole application can be found in the following directory path
/app/src/main/res/layout
 
The UI of the application will be in xml format
 
# Resources
 
The whole front end resources used for the application can be found in the following directory path
/app/src/main/res
 
This contains many sub directories.
 
Directories which starts with the name drawable holds the icons and images used in the application.
 
Mipmap directory holds the app icon i.e app launcher icon.
 
Anim holds the animations used which is also done in xml.
 
Values contains some files viz.,
 
attrs.xml – contains the attributes used in the application
colors.xml – contains the colors used in the application
dimens.xml – contains the dimension used in the application
styles.xml – contains the styles for the buttons & widgets used in the application
strings.xml – contains the language resource(texts) used in the application
 
# Code
 
The path to the functional code can be found in the below path
/app/src/main/java/db/com/a5c
 
Inside this directory, there are multiple sub directories
 
# view
 
This directory contains all the code for button clicks, input fields, page navigations. This refers the UI directly.
 
# Presenter
 
This directory contains all the business logics for the entire application, like manipulating the data and passing it to the UI.
 
# Widgets
 
This directory contains the custom widgets used in the app.
 
# Utils
 
This directory contains the utility classes.
 
# Model
 
This directory contains all the model classes used for API.
 
Dto – This contains all the API request and response classes.
Listener – Listener/Callback class for API
 
# Webservice(API)
 
ApiClient – This class is used to initiate the API call. It contains the base url for the app.
ApiInterface – This class contains all the API endpoints.
