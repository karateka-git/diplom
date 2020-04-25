package com.example.diplom_DP.ui.updateSchedule

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.diplom_DP.R
import com.example.diplom_DP.base.BaseActivity
import com.example.diplom_DP.notification.NotificationHelper
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

    override fun scheduleUpdateSuccess(teacher: String,size: Int) {
        NotificationHelper.createSampleDataNotification(getContext(), getString(R.string.load_schedule),
            getString(R.string.success), getString(R.string.success_load_schedule, teacher, size), true)
    }

    override fun scheduleUpdateFail(teacher: String) {
        Toast.makeText(getContext(), getString(R.string.fail_load_schedule, teacher), Toast.LENGTH_SHORT).show()
    }

    override fun onFinish() {
        presenter.onViewDestroyed()
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