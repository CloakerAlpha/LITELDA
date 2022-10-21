package com.smada.app.litelda.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smada.app.litelda.R
import com.smada.app.litelda.databinding.ActivityDashboardBinding
import com.smada.app.litelda.fragments.Account
import com.smada.app.litelda.fragments.Favorite
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener


private lateinit var botnavItem1: BottomNavigationView

    val sampleImages = intArrayOf(
        R.drawable.carouselv_1,
        R.drawable.carouselv_2,
        R.drawable.carouselv_3,
        R.drawable.carouselv_4
    )

class DashboardAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDashboardBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

    override fun onBackPressed() {
        lateinit var binding: ActivityDashboardBinding
        setContentView(binding.root)
        botnavItem1 = binding.botnav1
        val iconHomeSelected = botnavItem1.selectedItemId

        if(iconHomeSelected!= 1 ){
            botnavItem1.selectedItemId = R.id.botnav_home
            supportFragmentManager.beginTransaction().addToBackStack(null).commit()
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