package com.example.diplom.ui.main

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diplom.R
import com.example.diplom.base.BaseActivity
import com.example.diplom.model.Record
import java.util.*

class MainActivity : BaseActivity<MainPresenter>(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.notes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        presenter.onViewCreated()
        recyclerView.adapter = presenter.getNotesAdapter()
    }

    override fun instantiatePresenter(): MainPresenter {
        return MainPresenter(this)
    }
}