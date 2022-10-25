package com.smada.app.litelda.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.smada.app.litelda.R
import com.smada.app.litelda.databinding.FragmentAboutappBinding

class AboutApp : Fragment(), View.OnClickListener {

    private var _binding: FragmentAboutappBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding= FragmentAboutappBinding.inflate(layoutInflater)
        _binding!!.btnRate.setOnClickListener(this)
        return binding.root
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(requireContext(),R.style.Theme_LITELDA)
            .create()
        //Inflate the dialog as custom view
        val dialog = LayoutInflater.from(activity).inflate(R.layout.customdialog_rating, null)
        val dialogBuilder = AlertDialog.Builder(requireActivity()).setView(dialog)

        //val btnRate : Button = requireView().findViewById(R.id.customdialog_button) as Button
        //dialogBuilder.setView(view)
        //show dialog
        val  messageBoxInstance = dialogBuilder.show()
        //set Listener
        dialog.setOnClickListener(){
            //close dialog
            messageBoxInstance.dismiss()
        }

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_rate -> {
                showDialog()
            }
        }
    }

}