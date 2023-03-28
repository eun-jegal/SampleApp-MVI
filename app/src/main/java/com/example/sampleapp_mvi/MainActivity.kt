package com.example.sampleapp_mvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampleapp_mvi.data.model.UserItem
import com.example.sampleapp_mvi.data.model.MainIntent
import com.example.sampleapp_mvi.data.model.MainState
import com.example.sampleapp_mvi.databinding.ActivityMainBinding
import com.example.sampleapp_mvi.ui.MainViewModel
import com.example.sampleapp_mvi.ui.MainViewModelFactory
import com.example.sampleapp_mvi.ui.UserAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private val userAdapter = UserAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        initLayout()
        updateUIFromState()
    }

    private fun initLayout() {
        binding.apply {
            btnUpdateData.setOnClickListener {
                // Send intent to viewModel
                viewModel.sendIntent(MainIntent.FetchUser)
            }
            recyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = userAdapter
            }
        }
    }

    private fun updateUIFromState() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is MainState.Idle -> {}
                    is MainState.Loading -> {
                        binding.apply {
                            btnUpdateData.visibility = View.GONE
                            progressBar.visibility = View.VISIBLE
                        }
                    }
                    is MainState.Users -> {
                        binding.apply {
                            progressBar.visibility = View.GONE
                            btnUpdateData.visibility = View.GONE
                            updateUserData(it.user)
                        }
                    }
                    is MainState.Error -> {
                        binding.apply {
                            progressBar.visibility = View.GONE
                            btnUpdateData.visibility = View.VISIBLE
                            Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun updateUserData(users: List<UserItem>) {
        binding.recyclerView.visibility = View.VISIBLE
        userAdapter.addData(users)
        userAdapter.notifyDataSetChanged()
    }
}