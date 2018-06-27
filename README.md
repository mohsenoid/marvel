Marvel Application [![CircleCI](https://circleci.com/gh/mohsenoid/marvel.svg?style=svg)](https://circleci.com/gh/mohsenoid/marvel) [![Build Status](https://travis-ci.org/mohsenoid/marvel.svg?branch=master)](https://travis-ci.org/mohsenoid/marvel) [![codecov](https://codecov.io/gh/mohsenoid/marvel/branch/master/graph/badge.svg)](https://codecov.io/gh/mohsenoid/marvel)
===============================

This repository holds the source code of the Marvel Application, a simple Android client for the [Marvel.com](http://marvel.com).
This application was created by [Mohsen Mirhoseini](http://mohsenoid.com), as part of the technical assessment by the [smava GmbH](https://www.smava.de) team. It also has been used as a sample project for a series of technical articles and tutorials.

--------------------
### What is this repository for? ###

* Marvel Application
* Version:  1.0.1
* Last Update: Wed June 27, 2018

![Screenshot](screenshot2.png)
![Screenshot](screenshot3.png)

--------------------
### Development Tools ###

* Android Studio v3.1.3
* gradle-plugin v4.4
* Android SDK Build Tools v27.1.1
* MinSdkVersion 14
* CompileSDKVersion 27

--------------------
### Dependencies ###

* Android Support Tools (recyclerView, cardView, vector, palette,... ) v27.1.1
* Retrofit v2.1.0
* OkHttp v3.4.1
* Dagger v2.8
* RxJava2 v2.1.13
* RxAndroid v2.0.2
* RxBinding v2.1.1
* ButterKnife v8.8.1
* Timber v4.7.0
* Picasso v2.5.2
* OrmLite v5.0
* Firebase v16.0.1
* AndroidUtils v1.0.11
* jUnit v4.12
* Android Support Test v1.0.2
* Mockito v1.10.19
* Robolectric v3.8
* Espresso v2.2.2

--------------------
### Important Notes ###

The application includes two Activities, Main and Character Activities. The Main Activity is consist of two Fragments, Search and Cache Fragments, which are responsible for searching new Marvel characters and presenting recently searched characters.

All activity lifecycle and network behaviors are implemented, and according to device size and network situation user get a good UI and UX. If no internet connection or network error, a Snackbar pops up and ask the user to turn on the network connection or retry.

Some simple Test Cases was designed to test application UI functionality and core classes using jUnit and AndroidUnitTest.

### Application Structure ###

The Application implemented and structured based on the MVP pattern best practice, contributed by [Antonio Leiva](http://antonioleiva.com/mvp-android/).

Whole application functionality is implemented in "Core-Lib" module using pure Java, and the "App" module contains all codes required for Android Application to load on Android OS, which can be replaced by any other interface (e.g. console app or web app)

The **view** (MainActivity), contain two fragments. Search and Cache fragments both contain their own presenter and implement View interfaces and the only thing that the view will do is calling a method from the presenter every time there is an interface action.

The **presenter** (Search or Cache Presenters), are responsible to act as the middleman between views and models. They retrieve data from Backend or Database and returns it formatted to the view. It also decides what should happens when user interacts with the view.

The **models** (Search Interactor), would only be the gateway to the service domain layer or business logic. In this case, it provides the data needed to be displayed in the view from Network.

The networking and API calls are managed by [Retrofit](http://square.github.io/retrofit/) and OkHttp as its httpclient, contributed by [Square](http://square.github.io). It also shows decent logs while the application is running in Debug mode. 

Caching characters data is done using [OrmLite](http://ormlite.com), a Lightweight Object Relational Mapping (ORM) Java Package. Database layer can also be done using other ORM libraries like [realm](https://realm.io) or [DBFlow](https://github.com/Raizlabs/DBFlow). 

Layers communications are managed by [RxJava](https://github.com/ReactiveX/RxJava) & [RxAndroid](https://github.com/ReactiveX/RxAndroid) contributed by [ReactiveX](http://reactivex.io).

Dependency Injections are being managed by [Dagger](https://github.com/google/dagger) created by [Square](http://square.github.io) and now maintained by [Google](http://google.github.io/dagger/).

![Diagram](diagram.png)

Some minor Android common functions are managed using [AndroidUtils](https://github.com/mmirhoseini/android_utils) library, developed and published on jCenter by [myself](http://mirhoseini.com).

Whole projects Dependencies are placed in "libraries.gradle" to avoid version conflicts and redundant in different modules.

Used new DataBinding library contributed by Google in Adapters for faster development, and added CustomBindingAdapter to handle downloading and caching images using [Picasso](http://square.github.io/picasso/) library, which also use Google [Palette](https://developer.android.com/topic/libraries/support-library/features.html#v7-palette) support library as a helper class to extract prominent colors from image and making a related background for a better UI/UX.

The Android Log system is replaced with [Timber](https://github.com/JakeWharton/timber) contributed by Jake Wharton, which avoids logging in the release version.

A general HashGenerator class generates the hash key required for calling API using params.

Used new SupportVector library in some icons cases for a better UI.

Used new [Google Firebase](http://firebase.google.com/) as application Analytics and CrashReport services. 

Sample test cases are implemented by mockito, espresso, robolectric and mocking dagger.

Finally, used [circleci](https://circleci.com/gh/mmirhoseini/marvel) and [travis ci](https://travis-ci.org/mmirhoseini/marvel) as Continues Integration services which are bind with [Github](https://github.com/mmirhoseini/marvel) repo.

--------------------

### Read more... ###
You can read more about this sample project in this 5-part article:

* [Part 1: Lets get to know the project](https://medium.com/@mohsenoid/yet-another-mvp-article-part-1-lets-get-to-know-the-project-d3fd553b3e21#.h18h1lefy)
* [Part 2: How Dagger helps with the project](https://medium.com/@mohsenoid/yet-another-mvp-article-part-2-how-dagger-helps-with-the-project-90d049a45e00#.744fya69g)
* [Part 3: Calling APIs using Retrofit](https://medium.com/@mohsenoid/yet-another-mvp-article-part-3-calling-apis-using-retrofit-23757f4eee05#.8c1wpn1vz)
* [Part 4: RxJava and RxAndroid know how to response](https://medium.com/@mohsenoid/yet-another-mvp-article-part-4-rxjava-and-rxandroid-knows-how-to-response-cde42ccc4958#.pvk5vac37)
* [Part 5: Writing Test using a mixture of Dagger and Espresso](https://medium.com/@mohsenoid/yet-another-mvp-article-part-5-writing-test-using-a-mixture-of-dagger-and-espresso-15c638182706#.mildke14n)
 
 and to get the exact code at that point check out this tag:
 https://github.com/mohsenoid/marvel/releases/tag/Yet-another-MVP-article
