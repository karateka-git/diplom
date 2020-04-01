package com.example.diplom.ui.record

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.diplom.R
import com.example.diplom.base.BaseActivity
import com.example.diplom.databinding.ActivityRecordBinding
import com.example.diplom.model.Record
import com.example.diplom.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : BaseActivity<RecordPresenter>(), RecordView {

    private lateinit var binding: ActivityRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val arguments = intent.extras
        if (arguments != null) {
            val record = arguments.getSerializable(Record::class.java.simpleName) as Record
            binding = DataBindingUtil.setContentView<ActivityRecordBinding>(this, R.layout.activity_record)
            binding.record = record
        }

        presenter.onViewCreated()

        initOnClickListener()
    }

    override fun instantiatePresenter(): RecordPresenter {
        return RecordPresenter(this)
    }

    override fun getBundle(): Bundle? {
        return intent.extras
    }

    override fun getBinding(): ActivityRecordBinding {
        return binding
    }

    private fun initOnClickListener() {
        this.date.setOnClickListener{
            presenter.datePickerDialog()
        }
        this.button_ok.setOnClickListener{
            presenter.clickButtonOk()
        }
    }

    override fun setDate(date: String) {
        this.date.text = date
    }

    override fun clickButtonOk() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
