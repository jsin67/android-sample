# android-sample
Following is the tech-stack for this sample. 
- [x] MVP: Chosen MVP to segrate business logic from UI logic.  I haven't used MVVM because there was no need to use android architecture component like Room, ViewModel etc. so held it back. 
- [x] Kotlin: Used kotlin for business, view, and test cases. 
- [x] RxJava : To fetch the response from API. Since I have used RxJava, I needed to mock the Schedular to run the test cases. 
- [x] Retrofit : Called API with query params.
- [x] Dagger : Dependency injector to create repository obj. 
- [x] Mockito : To mock the dependency.
- [x] TDD: Presenter contains all the business logic. The test cases for it is also written which has code coverage ~95%.
- [x] Errors: Handled network connection, API, parsing error and showed toast on UI to notify user.

Note: The UI of this application is very simple which is showing the title of the venue.  