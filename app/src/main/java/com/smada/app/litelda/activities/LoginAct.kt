package com.smada.app.litelda.activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.smada.app.litelda.R
import com.smada.app.litelda.databinding.ActivityLoginBinding
import com.smada.app.litelda.databinding.FragmentBookXBinding
import com.smada.app.litelda.sharedpref.AppClassSession.Companion.sessionManager
import com.smada.app.litelda.sharedpref.SessionManager
import com.smada.app.litelda.sharedpref.SessionManager.Companion.SESSION_TOKEN


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

        sessionManager = SessionManager(_context = applicationContext)
        //check loggedin or not
        if(sessionManager.getLogInStatus(SESSION_TOKEN)){
            finish()
            startActivity(Intent(this@LoginAct, DashboardAct::class.java))
        } else { //stay in login page
        }

    }

private fun login() {
    val usernameL = binding.namaET.text.toString()
    val passwordL = binding.katasandiET.text.toString()

    if(usernameL == "fixitsmada" && passwordL == "smada2022" || usernameL == "1" && passwordL == "1" ){
        sessionManager.isLoggedIn(SESSION_TOKEN,true)
        sessionManager.saveString("nama",usernameL)
        sessionManager.saveString("email","admin@sman2lamongan.sch.id")
        val goToDashboard = Intent(this@LoginAct, DashboardAct::class.java)
        startActivity(goToDashboard)
    } else {
        val popUpDialogAlert: AlertDialog.Builder = AlertDialog.Builder(this)

        popUpDialogAlert.setMessage("Maaf password atau username salah")
        popUpDialogAlert.setTitle("LOGIN GAGAL")
        popUpDialogAlert.setPositiveButton("OK", null)
        popUpDialogAlert.setCancelable(true)
        popUpDialogAlert.create().show()

        popUpDialogAlert.setPositiveButton("Ok"
        ) { _, _ -> }

    }


}

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login ->login()
        }
    }
}