package com.example.urbankotlinmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.urbankotlinmvvm.R
import com.example.urbankotlinmvvm.db.DefinitionDatabase
import com.example.urbankotlinmvvm.repo.Repository
import com.example.urbankotlinmvvm.viewmodel.DefinitionViewModel
import com.example.urbankotlinmvvm.viewmodel.DefinitionViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*

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
        button_saved_definitions.setOnClickListener {
            nav_host.findNavController().navigate(R.id.action_homePageFragment_to_savedPageFragment)
            Log.d("MainActivity", "Navigated to Saved")
        }
    }

    fun navigateToHome(view: View) {
        button_home.setOnClickListener {
            nav_host.findNavController().navigate(R.id.action_savedPageFragment_to_homePageFragment)
            Log.d("MainActivity", "Navigated to Saved")
        }
    }

}
