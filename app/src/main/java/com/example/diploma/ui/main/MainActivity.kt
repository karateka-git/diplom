package com.example.diploma.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diploma.R
import com.example.diploma.base.BaseActivity
import com.example.diploma.injection.component.DaggerMainActivityInjector
import com.example.diploma.model.Record
import com.example.diploma.ui.record.RecordActivity
import com.example.diploma.utils.DownloadData
import com.example.diploma.utils.ParserXML
import com.example.diploma.utils.adapters.RecordsAdapter
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_all_records.*
import kotlinx.android.synthetic.main.activity_main_drawer.*
import kotlinx.android.synthetic.main.bottom_navigation.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainPresenter>(), MainView, RecordsAdapter.OnRecordListener,
    NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var recordsAdapter: RecordsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_drawer)
        initDrawer()
        DaggerMainActivityInjector
            .builder()
            .listener(this)
            .build().inject(this)

        initAdapter()

        presenter.onViewCreated()

        initOnClickListener()

        val downloadData = DownloadData()

        CoroutineScope(Dispatchers.Main).launch {
            downloadData.process()
        }
    }

    override fun instantiatePresenter(): MainPresenter {
        return MainPresenter(this)
    }

    private fun initAdapter() {
        records.layoutManager = LinearLayoutManager(this)
        records.adapter = recordsAdapter
    }

    private fun initDrawer() {
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.app_name, 0
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun setRecords(records: List<Record>) {
        recordsAdapter.updateRecords(records)
    }

    private fun initOnClickListener() {
        date.setOnClickListener {
            presenter.datePickerDialog()
        }
        fab.setOnClickListener {
            val intent = Intent(this, RecordActivity::class.java)
            startActivity(intent)
        }
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.all_records -> Toast.makeText(this, "Fav", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    override fun setDate(date: String) {
        this.date.text = date
    }

    override fun onRecordClick(record: Record) {
        val intent = Intent(this, RecordActivity::class.java)
        intent.putExtra(Record::class.java.simpleName, record)
        startActivity(intent)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_messages -> {
                Toast.makeText(this, "Messages clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_friends -> {
                Toast.makeText(this, "Friends clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_update -> {
                Toast.makeText(this, "Update clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Sign out clicked", Toast.LENGTH_SHORT).show()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}