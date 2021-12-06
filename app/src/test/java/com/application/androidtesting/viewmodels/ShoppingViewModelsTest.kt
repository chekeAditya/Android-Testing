package com.application.androidtesting.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.application.androidtesting.MainCoroutineRule
import com.application.androidtesting.getOrAwaitValueTest
import com.application.androidtesting.other.Constants
import com.application.androidtesting.other.Status
import com.application.androidtesting.repositories.FakeShoppingRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class ShoppingViewModelsTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ShoppingViewModels

    /** Here we can't write defaultShoppingRepo as it contains parameter like dao and pixabayAPI instead of that we can use FakeShoppingRepository
     * FakeShoppingRepository :- it's just emitting the behaviour of the real repo
     * */
    @Before
    fun setup() {
        viewModel = ShoppingViewModels(FakeShoppingRepository())
    }

    @Test
    fun `insert shopping item with empty field, return error`() {
        viewModel.insertShoppingItem("name", "", "3.0")
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        /* here resource should return an error here*/
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }


    @Test
    fun `insert shopping item with two long name, return error`() {
        /* Here we are dependent on the Max_Name_Length and we used this as if max_length_name changes the this string will also changes */
        val string = buildString {
            for (i in 1..Constants.MAX_NAME_LENGTH + 1) {
                append(1)
            }
        }
        viewModel.insertShoppingItem(string, "5", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with two long price, return error`() {
        /* Here we are dependent on the Max_Name_Length and we used this as if max_length_name changes the this string will also changes */
        val string = buildString {
            for (i in 1..Constants.MAX_PRICE_LENGTH + 1) {
                append(1)
            }
        }
        viewModel.insertShoppingItem("name", "5", string)

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with two high amount, return error`() {

        viewModel.insertShoppingItem("name", "5234023747239042903402", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with valid input, return success`() {

        viewModel.insertShoppingItem("name", "5", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }

}