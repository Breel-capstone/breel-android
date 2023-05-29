Breel
=====

Breel is a client-side Android application written in Kotlin. It aims to provide valuable information and resources to individuals starting out as freelancers. The app offers guidance from professional freelancers, feedback for each step, and opportunities to take on real projects. By empowering newcomers with the tools and knowledge needed to succeed in the freelance industry, Breel helps unlock the full potential of the freelancer economy and create new opportunities for skilled professionals.

How to Run
----------

To run the Breel Android application, follow these steps:

1.  Clone the repository to your local machine.
2.  Open the project in Android Studio.
3.  Build and sync the project.
4.  Connect an Android device or start an emulator.
5.  Run the application on the connected device/emulator.

Note: Make sure you have the latest version of Android Studio and the Android SDK installed on your machine.

Dependencies
------------
The project uses the following dependencies:

    Android Flexbox (version 3.0.0)
    Dagger Hilt (version 2.44)
    Retrofit (version 2.9.0)
    OkHttp Logging Interceptor (version 4.9.0)
    Kotlin Coroutines (version 1.5.2)
    AndroidX Lifecycle (version 2.4.0)
    AndroidX Navigation (version 2.4.0)
    Firebase Authentication (version 20.0.2)
    Google Play Services Auth (version 19.2.0)
    CircleImageView (version 3.1.0)
    ViewPager2 (version 1.1.0)

Please note that the versions mentioned above are just placeholders and may not be the latest versions. Make sure to refer to the official documentation or relevant sources to get the latest versions of the dependencies.

Architecture
------------

The project follows the Model-View-ViewModel (MVVM) architecture. You can refer to the [Android-MVVM-architecture](https://github.com/ahmedeltaher/Android-MVVM-architecture) repository for more details on this architecture. The MVVM pattern separates data presentation logic from business logic by moving it into a specific class for clear distinction.

The project also utilizes Coroutines for handling asynchronous programming. Coroutines provide lightweight threads for performing asynchronous tasks in a sequential manner.
