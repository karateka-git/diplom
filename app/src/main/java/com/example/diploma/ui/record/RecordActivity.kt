package com.example.diploma.ui.record

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.diploma.R
import com.example.diploma.base.BaseActivity
import com.example.diploma.databinding.ActivityRecordBinding
import com.example.diploma.model.Record
import com.example.diploma.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : BaseActivity<RecordPresenter>(), RecordView {

    private lateinit var binding: ActivityRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val arguments = intent.extras
        presenter.onViewCreated()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_record)
        binding.record = if (arguments != null) {
            arguments.getSerializable(Record::class.java.simpleName) as Record
        } else {
            presenter.getEmptyRecord()
        }

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
        this.button_cancel.setOnClickListener{
            finish()
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
