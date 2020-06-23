package com.simfyafrica.assessment.ui

import android.os.Bundle
import com.simfyafrica.assessment.R
import dagger.android.support.DaggerAppCompatActivity
import androidx.navigation.fragment.NavHostFragment

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupCatsFragment()
    }

    private fun setupCatsFragment() {
        val host = NavHostFragment.create(R.navigation.navigation_graph)
        supportFragmentManager.beginTransaction().replace(R.id.fl_main_content, host).setPrimaryNavigationFragment(host).commit()
    }
}
