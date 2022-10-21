package com.smada.app.litelda.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.WindowCompat
import com.smada.app.litelda.R
import com.smada.app.litelda.databinding.ActivityLoginBinding
import java.lang.NullPointerException

class LoginAct : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login -> {
                val goToDashboard = Intent(this@LoginAct, DashboardAct::class.java)
                startActivity(goToDashboard)
            }
        }
    }
}