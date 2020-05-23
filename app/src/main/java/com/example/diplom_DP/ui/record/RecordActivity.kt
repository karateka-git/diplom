package com.example.diplom_DP.ui.record

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.diplom_DP.R
import com.example.diplom_DP.base.BaseActivity
import com.example.diplom_DP.databinding.ActivityRecordBinding
import com.example.diplom_DP.db.entity.RecordEntity
import com.example.diplom_DP.model.Record
import com.example.diplom_DP.ui.main.MainActivity
import com.example.diplom_DP.utils.DateAndTimeUtility
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : BaseActivity<RecordPresenter>(), RecordView {

    private lateinit var binding: ActivityRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val arguments = intent.extras
        presenter.onViewCreated()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_record)
        binding.dateAndTimeUtility = DateAndTimeUtility
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
            presenter.datePickerDialog(date.text.toString())
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
