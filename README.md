# An Implementation of Guy Debord's Game of War for Android

The `GameOfWarApp` folder contains all the project code.  Looking at the folders in Android studio, the important parts are as follows:

1. `app/java/` - contains the packages UI (com.example.gameofwarapp), playmanager, and ruleslogic, with their respective `.java` files, and the parallel packages containing unit and integration tests.
2. `app/res/drawable/` - all the visual assets used in drawing the board
3. `app/res/layout/` - `.xml` files specifiying the attributes and relationships of UI elements that aren't generated programmatically.
4. `app/res/values/strings.xml` - all the string resources used in the UI, such as the text shown on buttons or the lengthy instructions.

Without Android Studio, those parts are found on these paths:
1. `GameOfWarApp/app/src/main/java/` for the 3 main packages, `GameOfWarApp/app/src/test/java/` for the tests on those packages.
2. `GameOfWarApp/app/src/main/res/drawable/`
3. `GameOfWarApp/app/src/main/res/layout/`
4. `GameOfWarApp/app/src/main/res/values/string.xml`

Folder `BoardLayouts` contains the layouts designed for testing, the stock starting layouts, and a simple python script for converting from a grid reference to the integer location representation used in the app.

`Diagrams` contains images which may be useful in orienting in the code.

## To run the app

Open the folder `GameOfWarApp` using Android Studio.  Run the app on an emulated Nexus 6P API 29.  It should be compatible down to API 17, but the board likely won't display on screens smaller than 5.7".

Instructions for play are part of the app.