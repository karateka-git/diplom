package com.example.diploma.ui.record

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.diploma.R
import com.example.diploma.base.BaseActivity
import com.example.diploma.databinding.ActivityRecordBinding
import com.example.diploma.db.entity.RecordEntity
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
            arguments.getSerializable(Record::class.java.simpleName) as RecordEntity
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
        this.time.setOnClickListener {
            presenter.timePickerDialog()
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

    override fun setTime(time: String) {
        this.time.setText(time)
    }

    override fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
