package com.application.androidtesting.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.application.androidtesting.R
import com.application.androidtesting.viewmodels.ShoppingViewModels


class ShoppingFragment : Fragment(R.layout.fragment_shopping) {

    lateinit var viewModel: ShoppingViewModels

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModels::class.java)
    }

}