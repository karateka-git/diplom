package com.example.diploma.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diploma.R
import com.example.diploma.base.BaseActivity
import com.example.diploma.injection.component.DaggerMainActivityInjector
import com.example.diploma.model.Record
import com.example.diploma.ui.record.RecordActivity
import com.example.diploma.utils.adapters.RecordsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainPresenter>(), MainView, RecordsAdapter.OnRecordListener {

    @Inject
    lateinit var recordsAdapter: RecordsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerMainActivityInjector
            .builder()
            .listener(this)
            .build().inject(this)

        initAdapter()

        presenter.onViewCreated()

        initOnClickListener()
    }

    override fun instantiatePresenter(): MainPresenter {
        return MainPresenter(this)
    }

    private fun initAdapter() {
        records.layoutManager = LinearLayoutManager(this)
        records.adapter = recordsAdapter
    }

    override fun setRecords(records: Map<UUID, Record>) {
        recordsAdapter.updateRecords(records.values.toList())
    }

    private fun initOnClickListener() {
        date.setOnClickListener {
            presenter.datePickerDialog()
        }
        add.setOnClickListener {
            val intent = Intent(this, RecordActivity::class.java)
            startActivity(intent)
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
}