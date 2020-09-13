package com.example.urbankotlinmvvm.view.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.urbankotlinmvvm.R
import com.example.urbankotlinmvvm.adapter.UrbanAdapter
import com.example.urbankotlinmvvm.util.Resource
import com.example.urbankotlinmvvm.view.MainActivity
import com.example.urbankotlinmvvm.viewmodel.DefinitionViewModel
import kotlinx.android.synthetic.main.fragment_home_page.*

class HomePageFragment : Fragment(R.layout.fragment_home_page) {

    lateinit var viewModel: DefinitionViewModel
    lateinit var urbanAdapter: UrbanAdapter

    val TAG = "Home Fragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

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