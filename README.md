## To Do App (Android)
A simple to do application for Android

![Screenshot 2025-01-07 at 6 59 09 PM](https://github.com/user-attachments/assets/bab649c8-6895-40bb-b76b-b0ea2282e693)

### Architecture
This project is designed based on the Model-View-ViewModel architecture. The package structure is as follows:
- <b>ui</b>: this package contains all ui related components (Activities, Fragments, RecyclerViews, etc)
- <b>viewmodel</b>: this package contains all the viewmodels.
- <b>usecase</b>: this package contains all the use cases.
- <b>repository</b>: this package contains all the repositories
- <b>model</b>: this package contains all the model classes (POJOs)

### Other packages
- <b>database / dao</b>: these packages contain all the databases related classes
- <b>di</b>: this package contains all the Dagger Hilt dependancy injection classes
- <b>extension</b>: this package contains all the Kotlin extension functions

## Dependancy Injection
This project uses Dagger Hilt for dependancy injection. For more details, please refer: https://developer.android.com/training/dependency-injection/hilt-android

## Database
This project uses the Room database for data persistance. For more details, please refer: https://developer.android.com/training/data-storage/room

## Unit Tests
To execute all unit tests, you can run the following command: ` ./gradlew test`

## Linting
KtLint can be executed using the following command: `./gradlew lint`
