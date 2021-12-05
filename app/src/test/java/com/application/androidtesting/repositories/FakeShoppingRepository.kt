package com.application.androidtesting.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.application.androidtesting.data.local.ShoppingItem
import com.application.androidtesting.other.Resource
import com.application.androidtesting.remote.responses.ImageResponse

/** Fake test doubles and it's basically very well suited for test cases but not very well suited for production
 *  It's work is just to stimulate the work of the real repo not it's not doing work of real repo
 *  We created this class to later test the viewModel class because our viewModel will take repo as a parameter
    and then we can simply make repository in the viewModels constructor or type shoppingRepo
 */

class FakeShoppingRepository : ShoppingRepository {

    private val shoppingItems = mutableListOf<ShoppingItem>()

    private val observableShoppingItems = MutableLiveData<List<ShoppingItem>>(shoppingItems)
    private val observableTotalPrice = MutableLiveData<Float>()

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData() {
        observableShoppingItems.postValue(shoppingItems)
        observableTotalPrice.postValue(getTotalPrice())
    }

    private fun getTotalPrice(): Float {
        return shoppingItems.sumByDouble { it.price.toDouble() }.toFloat()
    }

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.add(shoppingItem)
        refreshLiveData()
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.remove(shoppingItem)
        refreshLiveData()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return if (shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(ImageResponse(listOf(), 0, 0))
        }
    }

    override fun observeShoppingItem(): LiveData<List<ShoppingItem>> {
        return observeShoppingItem()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return observableTotalPrice
    }

}