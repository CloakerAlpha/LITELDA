package com.smada.app.litelda.activities

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smada.app.litelda.R
import com.smada.app.litelda.databinding.ActivityDashboardBinding
import com.smada.app.litelda.fragments.AboutApp
import com.smada.app.litelda.fragments.Account
import com.smada.app.litelda.fragments.BookFragmentX
import com.smada.app.litelda.fragments.BookFragmentXI
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import java.io.*

val sampleImages = intArrayOf(
    R.drawable.carouselv_1,
    R.drawable.carouselv_2,
    R.drawable.carouselv_3,
    R.drawable.carouselv_4,
    R.drawable.carouselv_5
)

class DashboardAct : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityDashboardBinding
    private lateinit var botnavItem1: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.nestedScrollView.isNestedScrollingEnabled = false;

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        botnavItem1 = binding.botnav1
        botnavItem1.selectedItemId = R.id.botnav_home
        botnavItem1.setOnItemSelectedListener{ item->
            val fragment : Fragment?
            when (item.itemId){
                R.id.botnav_account -> {
                    fragment = Account()
                    loadFragment(fragment)
                    true
                }
                R.id.botnav_home ->{
                    val currentFragment = supportFragmentManager.findFragmentById(R.id.fl_dashboard)
                    if (currentFragment != null) {
                        this.supportFragmentManager
                            .beginTransaction()
                            .remove(currentFragment)
                            .commit()
                    }
                    true
                }
                R.id.botnav_aboutapp -> {
                    fragment = AboutApp()
                    loadFragment(fragment)
                    true
                }
                else -> false
            }
        }

        val carouselView = findViewById<CarouselView>(R.id.carouselView)
        carouselView?.pageCount = sampleImages.size
        carouselView?.setImageListener(imageListener)

    }

    private fun hasWriteStoragePermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return true
        } else
            if (ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                val REQUEST_PERMISSIONS_CODE_WRITE_STORAGE = 333
                requestPermissions(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_PERMISSIONS_CODE_WRITE_STORAGE
                )

                return false
            }

        return true
    }

    private fun customModalDialog(){
            val dialogView   = layoutInflater.inflate(R.layout.customdialog, null)
            //val btnCloseDialog       = dialogView.findViewById<Button>(R.id.btnCloseNotif)
            val alertBuilder = AlertDialog.Builder(this)
            alertBuilder.setView( dialogView )
            alertBuilder.setNegativeButton("Okay") { dialogInterface, i -> dialogInterface.dismiss() }
            alertBuilder.create().show()
            /*alertBuilder.setPositiveButton("Tambah", DialogInterface.OnClickListener {
                    dialogInterface, i ->
                //val nim         = et_nim.text.toString().trim()

                dialogInterface.dismiss()
            })*/

    }

    private fun copyAssets() {
        val assetManager: AssetManager? = this.assets
        var files: Array<String>? = null
        try {
            files = assetManager?.list("")
        } catch (e: IOException) {
            Log.e("tag", "Failed to get asset file list.", e)
        }
        for (filename in files!!) {
            var `in`: InputStream?
            var out: OutputStream?
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

    private val onBackPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            //handle back button here
            fragBackButtonHandle()
        }
    }

    fun fragBackButtonHandle() {
        //val iconHomeSelected = botnavItem1.selectedItemId
        val count = supportFragmentManager.backStackEntryCount

        if(count==0){
            botnavItem1.selectedItemId = R.id.botnav_home
            supportFragmentManager.beginTransaction().addToBackStack(null).commit()
        } else {
            supportFragmentManager.popBackStack();
        }

    }

    override fun onClick(v: View) {
        when (v.id) {

            R.id.btn_bukuKM -> {
                val fragment : Fragment?
                fragment = BookFragmentX()
                loadFragment(fragment)
            } R.id.btn_bukuK13 -> {
                val fragment : Fragment?
                fragment = BookFragmentXI()
                loadFragment(fragment)
            } R.id.btn_bukuUKBM -> {
                //popUpDialogFrag()
                customModalDialog()
            } R.id.btn_bukuLAIN -> {
                //popUpDialogFrag()
                customModalDialog()
            } R.id.fab_sync -> {
                hasWriteStoragePermission()
                copyAssets()
        }

        }
    }

    private var imageListener: ImageListener = ImageListener { position, imageView ->
        // You can use Glide or Picasso here
        Glide.with(this@DashboardAct).load(sampleImages[position]).into(imageView)
    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_dashboard, fragment)
            .commit()
    }

    private fun popUpDialogFrag(){
        val popUpDialogAlert: AlertDialog.Builder = AlertDialog.Builder(this)

        popUpDialogAlert.setMessage("Maaf buku belum tersedia")
        popUpDialogAlert.setTitle("Oops...")
        popUpDialogAlert.setPositiveButton("OK", null)
        popUpDialogAlert.setCancelable(true)
        popUpDialogAlert.create().show()

        popUpDialogAlert.setPositiveButton("Ok"
        ) { _, _ -> }
    }

}