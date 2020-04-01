package com.example.diplom.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplom.R
import com.example.diplom.base.BaseActivity
import com.example.diplom.injection.component.DaggerMainActivityInjector
import com.example.diplom.model.Record
import com.example.diplom.ui.record.RecordActivity
import com.example.diplom.utils.adapters.RecordsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainPresenter>(), MainView, RecordsAdapter.OnRecordListener {

    @Inject
    lateinit var recordsAdapter: RecordsAdapter
//    val recordsAdapter = RecordsAdapter(this)

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

    override fun setRecords(records: List<Record>) {
        recordsAdapter.updateRecords(records)
    }

    private fun initOnClickListener() {
        date.setOnClickListener{
            presenter.datePickerDialog()
        }
    }

    override fun setDate(date: String) {
        this.date.text = date
    }

    override fun onRecordClick(position: Int) {
        val intent: Intent = Intent(this, RecordActivity::class.java)
        intent.putExtra(Record::class.java.simpleName, presenter.getRecord(position))
        startActivity(intent)
    }
}