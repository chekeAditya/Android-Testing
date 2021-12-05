package com.application.androidtesting.repositories

import androidx.lifecycle.LiveData
import com.application.androidtesting.data.local.ShoppingItem
import com.application.androidtesting.other.Resource
import com.application.androidtesting.remote.responses.ImageResponse
import retrofit2.Response

interface ShoppingRepository {


    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>

    fun observeShoppingItem(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

}