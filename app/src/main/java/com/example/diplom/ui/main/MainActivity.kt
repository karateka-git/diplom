package com.example.diplom.ui.main

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diplom.R
import com.example.diplom.base.BaseActivity
import com.example.diplom.model.Record
import com.example.diplom.utils.adapters.RecordsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainPresenter>(), MainView {

    @Inject
    lateinit var recordsAdapter: RecordsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAdapter()

        presenter.onViewCreated()

        initOnClickListener()
    }

    override fun instantiatePresenter(): MainPresenter {
        return MainPresenter(this)
    }

    fun initAdapter() {
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
}