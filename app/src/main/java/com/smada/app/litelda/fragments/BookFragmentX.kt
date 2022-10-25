package com.smada.app.litelda.fragments

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ShareCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.smada.app.litelda.BuildConfig
import com.smada.app.litelda.R
import com.smada.app.litelda.databinding.FragmentBookXBinding
import java.io.File
import java.util.*


class BookFragmentX : Fragment(), View.OnClickListener {

    private var _binding: FragmentBookXBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBookXBinding.inflate(layoutInflater)
        _binding!!.imgxPkn.setOnClickListener(this)
        _binding!!.imgxSejarah.setOnClickListener(this)
        _binding!!.imgxMtk.setOnClickListener(this)
        _binding!!.imgxPai.setOnClickListener(this)
        _binding!!.imgxBin.setOnClickListener(this)
        _binding!!.imgxBing.setOnClickListener(this)
        _binding!!.imgxIpa.setOnClickListener(this)
        _binding!!.imgxIps.setOnClickListener(this)
        _binding!!.imgxInformatika.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.imgx_pkn -> {
                var resourceName = "x_ppkn.pdf"
                openPDF2(resourceName)
            } R.id.imgx_sejarah -> {
                var resourceName = "x_sejarah.pdf"
                openPDF2(resourceName)
            } R.id.imgx_mtk -> {
                var resourceName = "x_mtk.pdf"
                openPDF2(resourceName)
            } R.id.imgx_pai -> {
                var resourceName = "all_PAI.pdf"
                openPDF2(resourceName)
            } R.id.imgx_bin -> {
                var resourceName = "x_bahasaindonesia.pdf"
                openPDF2(resourceName)
            } R.id.imgx_bing -> {
                var resourceName = "x_bahasainggris.pdf"
                openPDF2(resourceName)
            } R.id.imgx_ipa -> {
                var resourceName = "x_ipa.pdf"
                openPDF2(resourceName)
            } R.id.imgx_ips -> {
                var resourceName = "x_ips.pdf"
                openPDF2(resourceName)
            } R.id.imgx_informatika -> {
                var resourceName = "x_informatika.pdf"
                openPDF2(resourceName)
            }
        }
    }

    private fun openPDF2(resourceName: String) {
        val imagePath = File(Environment.getExternalStorageDirectory().absolutePath + "/Download/" + resourceName)

        imagePath.mkdir()
        val imageFile = File(imagePath.path)

        val popUpDialogAlert: AlertDialog.Builder = AlertDialog.Builder(requireContext())

        /* popUpDialogAlert.setMessage(imageFile.toString())
        popUpDialogAlert.setTitle("TEST")
        popUpDialogAlert.setPositiveButton("OK", null)
        popUpDialogAlert.setCancelable(true)
        popUpDialogAlert.create().show()

        popUpDialogAlert.setPositiveButton("Ok"
        ) { _, _ -> } */

        // Write data in your file
    var uri2 = FileProvider.getUriForFile(
        Objects.requireNonNull(requireContext()),
        BuildConfig.APPLICATION_ID + ".provider", imageFile);

        val intent = ShareCompat.IntentBuilder.from(requireContext() as Activity)
            .setStream(uri2) // uri from FileProvider
            //.setType("application/pdf")
            .intent
            .setDataAndType(uri2, "application/pdf")
            .setAction(Intent.ACTION_VIEW) //Change if needed
            .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        startActivity(Intent.createChooser(intent, "View PDF"));
    }

    private fun popUpDialogFrag(){
        val popUpDialogAlert: AlertDialog.Builder = AlertDialog.Builder(requireContext())

        popUpDialogAlert.setMessage("Maaf password atau username salah")
        popUpDialogAlert.setTitle("LOGIN GAGAL")
        popUpDialogAlert.setPositiveButton("OK", null)
        popUpDialogAlert.setCancelable(true)
        popUpDialogAlert.create().show()

        popUpDialogAlert.setPositiveButton("Ok"
        ) { _, _ -> }
    }

}