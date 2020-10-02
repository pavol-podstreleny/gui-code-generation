# Graphical User Interface generation 
Android mobile application that transforms row graphical user interfaces input image into a hierarchical structure of views that are rendered on the screen. 
- Application uses a machine learning model to predict individual views / view-groups at runtime. 
- ML model was trained on the [pix2code](https://github.com/tonybeltramelli/pix2code) dataset and currently is not available.

## Run the application
#### 1) The first step is to clone the repository
```cmd
git clone https://github.com/pavol-podstreleny/gui-code-generation.git
```

#### 2) Install android studio
The easiest way to run the app is to download and install [Android Studio IDE](https://developer.android.com/studio). Android Studio comes with everything you need to run the app (Android SDK, OpenJDK, Gradle, Emulator).

#### 3) Import `gui-code-generation` project in Android Studio
After importing the project you can run the `gui-code-generation` application on your [local device](https://developer.android.com/training/basics/firstapp/running-app) or [emulator](https://developer.android.com/training/basics/firstapp/running-app).
