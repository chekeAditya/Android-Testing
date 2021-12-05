package com.application.androidtesting.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    @Delete
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    @Query("select * from shopping_items")
    fun observerAllShoppingItems(): LiveData<List<ShoppingItem>>

    @Query("select sum(price * amount) from shopping_items")
    fun observeTotalPrice() : LiveData<Float>

}