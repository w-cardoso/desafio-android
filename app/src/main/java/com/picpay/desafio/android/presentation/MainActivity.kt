package com.picpay.desafio.android.presentation

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.presentation.adapter.UserListAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    private val adapterUser: UserListAdapter by inject()

    override fun onStart() {
        super.onStart()
        mainViewModel.interpret(MainInteractor.GetListUsers)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupsViewModel()
    }

    private fun setupsViewModel() {
        mainViewModel.viewStates.observe(this, Observer { viewStates ->
            viewStates.let {
                when (it) {
                    is MainDataStates.CallSucess -> {
                        populateViews(it.listUsers)
                    }

                    is MainDataStates.CallError -> {
                        activitiesEnds()
                    }
                }
            }
        })
    }

    private fun setupsHideLoading() {
        findViewById<ProgressBar>(R.id.user_list_progress_bar).visibility = View.GONE
    }

    private fun setupsShowLoading() {
        findViewById<ProgressBar>(R.id.user_list_progress_bar).visibility = View.VISIBLE
    }

    private fun activitiesEnds() = setupsHideLoading()

    private fun populateViews(listUsers: List<User>) {
        setupsShowLoading()
        findViewById<RecyclerView>(R.id.recyclerView).adapter = adapterUser
        adapterUser.users = listUsers
    }


}