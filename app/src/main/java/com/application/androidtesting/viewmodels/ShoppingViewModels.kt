package com.application.androidtesting.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.androidtesting.data.local.ShoppingItem
import com.application.androidtesting.other.Event
import com.application.androidtesting.other.Resource
import com.application.androidtesting.remote.responses.ImageResponse
import com.application.androidtesting.repositories.ShoppingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModels @Inject constructor(
    private val repository: ShoppingRepository
) : ViewModel() {

    val shoppingItems = repository.observeShoppingItem()

    val totalPrice = repository.observeTotalPrice()

    private val _images =
        MutableLiveData<Event<Resource<ImageResponse>>>() // we can only post values from fragment to this livedata

    val iamges: LiveData<Event<Resource<ImageResponse>>> =
        _images //we can't post newView value to this live data using fragment

    private val _currImageUrl = MutableLiveData<String>()

    val currImageUrl: LiveData<String> = _currImageUrl

    private val _insertShoppingItemStatus = MutableLiveData<Event<Response<ShoppingItem>>>()

    val insertShoppingItemStatus: LiveData<Event<Response<ShoppingItem>>> =
        _insertShoppingItemStatus


    fun setCurrImageUrl(url: String) {
        _currImageUrl.postValue(url)
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.deleteShoppingItem(shoppingItem)
    }

    fun insertShoppingItemToDb(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.insertShoppingItem(shoppingItem)
    }

    fun insertShoppingItem(name: String, amount: String, price: String) {

    }

    fun searchForImage(imageQuery: String) {

    }

}