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
import java.io.*


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
        return binding.root
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.imgx_pkn -> {
                var resourceName = "x_ppkn.pdf"
                hasWriteStoragePermission()
                copyAssets()
                //copyFiletoExternalStorage(R.raw.x_ppkn, resourceName)
                //popUpDialogFrag()

            } R.id.imgx_sejarah -> {
            var resourceName = "x_sejarah.pdf"
            //copyFiletoExternalStorage(R.raw.x_sejarah, resourceName)
            //popUpDialogFrag()

        }
        }
    }

    private fun hasWriteStoragePermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return true
        } else
            if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                val REQUEST_PERMISSIONS_CODE_WRITE_STORAGE = 333
                requestPermissions(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_PERMISSIONS_CODE_WRITE_STORAGE
                )

                return false
            }

        return true
    }

    private fun copyAssets() {
        val assetManager: AssetManager? = activity?.assets
        var files: Array<String>? = null
        try {
            files = assetManager?.list("")
        } catch (e: IOException) {
            Log.e("tag", "Failed to get asset file list.", e)
        }
        for (filename in files!!) {
            var `in`: InputStream? = null
            var out: OutputStream? = null
            try {
                `in` = assetManager?.open(filename)
                val outDir = Environment.getExternalStorageDirectory().absolutePath + "/Download/"
                val outFile = File(outDir, filename)
                out = FileOutputStream(outFile)
                `in`?.let { copyFile(it, out as FileOutputStream) }
                `in`?.close()
                out.flush()
                out.close()
                out = null
                Log.d("success_tag", filename+ "copied successfully");
            } catch (e: IOException) {
                Log.e("err_tag", "Failed to copy asset file: $filename", e)
            }
        }
    }

    @Throws(IOException::class)
    private fun copyFile(`in`: InputStream, out: OutputStream) {
        val buffer = ByteArray(1024)
        var read: Int
        while (`in`.read(buffer).also { read = it } != -1) {
            out.write(buffer, 0, read)
        }
    }

    /* private fun copyFiletoExternalStorage(resourceId: Int, resourceName: String) {
         val pathSDCard =
             (activity?.filesDir?.absolutePath) + resourceName
             //Environment.getDataDirectory().path + "/Android/" + resourceName

         val popUpDialogAlert: AlertDialog.Builder = AlertDialog.Builder(requireContext())
         popUpDialogAlert.setMessage(pathSDCard)
         popUpDialogAlert.setTitle("INFO")
         popUpDialogAlert.setPositiveButton("OK", null)
         popUpDialogAlert.setCancelable(true)
         popUpDialogAlert.create().show()

         popUpDialogAlert.setPositiveButton("Ok"
         ) { _, _ -> }

         try {
             val `in` = resources.openRawResource(resourceId)
             var out: FileOutputStream?
             out = FileOutputStream(pathSDCard)
             val buff = ByteArray(1024)
             var read: Int
             try {
                 while (`in`.read(buff).also { read = it } > 0) {
                     out.write(buff, 0, read)
                 }
             } finally {
                 `in`.close()
                 out.close()
             }
         } catch (e: FileNotFoundException) {
             e.printStackTrace()
         } catch (e: IOException) {
             e.printStackTrace()
         }
     } */

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