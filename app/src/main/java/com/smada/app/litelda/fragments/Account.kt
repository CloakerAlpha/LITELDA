package com.smada.app.litelda.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smada.app.litelda.activities.LoginAct
import com.smada.app.litelda.databinding.FragmentAccountBinding
import com.smada.app.litelda.sharedpref.AppClassSession

class Account : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAccountBinding.inflate(layoutInflater)

        val usernameSP = AppClassSession.sessionManager.getString("nama", )
        val emailSP = AppClassSession.sessionManager.getString("email", )
        _binding?.usernameTIET?.setText(usernameSP)
        _binding?.emailTIET?.setText(emailSP)

        _binding!!.btnOut.setOnClickListener {
            AppClassSession.sessionManager.clearSession()
            val thisActivity: Activity? = activity
            if (thisActivity != null) {
                startActivity(Intent(thisActivity, LoginAct::class.java))
                thisActivity.finish()
            }
            Log.d("btnLogout", "SIGNED OUT")
        }

        return binding.root
    }

}