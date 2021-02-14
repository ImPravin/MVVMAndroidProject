package com.example.shaadiapp.view.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.shaadiapp.R
import com.example.shaadiapp.framework.di.dataModules
import com.example.shaadiapp.framework.di.domainModules
import com.example.shaadiapp.framework.di.vmModules
import com.example.shaadiapp.framework.network.NetworkUtils
import com.example.shaadiapp.framework.network.Status
import com.example.shaadiapp.view.adapters.PersonRequestListAdapter
import com.example.shaadiapp.view.viewmodel.PersonRequestListModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.context.loadKoinModules


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: PersonRequestListModel
    private lateinit var adapter: PersonRequestListAdapter

    private val loadModules by lazy {
        loadKoinModules(listOf(vmModules, domainModules, dataModules))
    }

    private fun injectFeatures() = loadModules

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeatures()
        setContentView(R.layout.activity_main)
        viewModel = getViewModel()
        setupUI()
        setUpObservers()
    }

    private fun setupUI() {
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        adapter = PersonRequestListAdapter(viewModel,arrayListOf())
        recyclerView.adapter = adapter
        val mSnapHelper: SnapHelper = PagerSnapHelper()
        mSnapHelper.attachToRecyclerView(recyclerView)
    }

    private fun setUpObservers() {
        viewModel.allRequestsFromDataBase.observe(this, {
            progressBar.visibility = View.GONE
            if (it.isEmpty())
                Toast.makeText(this,getString(R.string.noRequestsFound),Toast.LENGTH_LONG).show()
            else {
                recyclerView.visibility = View.VISIBLE
                adapter.apply {
                    addUsers(it)
                    notifyDataSetChanged()
                }
            }
        })

        if (NetworkUtils().isOnline(this))
            showDataFromServer()
        else showDataFromDatabase()
    }

    private fun showDataFromDatabase() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.noInternetConnection)
        builder.setMessage(R.string.offlineDataMessage)
        builder.setPositiveButton(android.R.string.ok) { _, _ ->
            progressBar.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
            viewModel.getAllRequestFromDataBase()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun showDataFromServer() {
        viewModel.getAllRequestsFromServer().observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        viewModel.getAllRequestFromDataBase()
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }
}