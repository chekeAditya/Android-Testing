package com.application.androidtesting.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.application.androidtesting.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class) // this is Junit that this test is the instrumental test
@SmallTest //these that whatever we write here are unit test's
//@MediumTest //used for integrated Test
//@LargeTest //end to end test or ui test
class ShoppingDaoTest {

    @get:Rule
    var instaExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ShoppingItemDatabase
    private lateinit var dao: ShoppingDao

    @Before
    fun setup() {
        /* Here inMemoryDatabaseBuilder means it will only hold database in the RAM not inside the
           persistent storage i.e will save only for that testcase
         * allowMainThreadQueries() -> we allow this room database from the main thread.
         * If we run every test in the background thread then it will manipulate each other. That's why we do it in the Main thread
         */
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingItemDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.shoppingDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    /* We don't want concurrency here that's why we are using runBlocking here
     * This runBlocking function will delete the delay function if we had inside the function(testcase)

     * How to check the function is working fine or not?
     -> We will call the function in the testcase then will insert an item and will check will it present or not that's it.
     */

    @Test
    fun insertShoppingItem() = runBlockingTest {
        /** Inserting item into the database */
        val shoppingItem = ShoppingItem(
            "name",
            1, 1f, "url", id = 1
        )
        dao.insertShoppingItem(shoppingItem)

        /* Checking item present into the database or not
         * Here this allShoppingItem return list of ShoppingItems
         */
        val allShoppingItems = dao.observerAllShoppingItems().getOrAwaitValue()
        assertThat(allShoppingItems).contains(shoppingItem)

    }

    @Test
    fun deleteShoppingItem() = runBlockingTest {
        val shoppingItem = ShoppingItem("name", 1, 1f, "url", id = 1)
        dao.insertShoppingItem(shoppingItem)
        dao.deleteShoppingItem(shoppingItem)

        val allShoppingItem = dao.observerAllShoppingItems().getOrAwaitValue()
        assertThat(allShoppingItem).doesNotContain(shoppingItem)
    }

    @Test
    fun observeTotalPriceSum() = runBlockingTest {
        val shoppingItem = ShoppingItem("name", 2, 10f, "url", id = 1)
        val shoppingItem1 = ShoppingItem("name", 4, 5.5f, "url", id = 2)
        val shoppingItem2 = ShoppingItem("name", 0, 100f, "url", id = 3)
        dao.insertShoppingItem(shoppingItem)
        dao.insertShoppingItem(shoppingItem1)
        dao.insertShoppingItem(shoppingItem2)
        val totalPriceSum = dao.observeTotalPrice().getOrAwaitValue()
        assertThat(totalPriceSum).isEqualTo(2 * 10f + 4 * 5.5f)
    }

}