package com.example.urbankotlinmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

import com.example.urbankotlinmvvm.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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