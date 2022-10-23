package com.smada.app.litelda.activities

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smada.app.litelda.R
import com.smada.app.litelda.databinding.ActivityDashboardBinding
import com.smada.app.litelda.fragments.Account
import com.smada.app.litelda.fragments.BookFragmentX
import com.smada.app.litelda.fragments.Favorite
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener

val sampleImages = intArrayOf(
    R.drawable.carouselv_1,
    R.drawable.carouselv_2,
    R.drawable.carouselv_3,
    R.drawable.carouselv_4
)

class DashboardAct : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityDashboardBinding
    private lateinit var botnavItem1: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
                R.id.botnav_favorite -> {
                    fragment = Favorite()
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

    private val onBackPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            //handle back button here
            fragBackButtonHandle()
        }
    }

    fun fragBackButtonHandle() {
        val iconHomeSelected = botnavItem1.selectedItemId

        if(iconHomeSelected!= R.id.botnav_home ){
            botnavItem1.selectedItemId = R.id.botnav_home
            supportFragmentManager.beginTransaction().addToBackStack(null).commit()
        } else {
            this.finish()
        }

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.cv_x -> {
                val fragment : Fragment?
                fragment = BookFragmentX()
                loadFragment(fragment)
                true
            } else -> false

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
}