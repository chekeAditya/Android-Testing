- Test cases test if our code is working.
- Without tests, you need to manually test the same functionality of your app over and over agina to verify it is working.
- With JUnit, we can test out code with a single click whenever we want.
- Types:-
    - Ui Test:- 10% → Tests that check if many or all components of your app work together well and if the ui looks like it should
    - Integration Tests:- 20% → Tests how two components of our app work together ( e.g fragment and viewModel)
    - Unit Tests:- 70%  → Tests of single units of our app (e.g testing the functions of a class)

![https://miro.medium.com/max/960/1*6M7_pT_2HJR-o-AXgkHU0g.jpeg](https://miro.medium.com/max/960/1*6M7_pT_2HJR-o-AXgkHU0g.jpeg)

# **What are Good tests?**

## TDD :- Test Driven Development

- Main principle: Write the test case before the implementation of the function (only for unit tests)
    1. Write the function signature.
    2. Write the test cases for that fuction.
    3. Write the function login so the tests pass.
- You should only have one assertion(the actual test) per test case.
- We immediately want to know the cause of a failed test case
- Sometimes there is no way around multiple assertions.

## What makes a good tests?

1. Scope:- how much the actualy code which we had to test is covered by our single test case.
2. Speed:- How fast our testcase runs. ( the faster you test case run the more often you will run your test cases and the more bugs you will eventually find).
3. Fidelity:-  How close our test cases is to a real senario.
- Not a flaky test ( sometimes succeeds and sometimes fails).
- never make the outcome of a test dependent on the outcome of another test.

## How many test casess should you write?

- As little tests as necessary, but also as many as necessary.
- Equivalent classes help us to determine the amount of tests a function should have.

SourceSet : - androidTest and test package 

- Android Test :- Now the difference between them is Test cases that only run on the android emulator so our instrumental unit test those test belongs to the "androidTest" Directory.
- Test :- Test which don't depend on any android component such as context those test cases comes in "test" directory. And these runs on the JVM not on the andorid machine which make them run faster.

```kotlin
/*Used only for test set */
testImplementation 'junit:junit:4.+'

/*	Used only for android test source set */
androidTestImplementation 'androidx.test.ext:junit:1.1.3'

/* This is the library for ui testing */
androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'

/*  This will make our assertion much more readable fast*/
testImplementation "com.google.truth:truth:1.1.1"
```
![Testing Room Databases - Testing on Android - Part 6 - YouTube - Brave 12_5_2021 10_18_16 PM](https://user-images.githubusercontent.com/81345503/144755633-a87ea872-152f-4391-9b4f-5031df858e4c.png)
```kotlin
@SmallTest //these that whatever we write here are unit test's
@MediumTest //used for integrated Test
@LargeTest //end to end test or ui test
```
