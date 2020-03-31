package com.example.diplom.ui.record

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.diplom.R
import com.example.diplom.base.BaseActivity
import com.example.diplom.databinding.ActivityRecordBinding
import com.example.diplom.model.Record
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : BaseActivity<RecordPresenter>(), RecordView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val arguments = intent.extras
        if (arguments != null) {
            val binding = DataBindingUtil.setContentView<ActivityRecordBinding>(this, R.layout.activity_record)
            binding.record = arguments.getSerializable(Record::class.java.simpleName) as Record
        }
        initOnClickListener()
    }

    override fun instantiatePresenter(): RecordPresenter {
        return RecordPresenter(this)
    }

    private fun initOnClickListener() {
        this.date.setOnClickListener{
            presenter.datePickerDialog()
        }
    }

    override fun setDate(date: String) {
        this.date.text = date
    }
}
