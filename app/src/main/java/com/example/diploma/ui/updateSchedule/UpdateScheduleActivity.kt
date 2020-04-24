package com.example.diploma.ui.updateSchedule

import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.diploma.R
import com.example.diploma.base.BaseActivity
import kotlinx.android.synthetic.main.activity_update_schedule.*

class UpdateScheduleActivity : BaseActivity<UpdateSchedulePresenter>(), UpdateScheduleView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_schedule)
        presenter.onViewCreated()
        val listTeachersName = presenter.getTeachers()
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
            listTeachersName)
        teacher_name.setAdapter(adapter)
        initOnClickListener()
    }

    private fun initOnClickListener() {
        ok.setOnClickListener {
            presenter.clickButtonOk(teacher_name.text.toString().trim())
        }
    }

    override fun onFinish() {
        finish()
    }

    override fun instantiatePresenter(): UpdateSchedulePresenter {
        return UpdateSchedulePresenter(this)
    }

    override fun onDialogNegativeClick() {
        onFinish()
    }

    override fun onDialogPositiveClick() {
        onFinish()
    }
}