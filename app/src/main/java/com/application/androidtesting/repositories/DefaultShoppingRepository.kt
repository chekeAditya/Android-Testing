package com.application.androidtesting.repositories

import androidx.lifecycle.LiveData
import com.application.androidtesting.data.local.ShoppingDao
import com.application.androidtesting.data.local.ShoppingItem
import com.application.androidtesting.other.Resource
import com.application.androidtesting.remote.PixabayApi
import com.application.androidtesting.remote.responses.ImageResponse
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabayApi: PixabayApi
) : ShoppingRepository {

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)

    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeShoppingItem(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observerAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayApi.searchForImage(imageQuery)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown Error occured", null)
            } else {
                Resource.error("An Unknown Error has been occured", null)
            }
        } catch (e: Exception) {
            Resource.error("Error Check the internet connection", null)
        }
    }


}