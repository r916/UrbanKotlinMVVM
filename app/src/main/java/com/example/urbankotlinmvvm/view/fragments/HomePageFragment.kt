package com.example.urbankotlinmvvm.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.urbankotlinmvvm.R
import com.example.urbankotlinmvvm.view.MainActivity
import com.example.urbankotlinmvvm.viewmodel.DefinitionViewModel

class HomePageFragment : Fragment(R.layout.fragment_home_page) {

    lateinit var viewModel: DefinitionViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }
}