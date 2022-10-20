package com.smada.app.litelda.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.smada.app.litelda.R

class LoginAct : AppCompatActivity() {

    //private lateinit var binding: LoginActBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}