package com.example.urbankotlinmvvm.view.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.urbankotlinmvvm.R
import com.example.urbankotlinmvvm.adapter.UrbanAdapter
import com.example.urbankotlinmvvm.util.Resource
import com.example.urbankotlinmvvm.view.MainActivity
import com.example.urbankotlinmvvm.viewmodel.DefinitionViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home_page.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomePageFragment : Fragment(R.layout.fragment_home_page) {

    lateinit var viewModel: DefinitionViewModel
    lateinit var urbanAdapter: UrbanAdapter
    private var navigateButton: ImageButton? = button_saved_definitions

    val TAG = "Home Fragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()


        navigateButton?.setOnClickListener {
            nav_host.findNavController().navigate(R.id.action_homePageFragment_to_savedPageFragment)
            Log.d("MainActivity", "Navigated to Saved")
        }

        var job: Job? = null
        et_search_text?.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                editable?.let {
                    if(editable.toString().isNotEmpty()) {
                        viewModel.getDefinitions(editable.toString())
                        Log.d("Search","Error")
                    }
                }
            }
        }

        viewModel.definitions.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {urbanResponse ->
                        urbanAdapter.differ.submitList(urbanResponse.list)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {message ->
                        Log.e(TAG, "An error has occured")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        urbanAdapter = UrbanAdapter()
        rvDefinitions.apply {
            adapter = urbanAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}