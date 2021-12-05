package com.application.androidtesting.Second

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.application.androidtesting.R
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class ResourceComparerTest {

    /**
     * Why this is the bad practise to initialise resource compiler directly as the global valuable ?
     * Answer:- Our test should run independent but right not they don't they lie on the resourceComparer
    and that are not independent anymore.
    :- Here it won't make a huge difference as it's not dependent or it don't have the constructor.
    :- it can also very often produce flaky tests( test that sometime succeed and sometimes fails)

     */
//    private val resourceComparer = ResourceComparer()

    /** This time it not dependent as each one created there own instances of the test cases but what
    if we had so many test cases then we had to initialize everytime.

     * For that JUnit have a very cool solution for that i.e creating a setup Function.
       use is if we wrote with @Before annotation then it will execute before every testcase.
     */
    private lateinit var resourceComparer: ResourceComparer

    @Before // usually called if we wrote with @Before annotation then it will execute before every testcase.
    fun setup() {
        resourceComparer = ResourceComparer()
    }

    @After
    fun tearDown(){
        // we usually do this if we wrote the room database then we had to close it once it's work is done.
    }


    @Test
    fun stringResourceSameAsGivenString_returnTrue() {
//        resourceComparer = ResourceComparer()
        val context =
            ApplicationProvider.getApplicationContext<Context>() /* here we are calling the context to pass it in the nextLine */
        val result = resourceComparer.iSEqual(context, R.string.app_name, "Android Testing")
        assertThat(result).isTrue()
    }

    @Test
    fun stringResourceDifferentAsGivenString_returnFalse() {
//        resourceComparer = ResourceComparer()
        val context =
            ApplicationProvider.getApplicationContext<Context>() /* here we are calling the context to pass it in the nextLine */
        val result = resourceComparer.iSEqual(context, R.string.app_name, "Aditya")
        assertThat(result).isFalse()
    }

}