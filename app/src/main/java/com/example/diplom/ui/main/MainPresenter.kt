package com.example.diplom.ui.main

import android.content.Context
import android.util.Log
import com.example.diplom.R
import com.example.diplom.base.BasePresenter
import com.example.diplom.model.Record
import java.util.*
import javax.inject.Inject

class MainPresenter(mainView: MainView) : BasePresenter<MainView>(mainView) {
    @Inject
    lateinit var context: Context

    @Inject
    lateinit var recordsAdapter: RecordsAdapter

    override fun onViewCreated() {
        super.onViewCreated()
        Log.e("TEST", view.getContext().getString(R.string.test))
    }

    fun getNotesAdapter(): RecordsAdapter {
        recordsAdapter.updateNotes(generateFakeRecords())
        return recordsAdapter;
    }

    private fun generateFakeRecords(): List<Record> {
        val values = mutableListOf<Record>()
        values.add(Record(Date(), "test1", "test1 tester1 testers1"))
        values.add(Record(Date(), "test2", "test2 tester2 testers2"))
        values.add(Record(Date(), "test3", "test3 tester3 testers3"))
        return values
    }
}