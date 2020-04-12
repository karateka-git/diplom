package com.example.diploma.ui.login

import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.diploma.R
import com.example.diploma.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginPresenter>(), LoginView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val listTeachersName = presenter.getTeachers()
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
            listTeachersName)
        teacher_name.setAdapter(adapter)
    }

    override fun instantiatePresenter(): LoginPresenter {
        return LoginPresenter(this)
    }
}