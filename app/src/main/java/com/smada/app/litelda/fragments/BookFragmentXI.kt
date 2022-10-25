package com.smada.app.litelda.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.smada.app.litelda.R
import com.smada.app.litelda.databinding.FragmentBookXBinding
import com.smada.app.litelda.databinding.FragmentBookXiBinding
import java.io.*


class BookFragmentXI : Fragment(), View.OnClickListener {

    private var _binding: FragmentBookXiBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBookXiBinding.inflate(layoutInflater)
        _binding!!.imgxiPkn.setOnClickListener(this)
        _binding!!.imgxiSejarah.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.imgxi_pkn -> {


            } R.id.imgxi_sejarah -> {


        }
        }
    }

}