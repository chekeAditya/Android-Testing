package com.application.androidtesting.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.application.androidtesting.R
import com.application.androidtesting.viewmodels.ShoppingViewModels


class AddShoppingItemFragment : Fragment(R.layout.fragment_add_shopping_item) {

    lateinit var viewModel: ShoppingViewModels

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModels::class.java)
    }


}