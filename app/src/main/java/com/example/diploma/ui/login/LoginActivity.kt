package com.example.diploma.ui.login

import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.diploma.R
import com.example.diploma.base.BaseActivity
import com.example.diploma.utils.NetworkUtils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginPresenter>(), LoginView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.onViewCreated()
        val listTeachersName = presenter.getTeachers()
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
            listTeachersName)
        teacher_name.setAdapter(adapter)
        initOnClickListener()
    }

    private fun initOnClickListener() {
        ok.setOnClickListener {
            presenter.clickButtonOk(teacher_name.text.toString())
        }
    }

    override fun onFinish() {
        finish()
    }

    override fun instantiatePresenter(): LoginPresenter {
        return LoginPresenter(this)
    }

    override fun onDialogNegativeClick() {
        onFinish()
    }

    override fun onDialogPositiveClick() {
        onFinish()
    }
}