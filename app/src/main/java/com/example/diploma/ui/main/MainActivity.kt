package com.example.diploma.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diploma.R
import com.example.diploma.base.BaseActivity
import com.example.diploma.db.entity.RecordEntity
import com.example.diploma.injection.component.DaggerMainActivityInjector
import com.example.diploma.model.Record
import com.example.diploma.ui.login.LoginActivity
import com.example.diploma.ui.record.RecordActivity
import com.example.diploma.utils.adapters.RecordsAdapter
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_all_records.*
import kotlinx.android.synthetic.main.activity_main_drawer.*
import kotlinx.android.synthetic.main.bottom_navigation.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainPresenter>(), MainView, RecordsAdapter.OnRecordListener,
    NavigationView.OnNavigationItemSelectedListener {

    private val classTag = MainActivity::class.java.simpleName

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

        presenter.onViewCreated()

        initOnClickListener()
        initAdapter()
    }

    override fun instantiatePresenter(): MainPresenter {
        return MainPresenter(this)
    }

    private fun initAdapter() {
        records.layoutManager = LinearLayoutManager(this)
        records.adapter = recordsAdapter
        val liveData = presenter.getValues()
        liveData.observe(this, androidx.lifecycle.Observer {records ->
            records?.let{
                Log.d(classTag, "From observe")
                recordsAdapter.updateRecords(it)
            }
        })
        setupBottomNavigation()
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

    private fun initOnClickListener() {
        date.setOnClickListener {
            presenter.datePickerDialog()
        }
        fab.setOnClickListener {
            val intent = Intent(this, RecordActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setupBottomNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.all_records -> presenter.setSelectedRecords(R.id.all_records)
                R.id.my_records -> presenter.setSelectedRecords(R.id.my_records)
                R.id.holiday_records -> presenter.setSelectedRecords(R.id.holiday_records)
            }
            true
        }

        bottom_navigation.selectedItemId = R.id.all_records
    }

    override fun getCurrentIndexSelectedRecords(): Int {
        return bottom_navigation.selectedItemId
    }

    override fun setDate(date: String) {
        this.date.text = date
        presenter.setDate(date)
    }

    override fun onRecordClick(record: Record) {
        val intent = Intent(this, RecordActivity::class.java)
        intent.putExtra(Record::class.java.simpleName, record)
        startActivity(intent)
    }

    override fun onCompletedChange(record: Record) {
        presenter.completedChange(record)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_update -> {
                Toast.makeText(this, "Update clicked", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Sign out clicked", Toast.LENGTH_SHORT).show()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}