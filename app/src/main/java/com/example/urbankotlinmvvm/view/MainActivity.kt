package com.example.urbankotlinmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation

import com.example.urbankotlinmvvm.R
import com.example.urbankotlinmvvm.db.DefinitionDatabase
import com.example.urbankotlinmvvm.repo.Repository
import com.example.urbankotlinmvvm.viewmodel.DefinitionViewModel
import com.example.urbankotlinmvvm.viewmodel.DefinitionViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: DefinitionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository(DefinitionDatabase(this))
        val viewModelProviderFactory = DefinitionViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(DefinitionViewModel::class.java)

    }

    fun navigateToSaved(view: View) {
        Navigation.createNavigateOnClickListener(R.id.action_homePageFragment_to_savedPageFragment)
        Log.d("Navigated", "Navigated")
    }

    fun navigateToHome(view: View) {
        Navigation.createNavigateOnClickListener(R.id.action_savedPageFragment_to_homePageFragment)
        Log.d("Navigate", "Navigated to Home")
    }
}